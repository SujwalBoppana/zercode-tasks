package de.zeroco.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.zeroco.util.Utility;

public class DataMigration {

	public static String migration(String fileName, Connection connection, String schema, String stateTblName,
			String districtTblName, String cityTblName, String pincodeTblName) {
		if (Utility.isBlankWithVarArguments(fileName, schema))
			return null;
		BufferedReader lineReader = null;
		int stateId=1;
		
		try {
			lineReader = new BufferedReader(new FileReader(fileName));
			String lineText = null;
			lineReader.readLine();
			while ((lineText = lineReader.readLine()) != null) {
				List<Object> lineData = new ArrayList<Object>(Arrays.asList(lineText.split(",")));
				if (DbUtils.list(connection, schema, stateTblName, new ArrayList<String>(), "name", "=",
						(String) lineData.get(0)).size() == 0) {
					DbUtils.getGenertedId(connection, schema, stateTblName, Arrays.asList("name", "code", "status"),
							Arrays.asList(lineData.get(0), lineData.get(1), "success"));
				}
				List<Map<String, Object>> dataFromDb = DbUtils.list(connection, schema, stateTblName,
						new ArrayList<String>(), "name", "=", (String) lineData.get(0));
				Object pk_id = null;
				for (Map<String, Object> map : dataFromDb) {
					pk_id = map.get("pk_id");
				}
				if (DbUtils.list(connection, schema, districtTblName, Arrays.asList(), "name", "=",
						(String) lineData.get(2)).size() == 0) {
					DbUtils.getGenertedId(connection, schema, districtTblName,
							Arrays.asList("name", "code", "status", "state"),
							Arrays.asList(lineData.get(2), lineData.get(3), "success", pk_id));
				}
				dataFromDb = DbUtils.list(connection, schema, districtTblName, new ArrayList<String>(), "name", "=",
						(String) lineData.get(2));
				for (Map<String, Object> map : dataFromDb) {
					pk_id = map.get("pk_id");
				}
				if (DbUtils
						.list(connection, schema, cityTblName, Arrays.asList(), "name", "=", (String) lineData.get(4))
						.size() == 0) {
					DbUtils.getGenertedId(connection, schema, cityTblName,
							Arrays.asList("name", "code", "status", "district"),
							Arrays.asList(lineData.get(4), lineData.get(5), "success", pk_id));
				}
				dataFromDb = DbUtils.list(connection, schema, cityTblName, new ArrayList<String>(), "name", "=",
						(String) lineData.get(4));
				for (Map<String, Object> map : dataFromDb) {
					pk_id = map.get("pk_id");
				}
				if (DbUtils.list(connection, schema, pincodeTblName, Arrays.asList(), "name", "=",
						(String) lineData.get(6)).size() == 0) {
					DbUtils.getGenertedId(connection, schema, pincodeTblName,
							Arrays.asList("name", "code", "status", "city"),
							Arrays.asList(lineData.get(6), lineData.get(6), "success", pk_id));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lineReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/";
		String schema = "data_migration";
		String userName = "admin";
		String password = "Sujwal@123";
		String fileName = "D:\\files\\Master_Pincode_data.csv";
		Connection connection = DbUtils.getConnection(url, schema, userName, password);
		System.out.println(migration(fileName, connection, schema, "state", "district", "city", "postal_code"));
		DbUtils.getCloseConnection(connection);
	}

}

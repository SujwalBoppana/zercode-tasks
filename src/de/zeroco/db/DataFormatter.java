package de.zeroco.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.zeroco.util.Utility;

public class DataFormatter {
	public static void main(String[] args) throws SQLException {
		
		String data ="<p>The mortgage of OMV on plot 1/01/04/02/607 at Gisozi-Gasabo District</p>";
		System.out.println(data.replaceAll("<[^>]*>", ""));
		
		
		
		
//		String url = "jdbc:mysql://localhost:3306/";
//		String schema = "app";
//		String userName = "admin";
//		String password = "Sujwal@123";
//		Connection connection = DbUtils.getConnection(url, schema, userName, password);
//		List<Map<String, Object>> data = DbUtils.get(connection, schema, "facility",
//				Arrays.asList("pk_id", "desc", "facility_purpose"));
//		for (Map<String, Object> map : data) {
//			List<Object> updatedList = new ArrayList<Object>();
//			updatedList.add(Utility.isBlank(map.get("desc")) ?map.get("desc"):format((String) map.get("desc")));
//			updatedList.add(Utility.isBlank(map.get("facility_purpose")) ?map.get("facility_purpose"):format((String) map.get("facility_purpose")));
//			updatedList.add(map.get("pk_id"));
//			//DbUtils.updateData(connection, schema, "facility", Arrays.asList("desc","facility_purpose"), updatedList, "pk_id");
//			System.out.println(updatedList);
//		}
//		DbUtils.getCloseConnection(connection);
	}

	public static String format(String data) {
		if(Utility.isBlank(data)) return null;
		return data.replaceAll("<[^>]*>", "");
	}

}

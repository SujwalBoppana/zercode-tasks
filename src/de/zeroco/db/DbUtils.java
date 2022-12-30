package de.zeroco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.zeroco.util.Utility;

public class DbUtils {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/";
		String schema = "zerocode";
		String userName = "admin";
		String password = "Sujwal@123";
		
		Connection connection = getConnection(url, schema, userName, password);
//		System.out.println(insertData(connection, schema, "app_migration", new ArrayList<String>(Arrays.asList("data_set","source_column")), new ArrayList<String>(Arrays.asList("app_role","Himachal Pradesh"))));
//		List<String> listColumns = new ArrayList<String>(Arrays.asList("target_column","status","failure_reason"));
//		System.out.println(changeFormat(connection, schema, "app_migration", "source_column",listColumns,"pk_id"));
//		System.out.println(getData(connection, schema, "app_migration", new ArrayList<String>()));
	//	createTable(connection, schema, "master_country", Arrays.asList("pk_id","name","code"), Arrays.asList("int","varchar(255)","varchar(255)"), Arrays.asList("PRIMARY KEY","",""));
	//	System.out.println(changeFormat(connection, schema, "app_migration","data_set","target_column","source_column"));
//		insertData(connection, schema, "user_details",
//				Arrays.asList("first_name", "middle_name", "last_name", "gender", "address", "phone", "email",
//						"userid", "pass"),
//				Arrays.asList("firstName", "middleName", "lastName", "gender", "address", "phone", "email", "userid", "password"));
		createTable(connection, schema, "user_details", Arrays.asList("name","phno","email","date_of_birth","age"), Arrays.asList("VARCHAR(255)","VARCHAR(11)","VARCHAR(255)","DATE","INT"), Arrays.asList("","","","",""));
	//	System.out.println(get(connection, schema, "user_details", Arrays.asList("first_name", "middle_name", "last_name", "gender", "address", "phone", "email",
	//			"userid", "pass")));
		getCloseConnection(connection);
	}
	
	public static List<Map<String, Object>> get(Connection connection, String schema, String tableName,
			List<String> columns) {
		List<Map<String, Object>> result = new ArrayList<>();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(QueryBuilder.getAllQuery(schema, tableName, columns));
			ResultSet resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				int count = 1;
				int colCount = metaData.getColumnCount();
				Map<String, Object> data = new LinkedHashMap<>(colCount);
				while (count <= colCount) {
					data.put(metaData.getColumnName(count), resultSet.getObject(count));
					count++;
				}
				result.add(data);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String changeFormat(Connection connection, String schema, String tableName,String dataSet,String targetColumn,String sourceColumn) {
		List<String> tableNames = new ArrayList<String>();
		List<String> targetColumns = new ArrayList<String>();
		List<String> sourceColumns = new ArrayList<String>();
		PreparedStatement statement = null;
		PreparedStatement stmt = null;
		PreparedStatement update = null;
		int count = 0;
		try {
			statement = connection.prepareStatement(QueryBuilder.getAllQuery(schema, tableName, new ArrayList<String>()));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tableNames.add(resultSet.getString(dataSet));
				targetColumns.add(resultSet.getString(targetColumn));
				sourceColumns.add(resultSet.getString(sourceColumn));
			}
			while (count<tableNames.size()) {
				stmt = connection.prepareStatement(QueryBuilder.getAllQuery(schema, tableNames.get(count), Arrays.asList(sourceColumns.get(count),"pk_id")));
				update = connection.prepareStatement(QueryBuilder.getUpdateQuery(schema, tableNames.get(count), Arrays.asList(targetColumns.get(count)), "pk_id"));
				ResultSet result= stmt.executeQuery();
				while (result.next()) {
					update.setObject(1, getFormat(result.getString(sourceColumns.get(count))));
					update.setObject(2, result.getObject("pk_id"));
					update.executeUpdate();
				}
				updateData(connection, schema, tableName, Arrays.asList("status"), Arrays.asList("success",++count), "pk_id");
			}
			
		} catch (Exception e) {
			updateData(connection, schema, tableName, Arrays.asList("status","failure_reason"), Arrays.asList("failed",e.toString(),++count), "pk_id");
			System.out.println(e);
		}
		return "data updated";
		
	}
	public static String changeFormat(Connection connection, String schema, String tableName, String sourceColumn,
			List<String> columns, String referenceColmn) throws SQLException {
		PreparedStatement statement = null;
		PreparedStatement stmt = null;
		PreparedStatement expStmt = null;
		int count = 1;
		try {
			statement = connection.prepareStatement(
					QueryBuilder.getAllQuery(schema, tableName, new ArrayList<String>(Arrays.asList(sourceColumn))));
			stmt = connection.prepareStatement(QueryBuilder.getUpdateQuery(schema, tableName, columns, referenceColmn));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				stmt.setObject(1, getFormat(resultSet.getString(sourceColumn)));
				stmt.setObject(2, "success");
				stmt.setObject(3, "null");
				stmt.setObject(4, count);
				stmt.executeUpdate();
				count++;
			}
		} catch (Exception e) {
			expStmt = connection.prepareStatement("UPDATE `"+ tableName+"`SET failure_reason = "+ e +" WHERE `pk_id` = "+count+";");
			expStmt.executeUpdate();
			e.printStackTrace();

		}
		return "sucessfully updated";
	}
	public static String getFormat(String stateName) {
		stateName = (stateName.trim()).toLowerCase();
		stateName = stateName.replaceAll("[^a-zA-Z]+", "_");
		return stateName.endsWith("_") ? stateName.substring(0, stateName.length() - 1) : stateName;
	}
	/**
	 * This method will creates a table with he given input
	 * @author sujwal b
	 * @since 2022-12-26
	 * @param connection
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param dataTypes
	 * @param constrains
	 */
	public static void createTable(Connection connection,String schema,String tableName,List<String> columns,List<String> dataTypes,List<String> constrains) {
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getCreateTableQuery(schema, tableName, columns, dataTypes, constrains));
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method will returns the connection
	 * @author sujwal b
	 * @since 2022-12-20
	 * @param url
	 * @param schema
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(String url,String schema, String userName, String password) throws SQLException {
		if (Utility.isBlankWithVarArguments(url,schema,userName,password)) 	return null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url+schema, userName, password);
			connection.setAutoCommit(false);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * this method will close the given connection
	 * @author sujwal b
	 * @since 2022-12-23
	 * @param connection
	 */
	public static void getCloseConnection(Connection connection) {
		try {
			if(!connection.getAutoCommit()) {
				connection.commit();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method will insert the data with in the given fields in a table
	 * @author sujwal b 
	 * @since 2022-12-21
	 * @param connection
	 * @param tableName
	 * @param fields
	 * @param data
	 * @return status
	 */
	public static int insertData(Connection connection,String schema, String tableName,List<String>columns,List<Object>inputData ) {
		if (Utility.isBlankWithVarArguments(tableName,inputData)) 	return 0;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getInsertQuery(schema, tableName, columns),new String[] { "ID" });
			int count =1;
			for (Object data : inputData) {
				statement.setObject(count, data);
				count++;
			}
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			   return  resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * this method will updates the data with given input
	 * @author sujwal b
	 * @since 2022-12-23
	 * @param connection
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param inputData
	 * @param referenceColmn
	 * @param referenceData
	 * @return status 
	 */
	public static int updateData(Connection connection, String schema ,String tableName,List<String>columns, List<Object> inputData ,String referenceColmn) {
		if (Utility.isBlankWithVarArguments(tableName,columns,inputData,referenceColmn)) return 0;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getUpdateQuery(schema, tableName, columns, referenceColmn),new String[] { "ID" });
			int count = 1;
			for (Object data : inputData) {
				statement.setObject(count, data);
				count++;
			}
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
	/**
	 * this method will return all the data in the given table
	 * @author sujwal b
	 * @since 2022-12-23
	 * @param connection
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @return list
	 */
	public static List<?> getData(Connection connection, String schema, String tableName, List<String> columns) {
		if (Utility.isBlankWithVarArguments(schema, tableName))
			return null;
		return getData(connection, schema, tableName, columns, "ref", "ref", "ref");
	}
	
	/**
	 * this method will returns the data in input table with the given condition
	 * @author sujwal b
	 * @since 2022-12-23
	 * @param connection
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param referenceColmn
	 * @param operator
	 * @param referenceData
	 * @return list
	 */
	public static List<?> getData(Connection connection, String schema, String tableName, List<String> columns,String referenceColmn,String operator,String referenceData) {
		if (Utility.isBlankWithVarArguments(schema, tableName))
			return null;
		List<Object> list = new ArrayList<>();
		PreparedStatement statement = null;
		try {
			
			if (referenceColmn.equals("ref")) {
				 statement = connection
						.prepareStatement(QueryBuilder.getAllQuery(schema, tableName, columns));
			}else {
				 statement = connection
							.prepareStatement(QueryBuilder.getDataQuery(schema, tableName, columns, referenceColmn, operator,referenceData));
			}
			ResultSet resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				int count = 1;
				while (count <= metaData.getColumnCount()) {
					list.add(resultSet.getObject(count));
					count++;
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * this method will delete the rows with the given data 
	 * @param connection
	 * @param schema
	 * @param tableName
	 * @param referenceColumn
	 * @param operator
	 * @param referenceData
	 * @return
	 */
	public static int deleteData(Connection connection,String schema,String tableName,String referenceColumn,String operator,List<String> referenceData) {
		if (Utility.isBlankWithVarArguments(tableName,referenceColumn,referenceData,operator,schema)) return 0;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getDeleteQuery(schema, tableName, referenceColumn, operator, referenceData));
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
	

package de.zeroco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.zeroco.util.Utility;

public class DbUtils {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/";
		String schema = "zerocode";
		String userName = "admin";
		String password = "Sujwal@123";
		
		Connection connection = getConnection(url, schema, userName, password);
//		List<String> columns = new ArrayList<>(Arrays.asList("rollno","name","age","gender","email", "phno","subject_code"));
//		List<String> inputData = new ArrayList<>(Arrays.asList("108","mech","harsha","19","f","harsha@gmail.com", "8307531199"));
//		List<String> subject = new ArrayList<>(Arrays.asList("branch","subject","subject_code"));
//		List<String> sub = new ArrayList<>(Arrays.asList("mech","drawing","r1625"));
		//System.out.println(getData(connection, schema, "student_details","subject_details", columns));
		List<String> columns = new ArrayList<>(Arrays.asList("rollno","branch", "subject"));
		List<String> dataTypes = new ArrayList<>(Arrays.asList("VARCHAR(5)", "VARCHAR(20)", "VARCHAR(25)"));
		List<String> constrains = new ArrayList<>(Arrays.asList("NOT NULL","UNIQUE", "UNIQUE"));
	//	createTable(connection, schema, "order_details", columns, dataTypes, constrains);
	//	System.out.println(insertData(connection, schema, "subject_details", subject, sub));
	//	System.out.println(updateData(connection, schema,"employes" , columns, inputData, "emp_id", "113"));
	//	System.out.println(insertData(connection, schema, "employes", columns, inputData));
	//	System.out.println(getData(connection, schema, "student", columns,"age",">","23"));
		//List<String> ageList = new ArrayList<>(Arrays.asList("21","23"));
	//	System.out.println(deleteData(connection, schema, "duplicate", "age", "IN", ageList));
	//	System.out.println(getData(connection, schema, "student", columns));
		//System.out.println(updateData(connection, schema, "student", columns, inputData, "name"));
		List<String> secondTableColumns = new ArrayList<>(Arrays.asList("DEPTCODE","DeptName", "LOCATION"));
		List<String> firstTableColumns = new ArrayList<>(Arrays.asList("salary","Manager", "Job", "HireDate","EmpLName","EmpFName","EmpCode","DEPTCODE","Commission"));
		List<?> results =  getData(connection, schema, "employee", "department", firstTableColumns, secondTableColumns,"INNER JOIN", "DEPTCODE", "DEPTCODE");
		for (Object result : results) {
			System.out.println(result);
		}
		System.out.println(getData(connection, schema, "employee", "department", firstTableColumns, secondTableColumns,"INNER JOIN","DEPTCODE", "DEPTCODE"));
		getCloseConnection(connection);
	}

	public static List<?> getData(Connection connection, String schema, String firstTable, String secondTable,
			List<String> columnsOfFirstTable, List<String> columnsOfSecondTable, String jointType, String tableOneRef,
			String tableTwoRef) {
		if (Utility.isBlankWithVarArguments(schema, firstTable))
			return null;
		List<Object> list = new ArrayList<>();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(QueryBuilder.getJoinsQuery(schema, firstTable, secondTable,
					columnsOfFirstTable, columnsOfSecondTable, jointType, tableOneRef, tableTwoRef));
			ResultSet resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String col = "";
			int column = 0;
			while (resultSet.next()) {
				int count = 1;
				String temp = "";
				while (count <= metaData.getColumnCount()) {
					if (column == 0)
						col += metaData.getColumnName(count) + " ";
					temp += resultSet.getObject(count) + " ";
					count++;
				}
				column++;
				list.add(temp);
			}
			list.add(0, col);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
	public static int insertData(Connection connection,String schema, String tableName,List<String>columns,List<String>inputData ) {
		if (Utility.isBlankWithVarArguments(tableName,inputData)) 	return 0;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getInsertQuery(schema, tableName, columns),new String[] { "ID" });
			int count =1;
			for (String data : inputData) {
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
	public static int updateData(Connection connection, String schema ,String tableName,List<String>columns, List<String> inputData ,String referenceColmn) {
		if (Utility.isBlankWithVarArguments(tableName,columns,inputData,referenceColmn)) return 0;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getUpdateQuery(schema, tableName, columns, referenceColmn),new String[] { "ID" });
			int count = 1;
			for (String data : inputData) {
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
		if (Utility.isBlankWithVarArguments(schema, tableName, columns))
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
		if (Utility.isBlankWithVarArguments(schema, tableName, columns))
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
	

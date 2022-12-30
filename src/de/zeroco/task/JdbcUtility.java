package de.zeroco.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.zeroco.db.QueryBuilder;
import de.zeroco.util.Utility;

public class JdbcUtility {
	public static final String GRAVE = "`";
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
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	public static String insertData(Connection connection,String schema, String tableName,List<String>columns,List<String>inputData ) {
		if (Utility.isBlankWithVarArguments(tableName,inputData)) 	return null;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryBuilder.getInsertQuery(schema, tableName, columns));
			int count =1;
			for (String data : inputData) {
				statement.setObject(count, data);
				count++;
			}
			statement.execute();
			return "Data Entered Successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * this method will will insert the data
	 * @author sujwal b
	 * @since 2022-12-21
	 * @param connection
	 * @param tableName
	 * @param data
	 * @param fields
	 * @return status
	 */
	public static String insertData(Connection connection, String tableName,String data,String... fields ) {
		if (Utility.isBlankWithVarArguments(tableName,data,fields)) 	return null;
		String temp = "";
		try {
			for (String field : fields) {
				temp= field+",";
			}
			PreparedStatement statement = connection.prepareStatement("INSERT INTO "+tableName+"("+temp+")"+" VALUES"+"("+data+")");
			statement.execute();
			return "Data Entered Successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * this method will updates the data in given table in the specified column 
	 * @param connection
	 * @param tableName
	 * @param columnName
	 * @param newData
	 * @param refColumName
	 * @param refData
	 * @return status
	 */
	public static String updateData(Connection connection, String tableName, String columnName, String newData,String refColumName, String refData) {
		if (Utility.isBlankWithVarArguments(tableName,columnName,newData,refColumName,refData)) return "";
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE " + tableName + " SET " + columnName +" = " +"'"+ newData+"'" + " WHERE " + refColumName + " = " + "'"+refData+"'");
			statement.execute();
			return "data updated successfully.";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * this method will gives the column names present inside the table
	 * @author sujwal b
	 * @since 2022-12-21
	 * @param connection
	 * @param tableName
	 * @return list
	 */
	public static List<Object> getColumnNames(Connection connection, String tableName){
		List<Object> list = new ArrayList<Object>();
		try {
			PreparedStatement statement = connection.prepareStatement("DESC "+tableName);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				list.add(res.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * this method will returns the data in list according to query
	 * @author sujwal b
	 * @since 2022-12-21
	 * @param connection
	 * @param query
	 * @return list
	 */
	public static List<?> getData(Connection connection, String query) {
		if (Utility.isBlankWithVarArguments(query))
			return null;
		List<Object> list = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
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
	 * this method will delete the data of a particular cell
	 * @author sujwal b
	 * @ since 2022-12-21
	 * @param connection
	 * @param tableName
	 * @param referenceClmn
	 * @param referenceData
	 * @return status
	 */
	public static String deleteData(Connection connection,String tableName,String referenceClmn,String referenceData) {
		if (Utility.isBlankWithVarArguments(tableName,referenceClmn,referenceData)) return null;
		try {
			PreparedStatement psmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE " + referenceClmn + " = " + "'" + referenceData + "'");
			psmt.execute();
			return referenceData + " data deleted successfully.";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * this method will delete the column of the given table
	 * @author sujwal b
	 * @since 2022-12-21
	 * @param connection
	 * @param tableName
	 * @param columnName
	 * @return status
	 */
	public static String deleteColumn(Connection connection, String tableName,String columnName) {
		if (Utility.isBlankWithVarArguments(tableName,columnName)) return null;
		try {
			PreparedStatement statement = connection.prepareStatement("ALTER TABLE "+tableName+" DROP "+columnName);
			statement.execute();
			return "Data removed Successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	/**
	 * this method will create a new table with given parameters
	 * @author sujwal b
	 * @since 2022-12-21
	 * @param connection
	 * @param tableName
	 * @param columnAndDataType
	 * @return status
	 */
	public static String CreateTable(Connection connection, String tableName, String... columnAndDataType) {
		if (Utility.isBlankWithVarArguments(tableName, columnAndDataType)) {
			return null;
		}
		String columnName = "";
		for (String names : columnAndDataType) {
			columnName = columnName + "," + names;
		}
		columnName = columnName.startsWith(",") ? columnName.substring(1) : columnName;
		try {
			PreparedStatement statement = connection
					.prepareStatement("CREATE TABLE " + tableName + "(" + columnName + ")");
			statement.execute();
			return tableName + " table created sucessfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * this method will returns a query to update the given data
	 * @author sujwal b
	 * @since 2022-12-22
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param referenceColmn
	 * @param referenceData
	 * @return query
	 */
	public static String getUpdateQuery(String schema, String tableName, List<String> columns,String referenceColmn ,String referenceData ) {
		String col = "";
		for (String column : columns) {
			col += ","+GRAVE+column+GRAVE+" = ? ";
		}
		return "UPDATE " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE + " SET " + col.substring(1) + " WHERE (" +GRAVE+ referenceColmn+ GRAVE+" = "+referenceData+" );";
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
	//
//	public static List<?> getData(Connection connection, String schema, String firstTable, String secondTable,
//			List<String> columnsOfFirstTable, List<String> columnsOfSecondTable, String jointType, String tableOneRef,
//			String tableTwoRef) {
//		if (Utility.isBlankWithVarArguments(schema, firstTable))
//			return null;
//		List<Object> list = new ArrayList<>();
//		PreparedStatement statement = null;
//		try {
//			statement = connection.prepareStatement(QueryBuilder.getJoinsQuery(schema, firstTable, secondTable,
//					columnsOfFirstTable, columnsOfSecondTable, jointType, tableOneRef, tableTwoRef));
//			ResultSet resultSet = statement.executeQuery();
//			ResultSetMetaData metaData = resultSet.getMetaData();
//			String col = "";
//			int column = 0;
//			while (resultSet.next()) {
//				int count = 1;
//				String temp = "";
//				while (count <= metaData.getColumnCount()) {
//					if (column == 0)
//						col += metaData.getColumnName(count) + " ";
//					temp += resultSet.getObject(count) + " ";
//					count++;
//				}
//				column++;
//				list.add(temp);
//			}
//			list.add(0, col);
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
//	public static List<?> get(Connection connection,String schema,String tableName,String target,String resultTable) {
//		List<String> columns = new ArrayList<>();
//		List<String> tempList = new ArrayList<>();
//		PreparedStatement statement = null;
//		PreparedStatement stmt = null;
//		try {
//			statement = connection.prepareStatement("SELECT " + "`"+target+ "`  FROM `"+schema+"`.`"+tableName+"` ;");
//			stmt  = connection.prepareStatement("DESC `"+schema+"`.`"+resultTable+"`;");
//			System.out.println(stmt);
//			ResultSet resultSet = statement.executeQuery();
//			ResultSet colSet = stmt.executeQuery();
//			while (colSet.next()) {
//				columns.add(colSet.getString(1));
//			}
//			while (resultSet.next()) {
//				tempList.add(0,tableName);
//				tempList.add(1,getFormat(resultSet.getString(target)));
//				tempList.add(2,resultSet.getString(target));
//				tempList.add(3,null);
//				tempList.add(4,null);
//				System.out.println(tempList);
//				insertData(connection, schema, resultTable, columns, tempList);
//				tempList.clear();
//			}
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	public static void main(String[] args) {
		
	}
}

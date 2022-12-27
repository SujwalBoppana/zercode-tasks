package de.zeroco.testing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import de.zeroco.util.Utility;

public class JdbcTesting {
	public static String getSchema(String url, String userName, String password,String schema) {
		if (Utility.isBlankWithVarArguments(url,userName,password,schema)) {
			return null;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection(url, userName, password);
			statement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + schema);
			statement.execute();
			return  schema;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && statement != null) {
					connection.close();
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * this method will returns the connection 
	 * @author sujwal b
	 * @since 2022-12-20 
	 * @param url
	 * @param userName
	 * @param password
	 * @return connection
	 */
	public static Connection getConnection(String url, String userName, String password) {
		if (Utility.isBlankWithVarArguments(url,userName,password)) {
			return null;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, userName, password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * this method will creates a table in given data base 
	 * @author sujwal b
	 * @since 2022-12-20
	 * @param url
	 * @param userName
	 * @param password
	 * @param table
	 * @return status
	 */
	public static String createTable(Connection connection, String table) {
	
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + table);
			statement.execute();
			return  "table created successfully";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && statement != null) {
					connection.close();
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * This methos will insert the data in the given table
	 * @author sujwal b
	 * @since 2022-12-20
	 * @param url
	 * @param userName
	 * @param password
	 * @param tableName
	 * @param data
	 * @return status
	 */
	public static String insertData(String url, String userName, String password, String tableName,String data ) {
		if (Utility.isBlankWithVarArguments(url,userName,password,tableName,data)) {
			return null;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection(url, userName, password);
			statement = connection.prepareStatement("INSERT INTO "+tableName+" VALUES"+data);
			statement.execute();
			return "Data Entered Successfully";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && statement != null) {
					connection.close();
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * this method will return all the data present in the data base
	 * @param url
	 * @param userName
	 * @param password
	 * @param tableName
	 * @return
	 */
	public static List<String> get(String url, String userName, String password, String tableName) {
		if (Utility.isBlankWithVarArguments(url, userName, password, tableName)) {
			return null;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		List<String> list = new ArrayList<>();
		try {
			connection = getConnection(url, userName, password);
			statement = connection.prepareStatement("SELECT * FROM " + tableName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " "
						+ resultSet.getString(4) + " " + resultSet.getString(5));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && statement != null) {
					connection.close();
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String updateData(String url, String userName, String password, String tableName,String data) {
		
		return data;
		
	}
	
	public static String insertData(String url, String userName, String password, String tableName,String data,boolean createTable ) {
		if (Utility.isBlankWithVarArguments(url,userName,password,tableName,data)) {
			return null;
		}
		
		
		
		return data;
		
	}


	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/zerocode";
		String userName = "admin";
		String password = "Sujwal@123";
	//	System.out.println(insertData(url, userName, password, "employee", "('sujwal','sujwalboppana2000@gmail.com','6309638199')"));
		System.out.println(get(url, userName, password, "student"));
	//System.out.println(createTable(url, userName, password,"employee(name varchar(24) NOT NULL,emailid varchar(30) NOT NULL UNIQUE,phno varchar(10) UNIQUE NOT NULL CHECK(LENGTH(phno)=10))" ));
//		System.out.println(getConnection("jdbc:mysql://localhost:3306/zerocode", "admin", "Sujwal@123"));
	
		
		/*Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zerocode", "admin", "Sujwal@123");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}

}

package de.zeroco.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.zeroco.util.Utility;

public class JdbcPractice {
	
	
	
	
	
	
	
	
	
	
	public static Connection getConnection(String url, String schema, String userName, String password)
			throws SQLException {
		if (Utility.isBlankWithVarArguments(url, schema, userName, password))
			return null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url + schema, userName, password);
			return connection;
		} catch (Exception e) {
			Connection tempConnect = null;
			PreparedStatement tempStatement = null;
			try {
				tempConnect = DriverManager.getConnection(url, userName, password);
				tempStatement = tempConnect.prepareStatement("CREATE DATABASE IF NOT EXISTS " + schema);
				tempStatement.execute();
				tempConnect.close();
				return getConnection(url, schema, userName, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				if (tempConnect != null || tempStatement != null) {
					tempStatement.close();
					tempConnect.close();
				}
			}
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/zerocode";
		String userName = "admin";
		String password = "Sujwal@123";
		Connection connection = JdbcTesting.getConnection(url, userName, password);
	
	}

}

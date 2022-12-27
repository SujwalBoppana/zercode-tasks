package de.zeroco.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentTesting {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zerocode","admin","Sujwal@123");
			PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS demo(name varchar(24) NOT NULL,email varchar(30) NOT NULL,phno varchar(10) UNIQUE NOT NULL )");
			statement.execute();
			String data ="INSERT INTO demo VALUES";
			//statement.execute(data+"('bhanu','bhanu12843@gmail.com','8919190233')");
			//statement.execute("DROP TABLE employee");
			System.out.println("INSERT INTO"+"employeee"+"VALUES" + " ('sujwal','sujwalboppana2000@gmail.com','6309638199')");
			statement.execute("INSERT INTO "+"employeee"+" VALUES" + "('sujwal','sujwalboppana2000@gmail.com','6309638199')");
			System.out.println(statement.isClosed());
			System.out.println(statement.execute());
			Statement stmt=connection.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from student");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));  
			connection.close();  
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
		
		}
	}

}

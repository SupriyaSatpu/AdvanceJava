package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DButils {
	
	static Connection fetchDBConnection() throws ClassNotFoundException, SQLException {
		
		//Load JDBC driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Establish connection
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2","root","1234");

		
	}
		
}

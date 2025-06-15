package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBUtils {
	
	static Connection fetchDBConnection() throws ClassNotFoundException, SQLException {
		
		//Load JDBC driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Establish connection
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","1234");

		
	}
		
}

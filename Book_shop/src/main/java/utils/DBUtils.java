package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBUtils {
	static Connection fetchDBConnection() throws ClassNotFoundException, SQLException {
		//load JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Establish Connection
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/book_shop","root","1234");
		
	}

}

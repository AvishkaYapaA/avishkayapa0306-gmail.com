package DbConnect;

import java.sql.*;

public class DbConnect {
	
	//A common method to connect to the DB
	public Connection connect() throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb", "root", "");
		
		return con;	
	}
	
	

}

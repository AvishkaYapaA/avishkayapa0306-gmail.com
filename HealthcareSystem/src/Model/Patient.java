package Model;

import java.sql.*;

import DbConnect.DbConnect;

public class Patient {

	//insert-------------------------------------------------------------------------------------------------------------------------
		public String insertPatient(String username,String address,String nic,String dob,String gender,String mobile_number,String email,String password)
	    {
				String output = "";
				try
				{
					DbConnect db = new DbConnect();
					Connection con = null;
					con = db.connect();
					
					if (con == null)
					{    
						return "Error while connecting to the database for inserting new patient.";
					}
					// create a prepared statement
					String query = "insert into patient"
							+"(`userID`,`userName`,`userAddress`,`nicNo`,`dateOfBirth`,`gender`,`mobileNumber`,`email`,`password`)"
							 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
							PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, username);
					preparedStmt.setString(3, address);
					preparedStmt.setString(4, nic);
					preparedStmt.setString(5, dob);
					preparedStmt.setString(6, gender); 
					preparedStmt.setString(7, mobile_number); 
					preparedStmt.setString(8, email); 
					preparedStmt.setString(9, password); 
		
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted new patient successfully";
				}
					catch (Exception e)
				{
						output = "Error while inserting the new patient!";
						System.err.println(e.getMessage());
				}
				return output;
	    		}
	
}

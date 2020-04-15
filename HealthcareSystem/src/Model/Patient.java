package Model;

import java.sql.*;


import DbConnect.DbConnect;

public class Patient {

	//comment 01
	//comment
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
	
		
		
		//view------------------------------------------------------------------------------------------------------------------------
		public String readPatients(){
			
			String output = "";
				try{
					DbConnect db = new DbConnect();
					Connection con = null;
					con = db.connect();
				
				if (con == null){
					return "Error while connecting to the database for reading."; 
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Item Code</th><th>Item Name</th><th>ItemPrice</th><th>ItemDescription</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from items";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()){
					
					String itemID = Integer.toString(rs.getInt("itemID"));
					String itemCode = rs.getString("itemCode");
					String itemName = rs.getString("itemName");
					String itemPrice = Double.toString(rs.getDouble("itemPrice"));
					String itemDesc = rs.getString("itemDesc");
					// Add into the html table
					output += "<tr><td>" + itemCode + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + itemPrice + "</td>";
					output += "<td>" + itemDesc + "</td>";
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>" 
					+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
					+ "<input name=\"itemID\" type=\"hidden\" value=\"" + itemID
					+ "\">" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
				}
				catch (Exception e){
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}
				
		return output;
		
		}
}

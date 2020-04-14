package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DbConnect.DbConnect;

public class Hospital {

	//insert hospitals-------------------------------------------------------------------------------------------------------------------------
	
			public String insertHospital(String MOHCode,String Managername,String Hospitalname,String address,String TPNumber,String location,String username,String password)
		    {
					String output = "";
					try
					{
						DbConnect db = new DbConnect();
						Connection con = null;
						con = db.connect();
						
						if (con == null)
						{    
							return "Error while connecting to the database for inserting new hospital.";
						}
						// create a prepared statement
						String query = "insert into hospital"
								+"(`HospitalID`,`MOHcode`,`ManagerName`,`HospitalName`,`Address`,`TPnumber`,`Location`,`Username`,`Password`)"
								 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
								PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, MOHCode);
						preparedStmt.setString(3, Managername);
						preparedStmt.setString(4, Hospitalname);
						preparedStmt.setString(5, address);
						preparedStmt.setString(6, TPNumber); 
						preparedStmt.setString(7, location); 
						preparedStmt.setString(8, username); 
						preparedStmt.setString(9, password); 
			
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Inserted new hospital successfully";
					}
						catch (Exception e)
					{
							System.out.println("erro insert hospital");
							output = "Error while inserting the new hospital!";
							System.err.println(e.getMessage());
					}
					return output;
		    }
			
	//display hospitals-------------------------------------------------------------------------------------------------------------------------
					
			public String readHospital()
			{
				String output = "";
				try
				{
					DbConnect db = new DbConnect();
					Connection con = null;
					con = db.connect();
					
					if (con == null)
					{    
						return "Error while connecting to the database for inserting new hospital.";
					}
					
					// Prepare the html table to be displayed
					output = "<table border=\"1\">"
							+ "<tr>"
							+ "<th>MOH Registration Code</th>"
							+ "<th>Manager Name</th>"
							+ "<th>Hospital Name</th>"
							+ "<th>Address</th>"
							+ "<th>Telephone Number</th>"
							+ "<th>Location</th>"
							+ "<th>Update</th>"
							+ "<th>Remove</th>"
							+ "</tr>";
					
					String query = "select * from hospital";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String HospitalID = Integer.toString(rs.getInt("HospitalID"));
						String MOHcode = rs.getString("MOHcode");
						String ManagerName = rs.getString("ManagerName");
						String HospitalName = rs.getString("HospitalName");
						String Address = rs.getString("Address");
						String TPnumber = rs.getString("TPnumber");
						String Location = rs.getString("Location");
						
//						String Username = rs.getString("Username");
//						String Password = rs.getString("Password");
						
						// Add into the html table
						output += "<tr><td>" + MOHcode + "</td>";
						output += "<td>" + ManagerName + "</td>";
						output += "<td>" + HospitalName + "</td>";
						output += "<td>" + Address + "</td>";
						output += "<td>" + TPnumber + "</td>";
						output += "<td>" + Location + "</td>";
						
//						output += "<td>" + Username + "</td>";
//						output += "<td>" + Password + "</td>";
						
						// buttons
							output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"items.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
							+ "<input name=\"itemID\" type=\"hidden\" value=\"" + HospitalID
							+ "\">" + "</form></td></tr>";
					}
					con.close();
					// Complete the html table
					output += "</table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the Hospital.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
}

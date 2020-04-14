package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
			
}

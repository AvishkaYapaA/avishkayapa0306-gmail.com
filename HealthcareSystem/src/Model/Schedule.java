package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DbConnect.DbConnect;

public class Schedule {
	
	public String insertSchedule(int doctor_ID, String doctor_Name, String specialization ,
			int hospital_ID, String chargers,  String available_Time, String end_Time, String sever_Time)
   {
		
		String output = "";
			try
			{
				DbConnect db = new DbConnect();
				Connection con = null;
				con = db.connect();
				
				if (con == null)
				{    
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = "insert into schedule"
						+"(`Shedule_ID`, `Doctor_ID`, `Doctor_Name`, `Specialization`, `Hospital_ID`, `Chargers`, `Available_Time`, `End_Time`, `Sever_Time`)"
						 + " values (?,?,?,?,?,?,?,?,?)";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setInt(2, doctor_ID);
				preparedStmt.setString(3, doctor_Name);
				preparedStmt.setString(4, specialization);
				preparedStmt.setInt(5, hospital_ID);
				preparedStmt.setDouble(6, Double.parseDouble(chargers));
				preparedStmt.setString(7, available_Time);
				preparedStmt.setString(8, end_Time);
				preparedStmt.setString(9, sever_Time); 
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
				catch (Exception e)
			{
					output = "Error while inserting the item.";
					System.err.println(e.getMessage());
			}
			return output;
    		}

	
	//                 READ 
	
	public String readschedule() {
		String output = "";
		try {

			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();
			
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			

			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>Shedule ID</th>" 
					+ "<th>Doctor ID</th>"
					+ "<th>Doctor Name</th>"
					+ "<th>Specialization</th>"
					+ "<th>Hospital ID</th>"
					+ "<th>Available Time</th>"
					+ "<th>End Time</th>"
					+ "<th>Chargers</th>"
					+ "<th>Sever Time</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";

			String query = "select * from schedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String Shedule_ID = Integer.toString(rs.getInt("Shedule_ID"));
				String Doctor_ID = Integer.toString(rs.getInt("Doctor_ID"));
				String Doctor_Name = rs.getString("Doctor_Name");
				String Specialization = rs.getString("Specialization");
				String Hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));
				String Chargers = Double.toString(rs.getDouble("Chargers"));
				String Available_Time = rs.getString("Available_Time");
				String End_Time = rs.getString("End_Time");
				String Sever_Time = rs.getString("Sever_Time");
				
				
				// Add into the html table
				output += "<tr><td>" + Shedule_ID + "</td>";
				output += "<td>" + Doctor_ID + "</td>";
				output += "<td>" + Doctor_Name + "</td>";
				output += "<td>" + Specialization + "</td>";
				output += "<td>" + Hospital_ID + "</td>";
				output += "<td>" + Available_Time + "</td>";
				output += "<td>" + End_Time + "</td>";
				output += "<td>" + Chargers + "</td>";
				output += "<td>" + Sever_Time + "</td>";
				
				// buttons
				// buttons
				output += "<td><input name=\"btnUpdate\" " 
								+ " type=\"button\" value=\"Update\">"
							+ "</td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">" 
							+ "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" 
							+ "<input name=\"itemID\" type=\"hidden\" "
						+ " value=\""
						+ Shedule_ID + "\">" + "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Schedule.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	//  Update  
	
	public String updateSchedule(int schedule_ID, int doctor_ID, String doctor_Name, String specialization ,
			int hospital_ID, double chargers,  String available_Time, String end_Time, String sever_Time)
	    {
		String output = "";
		try {

			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE `schedule` SET `Doctor_ID`=?,`Doctor_Name`=?,`Specialization`=?,`Hospital_ID`=?,`Chargers`=?,`Available_Time`=?,`End_Time`=?,`Sever_Time`=? WHERE `Shedule_ID`=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, doctor_ID);
			preparedStmt.setString(2, doctor_Name);
			preparedStmt.setString(3, specialization);
			preparedStmt.setInt(4, hospital_ID);
			preparedStmt.setDouble(5, chargers);
			preparedStmt.setString(6, available_Time);
			preparedStmt.setString(7, end_Time);
			preparedStmt.setString(8, sever_Time);
			preparedStmt.setInt(9, schedule_ID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteschedule(String schedule_ID) {
		String output = "";
		try {
			
			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();
			
			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from schedule where Shedule_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(schedule_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	
	

}

package Model;

import java.sql.*;


import DbConnect.DbConnect;;

public class Doctor {

	public String insertDoctors( String dname, String dRegNo, String specialization, int contactNo, String address, String email, String hospitalName)
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
				String query = "insert into doctor"
						+"(`DoctorId`,`Dname`,`DRegNo`,`Specialization`,`ContactNo`,`Address`,`Email`,`HospitalName`)"
						 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						
						
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, dname);
				preparedStmt.setString(3, dRegNo);
				preparedStmt.setString(4, specialization);
				preparedStmt.setInt(5, contactNo);
				preparedStmt.setString(6, address);
				preparedStmt.setString(7, email);
				preparedStmt.setString(8, hospitalName);
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted Doctor successfully";
			}
				catch (Exception e)
			{
					output = "Error while inserting doctors";
					System.err.println(e.getMessage());
			}
			return output;
    		}

	public String readDoctors() {
		String output = "";
		try {
			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();
			
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>Doctor Name</th>"
					+ "<th>Registration No</th>"
					+ "<th>Specialization</th>"
					+ "<th>Contact No</th>"
					+ "<th>Address</th>"
					+ "<th>Email</th>"
					+ "<th>Hospital Name</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";


			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				
				String doctorId = Integer.toString(rs.getInt("DoctorId"));
				String dname = rs.getString("Dname");
				String dRegNo = rs.getString("DRegNo");
				String specialization = rs.getString("Specialization");
				String contactNo = Integer.toString(rs.getInt("ContactNo"));
				String address = rs.getString("Address");
				String email = rs.getString("Email");
				String hospitalName = rs.getString("HospitalName");
				
				// Add into the html table
				output += "<tr>"
					    + "<td>" + dname + "</td>";
				output += "<td>" + dRegNo + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + hospitalName + "</td>";
				
				
		
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">"
				+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
				+ "<input name=\"doctorId\" type=\"hidden\" value=\"" + doctorId + "\">" 
				+ "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctors.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateDoctor(String DoctorId,String Dname, String DRegNo, String Specialization, int ContactNo, String Address,
			String Email, String HospitalName) {
		
		
		String output = "";
		
		try {
			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
				String query = "UPDATE doctor SET Dname = ?, DRegNo = ?,Specialization = ?,ContactNo = ?, Address = ?,Email = ?,HospitalName = ? WHERE DoctorId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, Dname);
			preparedStmt.setString(2, DRegNo);
			preparedStmt.setString(3, Specialization);
			preparedStmt.setInt(4, ContactNo);
			preparedStmt.setString(5, Address);
			preparedStmt.setString(6, Email);
			preparedStmt.setString(7, HospitalName);
			preparedStmt.setInt(8, Integer.parseInt(DoctorId));
			
			
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

	public String deleteDoctor(String DoctorId) {
		String output = "";
		try {
			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();
			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctor where DoctorId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(DoctorId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted Doctor successfully";
		} catch (Exception e) {
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

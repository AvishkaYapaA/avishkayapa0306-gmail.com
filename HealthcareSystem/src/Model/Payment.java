package Model;

import java.sql.*;

import DbConnect.DbConnect;;

public class Payment {
	
	public String insertPayment(int cardNo, String nameOnCard, String expDate, int cvc) {
		String output = "";
		try {

			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "insert into payment" + "(`pamentID`,`cardNo`,`nameOnCard`,`expDate`,`cvc`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, cardNo);
			preparedStmt.setString(3, nameOnCard);
			preparedStmt.setString(4, expDate);
			preparedStmt.setInt(5, cvc);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted Payment successfully";
		} catch (Exception e) {
			output = "Error while inserting Payment Info";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readPayment() {
		String output = "";
		try {
			DbConnect db = new DbConnect();
			Connection con = null;
			con = db.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

			output = "<table border=\"1\">" + "<tr>" + "<th>Card No</th>" + "<th>Name On Card</th>"
					+ "<th>Expiry Date</th>" + "<th>CVC No</th>" + "<th>Update</th>" + "<th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {

				String pamentID = rs.getString("pamentID");
				String cardNo = Integer.toString(rs.getInt("cardNo"));
				String nameOnCard = rs.getString("nameOnCard");
				String expDate = rs.getString("expDate");
				String cvc = Integer.toString(rs.getInt("cvc"));

				// Add into the html table
				output += "<tr>" + "<td>" + cardNo + "</td>";
				output += "<td>" + nameOnCard + "</td>";
				output += "<td>" + expDate + "</td>";
				output += "<td>" + cvc + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"pamentID\" type=\"hidden\" value=\"" + pamentID + "\">" + "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

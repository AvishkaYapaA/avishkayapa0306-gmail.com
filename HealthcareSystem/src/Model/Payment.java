package Model;

import java.sql.*;

import DbConnect.DbConnect;;

public class Payment {
	
	//testpayment branch

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

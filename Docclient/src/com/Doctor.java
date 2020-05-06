package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class Doctor {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doctorser?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	public String insertDoctor(String name, String age ,String address, String email, String phone, String spec) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into doctors(`doctorID`,`doctorName`,`doctorAge`,`doctorAddress`,`doctorEmail`,`doctorPhone`,`doctorSpec`)"
					+ " values (? ,? ,?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, age);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phone);
			preparedStmt.setString(7, spec);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newDoctors = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the doctor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDoctors() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>Doctor Name</th><th>Doctor Age</th><th>Doctor Address</th><th>Doctor Email</th><th>Doctor PhoneNo</th><th>Doctor Specialization</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from doctors";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String doctorID = Integer.toString(rs.getInt("doctorID"));
				String doctorName = rs.getString("doctorName");
				String doctorAge = rs.getString("doctorAge");
				String doctorAddress = rs.getString("doctorAddress");
				String doctorEmail = rs.getString("doctorEmail");
				String doctorPhone = rs.getString("doctorPhone");
				String doctorSpec = rs.getString("doctorSpec");

				// Add into the html table
				output += "<tr><td><input id=\'hidDoctorIDUpdate\' name=\'hidDoctorIDUpdate\' type=\'hidden\' value=\'" + doctorID + "'>" 
						+ doctorName + "</td>"; 
			output += "<td>" + doctorAge + "</td>"; 	
			output += "<td>" + doctorAddress + "</td>";
			output += "<td>" + doctorEmail + "</td>";
			output += "<td>" + doctorPhone + "</td>";     
			output += "<td>" + doctorSpec + "</td>"; 

				// buttons
			output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-doctorid='" + doctorID + "'>" + "</td></tr>"; 
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

	public String updateDoctor(String ID, String name,String age, String address, String email, String phone, String spec) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE doctors SET doctorName=?,doctorAge=?,doctorAddress=?,doctorEmail=?,doctorPhone=?,doctorSpec=? WHERE doctorID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, age);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, spec);
			preparedStmt.setInt(7, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newDoctors = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";    
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the doctor.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteDoctor(String doctorID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from doctors where doctorID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(doctorID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newDoctors = readDoctors();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					newDoctors + "\"}";  
		} catch (Exception e) {
			output = "Error while deleting the doctor.";    
			System.err.println(e.getMessage());  
		}

		return output;
	}

	
}
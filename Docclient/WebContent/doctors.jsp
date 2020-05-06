<%@page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/doctors.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">

				<h1>Doctor Management</h1>

				<form id="formDoctor" name="formDoctor" method="post"
					action="doctors.jsp">
					 Name <input id="doctorName" name="doctorName" type="text"
						class="form-control form-control-sm"> <br> 
					 Age <input id="doctorAge" name="doctorAge" type="text"
						class="form-control form-control-sm"> <br> 
					Address <input id="doctorAddress" name="doctorAddress" type="text"
						class="form-control form-control-sm"> <br> 
					Email <input id="doctorEmail" name="doctorEmail" type="text"
						class="form-control form-control-sm"> <br> 
					PhoneNo <input id="doctorPhone" name="doctorPhone" type="text"
						class="form-control form-control-sm"> <br>
					
					Specialization <br>
					
						<select id="doctorSpec" name="doctorSpec" class="class="form-control form-control-sm">
 							<option value="0">--Select Specialization--</option>
 							<option value="Allergists/Immunologists">Allergists/Immunologists</option>
 							<option value="Anesthesiologists">Anesthesiologists</option>
 							<option value="Cardiologists">Cardiologists</option>
 							<option value="Colon and Rectal Surgeons">Colon and Rectal Surgeons</option>
 							<option value="Critical Care Medicine Specialists">Critical Care Medicine Specialists</option>
 							<option value="Dermatologists">Dermatologists</option>
 							<option value="Endocrinologists">Endocrinologists</option>
 							<option value="Emergency Medicine Specialists">Emergency Medicine Specialists</option>
 							<option value="Family Physicians">Family Physicians</option>
 							<option value="Gastroenterologists">Gastroenterologists</option>
 							<option value="Geriatric Medicine Specialists">Geriatric Medicine Specialists</option>
 							<option value="Neurologists">Neurologists</option>
 							<option value="Plastic Surgeons">Plastic Surgeons</option>
 							<option value="Radiologists">Radiologists</option>
 							<option value="Ophthalmologists">Ophthalmologists</option>
						</select>
						
						<br>
					
					  <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success">
					
				</div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divDoctorsGrid">
					<%
						Doctor doctorObj = new Doctor();
					out.print(doctorObj.readDoctors());
					%>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
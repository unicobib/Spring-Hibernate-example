<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" />
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js" />
<script src="https://cdn.datatables.net/fixedheader/3.1.5/js/dataTables.fixedHeader.min.js" />
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js" />
<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js" />
<meta charset="ISO-8859-1">
<title>Employee Table</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped" id="employee" >
			<thead>
				<tr>
					<th>Name</th>
					<th>Joining Date</th>
					<th>Salary</th>
					<th>SSN</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<th>${employee.name}</th>
						<th>${employee.joinningDate}</th>
						<th>${employee.salary}</th>
						<th><a href="<c:url value='/edit-${employee.ssn}-employee'/>">${employee.ssn}</a></th>
						<th><a href="<c:url value='/delete-${employee.ssn}-employee'/>">delete</a>
					</tr>
				</c:forEach>
			</thead>
		</table>
		<br>
		<a href="<c:url value='/new'/>"> add New Employee</a>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>

<h2>  Manage Suppliers  </h2>

<div id="CreateSupplier">

	<form action="manage_supplier_add" method="get">

		<input type="text" name="id" placeholder="Supplier ID"> 
		<input type="text" name="name" placeholder="Supplier Name">
		<input type="text" name="description" placeholder="Description"> 
		<input type="submit" value="Create Supplier">
	</form>
	
</div>	
	
	<h2> Delete / Update the Suppliers  </h2>
	<div id="ShowSuppliers">
	
		<table border="2">
		<thead>
		<tr>
		<td> Supplier ID</td>
		<td> Supplier Name</td>
		<td> Supplier Description </td>
		<td>  Action  </td>
		</tr>
		</thead>
		
	<c:forEach var="supplier" items="${supplierList}">
	
	<tr>  
	 <td> ${supplier.id} </td>
	  <td> ${supplier.name} </td>
	   <td> ${supplier.description} </td>
	   
	   <td> <a href="manage_supplier_delete/${supplier.id}"> Delete | </a>   
	   <a href=""> Update  </a>    </td>
	
	</tr>

	</c:forEach>
	
	</table>
	
	</div>
	
</body>
</html>
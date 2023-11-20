<%-- 
    Document   : Departments DB
    Created on : Jul 4, 2023, 1:10:07 PM
    Author     : cge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
<link href="${pageContext.request.contextPath}/css/bootstrap.min_v4.6.2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/icons/font/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slim.min.v3.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.min_v4.6.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Chart.min.v2.9.3.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
<title>My Request in progress</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
var table = $('#myTable').DataTable();
table.order( [ 0, 'asc' ] ).draw();
$('.alert').alert()
$('[data-toggle="tooltip"]').tooltip();
});
</script>
<%@include file="../shared/head.jsp" %>
<div class="container">
<h1>Mis Solicitudes en proceso:</h1>
<hr class="mb-4">
<table id="myTable" class="table table-striped table-sm">
<thead>
	<tr>
		<th>ID</th>
		<th>Fecha de creación</th>
        <th>Inicio</th>
        <th>Comentarios</th>
        <th>Tarea</th>
        <th>Prioridad</th>
        <th>Recibe</th>
		<th style="text-aling:center;">Acciones</th>
    </tr>
</thead>
<tbody>
<c:forEach var="ListItem" items="${listReqInProgress}">
	<!-- Link para Detalles -->
	<c:url var="linkDetalle" value="/sistemas/detail">
		<c:param name="id" value="${ListItem.id}"></c:param>
	</c:url>
    <tr>
    	<td>${ListItem.id}</td>
    	<td><fmt:formatDate type="date" value="${ListItem.creation}" /></td>
    	<td>${ListItem.startactivity}</td>
    	<td>${ListItem.comments}</td>
    	<td>${ListItem.task}</td>
    	<td>${ListItem.priority}</td>
    	<td>${ListItem.receiver}</td>
        <td class="text-center">
        	<div class="btn-group" role="group" aria-label="Basic example">
  				<a href="${linkDetalle}" type="button" class="btn btn-outline-warning btn-sm" data-toggle="tooltip" data-placement="top" title="Visualizar Datos"><i class="bi-eye"></i></a>
  			</div>
        </td>
    </tr>
</c:forEach>
</tbody>
<tfoot>
	<tr>
    	<th>ID</th>
		<th>Fecha de creación</th>
        <th>Inicio</th>
        <th>Comentarios</th>
        <th>Tarea</th>
        <th>Prioridad</th>
        <th>Recibe</th>
		<th style="text-aling:center;">Acciones</th>
    </tr>
</tfoot>
</table>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

<%-- 
    Document   : Configuration DB
    Created on : Jul 4, 2023, 1:10:07 PM
    Author     : cge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min_v4.6.2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/icons/font/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slim.min.v3.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.min_v4.6.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Chart.min.v2.9.3.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
<title>User Details</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
$('[data-toggle="tooltip"]').tooltip();
});
</script>
<%@include file="../shared/head.jsp" %>
<div class="container">
<a href="${pageContext.request.contextPath}/usuarios/list" type="button" class="btn btn-outline-dark btn-sm" data-toggle="tooltip" data-placement="top" title="Regresar al Listado"><i class="bi-arrow-bar-left"></i></a>
<h2>Detalles del Usuario</h2>
<hr class="mb-4">
<div class="table-responsive">
<table class="table table-bordered table-striped">
<tr>
<th scope="row" class="text-right col-2">Username:</th>
<td>${user.username}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Activo:</th>
<td>${user.enabled}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Permisos:</th>
<td>
	<ul>
	<c:forEach var="TempAuthority" items="${user.authority}">
		<li>${TempAuthority}</li>
	</c:forEach>
	</ul>
</td>
</tr>
</table>
</div>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

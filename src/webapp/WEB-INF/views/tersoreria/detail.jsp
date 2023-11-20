<%-- 
    Document   : Configuration DB
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
<link href="${pageContext.request.contextPath}/css/bootstrap.min_v4.6.2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/icons/font/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slim.min.v3.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.min_v4.6.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Chart.min.v2.9.3.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
<title>Request Details</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
$('[data-toggle="tooltip"]').tooltip();
});
</script>
<%@include file="../shared/head.jsp" %>
<div class="container">
<a href="${pageContext.request.contextPath}/tersoreria/myreqinprogress" type="button" class="btn btn-outline-dark btn-sm" data-toggle="tooltip" data-placement="top" title="Regresar al Listado"><i class="bi-arrow-bar-left"></i></a>
<h2>Detalles de la Solicitud</h2>
<hr class="mb-4">
<div class="table-responsive">
<table class="table table-bordered table-striped">
<tr>
<th scope="row" class="text-right col-2">ID:</th>
<td>${req.id}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Creación:</th>
<td><fmt:formatDate type="date" value="${req.creation}" /></td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Fecha de Inicio:</th>
<c:choose>
    <c:when test="${req.startactivity == null}">
        <td>Sin datos...</td>
    </c:when>
    <c:otherwise>
        <td>${req.startactivity}</td>
    </c:otherwise>
</c:choose>
</tr>
<tr>
<th scope="row" class="text-right col-2">Fecha de Fin:</th>
<c:choose>
    <c:when test="${req.endactivity == null}">
        <td>Sin datos...</td>
    </c:when>
    <c:otherwise>
        <td>${req.endactivity}</td>
    </c:otherwise>
</c:choose>
</tr>
<tr>
<th scope="row" class="text-right col-2">Comentarios:</th>
<c:choose>
    <c:when test="${req.comments == null}">
        <td>Sin datos...</td>
    </c:when>
    <c:otherwise>
        <td>${req.comments}</td>
    </c:otherwise>
</c:choose>
</tr>
<tr>
<th scope="row" class="text-right col-2">Tarea:</th>
<td>${req.task}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Prioridad:</th>
<td>${req.priority}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Empleado que Envia:</th>
<td>${req.sender}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Empleado que Recibe:</th>
<td>${req.receiver}</td>
</tr>
<tr>
<th scope="row" class="text-right col-2">Departamento:</th>
<td>${req.department}</td>
</tr>
</table>
</div>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

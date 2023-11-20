<%-- 
    Document   : Departments DB
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
<title>Task List</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
$('#myTable').DataTable();
$('.alert').alert()
$('[data-toggle="tooltip"]').tooltip();
});
function eliminar() {
  var x = confirm("Vas a eliminar un registro. ¡Estás seguro?!");
  if (x)
    return true;
  else
    return false;
}
</script>
<%@include file="../shared/head.jsp" %>
<div class="container">
<c:if test="${message=='C'}">
<div class="alert alert-success alert-dismissible fade show" role="alert">
	<strong><i class="bi-check2"></i> Success!</strong> Nuevo registro creado.
  	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
</div>
</c:if>
<c:if test="${message=='U'}">
<div class="alert alert-primary alert-dismissible fade show" role="alert">
	<strong><i class="bi-arrow-repeat"></i> Info!</strong> Datos del registro actualizados.
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
</div>
</c:if>
<c:if test="${message=='D'}">
<div class="alert alert-danger alert-dismissible fade show" role="alert">
	<strong><i class="bi-x-lg"></i> Warning!</strong> Registro eliminado.
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
</div>
</c:if>
<h2>Listado de Tareas</h2>
<button type="button" class="btn btn-outline-success btn-sm" onclick="window.location.href='create'; return false;" data-toggle="tooltip" data-placement="top" title="Agregar nuevo"><i class="bi-plus-lg"></i> Crear</button>
<hr class="mb-4">
<table id="myTable" class="table table-striped table-sm">
<thead>
	<tr>
		<th>ID</th>
		<th>Descripción</th>
        <th>Departamento</th>
        <th style="text-aling:center;">Acciones</th>
    </tr>
</thead>
<tbody>
<c:forEach var="ItemTemp" items="${listado}">
	<!-- Link para Detalles -->
	<c:url var="linkDetalle" value="/tareas/detail">
		<c:param name="id" value="${ItemTemp.id}"></c:param>
	</c:url>
	<!-- Link para Actualizar -->
	<c:url var="linkActualizar" value="/tareas/edit">
		<c:param name="id" value="${ItemTemp.id}"></c:param>
	</c:url>
	<!-- Link para Eliminar -->
	<c:url var="linkEliminar" value="/tareas/eliminate">
		<c:param name="id" value="${ItemTemp.id}"></c:param>
	</c:url>
    <tr>
    	<td>${ItemTemp.id}</td>
        <td>${ItemTemp.description}</td>
        <td>${ItemTemp.department}</td>
        <td class="text-center">
        <form:form action="${linkEliminar}" modelAttribute="task" method="POST">
        	<div class="btn-group" role="group" aria-label="Basic example">
  				<a href="${linkDetalle}" type="button" class="btn btn-outline-warning btn-sm" data-toggle="tooltip" data-placement="top" title="Visualizar Datos"><i class="bi-eye"></i></a>
  				<a href="${linkActualizar}"type="button" class="btn btn-outline-primary btn-sm" data-toggle="tooltip" data-placement="top" title="Modificar Datos"><i class="bi-pencil-square"></i></a>  				
  				<button type="submit" class="btn btn-outline-danger btn-sm" onclick="return eliminar();" data-toggle="tooltip" data-placement="top" title="Eliminar Registro"><i class="bi-trash3"></i></button>
			</div>
		</form:form>
        </td>
    </tr>
</c:forEach>
</tbody>
<tfoot>
	<tr>
    	<th>ID</th>
        <th>Descripción</th>
        <th>Departamento</th>
        <th style="text-aling:center;">Acciones</th>
    </tr>
</tfoot>
</table>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>
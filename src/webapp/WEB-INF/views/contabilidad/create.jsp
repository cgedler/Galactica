<%-- 
    Document   : login
    Created on : Jul 4, 2023, 1:04:02 PM
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
<title>Add Request</title>
<script type="text/javascript">
$(document).ready(function(){
    var employees = ${employees};
    var tasks = ${tasks};
    $('#department').change(function() {
        $('#receiver').find('option').remove().end();
        for (var key in employees) {
            if (employees[key].departmentid == $('#department').val()) {
                $('#receiver').append($('<option>', { value: employees[key].id, text: employees[key].fullname, }, '</option>' ));
            };
        };
        $('#tasks').find('option').remove().end();
        for (var key in tasks) {
            if (tasks[key].departmentid == $('#department').val()) {
            	$('#tasks').append($('<option>', { value: tasks[key].id, label: tasks[key].description, }, '</option>' ));
            };
        };
    });
});
</script>
</head>
<body>
<%@include file="../shared/head.jsp" %>
<div class="container">
<a href="${pageContext.request.contextPath}/contabilidad/myreqtostart" type="button" class="btn btn-outline-dark btn-sm" data-toggle="tooltip" data-placement="top" title="Regresar al Listado"><i class="bi-arrow-bar-left"></i></a>
<h2>Crear nueva Solicitud</h2>
<form:form action="add" modelAttribute="request" method="POST">
<div class="row">
	<div class="col-md-6 mb-3">
		<label for="departmentid">Departamento:</label>
		<form:select path="departmentid" class="form-control" id="department">
		<c:forEach var="ItemDepartment" items="${departments}">
        	<form:option value="${ItemDepartment.id}" label="${ItemDepartment.description}"/>
        </c:forEach>  
        </form:select>
	</div>
</div>
<div id="prueba"></div>
<div class="row">
	<div class="col-md-6 mb-3">
		<label for="task">Tarea:</label>
		<form:select path="taskid" class="form-control" id="tasks"></form:select>  
	</div>
</div>
<div class="row">
	<div class="col-md-6 mb-3">
		<label for="receiverid">Empleado que Recibe la solicitud:</label>
		<form:select path="receiverid" class="form-control" id="receiver"></form:select>        
	</div>
</div>
<div class="row">
	<div class="col-md-6 mb-3">
		<label for="priorityid">Prioridad:</label>
		<form:select path="priorityid" class="form-control">
		<c:forEach var="ItemPriority" items="${prioritys}">
        	<form:option value="${ItemPriority.id}" label="${ItemPriority.description}"/>
        </c:forEach>  
        </form:select>  
	</div>
</div>
<div class="row">
	<div class="col-md-6 mb-3">
		<label for="creation">Comentarios:</label>
        <form:textarea path="comments" rows="3" cols="20" class="form-control"/>
    </div>
</div>
<button class="btn btn-primary btn-sm btn-block" type="submit">Guardar</button>
</form:form>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

<%-- 
    Document   : Departments DB
    Created on : Jul 4, 2023, 1:10:07 PM
    Author     : cge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>My Qualifys</title>
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
<h1>Mis Calificaciones Recibidas:</h1>
<hr class="mb-4">
<div class="row">
	<canvas id="QualifyValue" width="900" height="380"></canvas>
</div>
<hr class="mb-4">
<table id="myTable" class="table table-striped table-sm">
<thead>
	<tr>
		<th>ID</th>
		<th>Fecha de creación</th>
        <th>Comentarios</th>
        <th>Calificación</th>
    </tr>
</thead>
<tbody>
<c:forEach var="ListItem" items="${listMyQualifys}">	
    <tr>
    	<td>${ListItem.id}</td>
    	<td><fmt:formatDate type="date" value="${ListItem.creation}" /></td>
    	<td>${ListItem.comments}</td>
    	<td>
    	<c:if test="${ListItem.qualificationid==1}">
		<div style="color: #FF0000; font-size: 35px;"><strong><i class="fa-regular fa-face-frown"></i> ${ListItem.qualification}!!!</strong></div>
		</c:if>
    	<c:if test="${ListItem.qualificationid==2}">
		<div style="color: #FC6A00; font-size: 35px;"><strong><i class="fa-regular fa-face-rolling-eyes"></i> ${ListItem.qualification}!!!</strong></div>
		</c:if>
		<c:if test="${ListItem.qualificationid==3}">
		<div style="color: #F5D605; font-size: 35px;"><strong><i class="fa-regular fa-face-meh"></i> ${ListItem.qualification}!!!</strong></div>
		</c:if>
		<c:if test="${ListItem.qualificationid==4}">
		<div style="color: #9DEE09; font-size: 35px;"><strong><i class="fa-regular fa-face-grin-wink"></i> ${ListItem.qualification}!!!</strong></div>
		</c:if>
		<c:if test="${ListItem.qualificationid==5}">
		<div style="color: #008000; font-size: 35px;"><strong><i class="fa-regular fa-face-grin-stars"></i> ${ListItem.qualification}!!!</strong></div>
		</c:if>
    	</td>
    </tr>
</c:forEach>
</tbody>
<tfoot>
	<tr>
    	<th>ID</th>
		<th>Fecha de creación</th>
        <th>Comentarios</th>
        <th>Calificación</th>
    </tr>
</tfoot>
</table>
<script>
const obj = JSON.parse('${ValuesQualifys}');
const ctx = document.getElementById('QualifyValue');
new Chart(ctx, {
 	type: 'doughnut',
 	data: {
 		//labels: ['Awful', 'Poor', 'Average', 'Good', 'Excellent'],
 		labels: ['\uf57a', '\uf5a5', '\uf5a4', '\uf58c', '\uf587'],
 		
 		datasets: [{
 			data: [obj[0]._value, obj[1]._value, obj[2]._value, obj[3]._value, obj[4]._value],
 			backgroundColor: [   
                '#FFC0CB', 
 				'#EBBD9B',
 				'#E5DCA4',
 				'#D4E7B1',
 				'#91E391'
             ],
             borderColor: [
            	 '#FF0000',
                 '#FC6A00',
                 '#F5D605',
                 '#9DEE09',
                 '#008000'
             ],
 		}]
 	},
 	options: {
 		responsive: true,
 		legend: {
            display: true,
            labels: {
                fontSize: 24,
                fontFamily: "'Font Awesome 6 Free'",
                
            }
        }
 	}
 });
</script>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>
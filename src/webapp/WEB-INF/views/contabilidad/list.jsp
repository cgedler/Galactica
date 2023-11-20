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
<title>Request List</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
$('#myTable').DataTable();
$('[data-toggle="tooltip"]').tooltip();
});
</script>
<%@include file="../shared/head.jsp" %>
<div class="container-xl">
<h2>Solicitudes del Departamento</h2>
<hr class="mb-4">
<div class="row">
	<div class="col-md-8">
		<canvas id="requests" width="900" height="380"></canvas>
	</div>
	<div class="col-md-4">
		<table class="table table-striped table-md">
		<tr><th scope="row" class="text-right col-6">Para Comenzar:</th><td style="color:#FF6384;">${toStartPerc} %</td></tr>
		<tr><th scope="row" class="text-right col-6">En proceso:</th><td style="color:#4BC0C0;">${inProcessPerc} %</td></tr>
		</table>
	</div>
</div>
<hr class="mb-4">
<div class="row">
	<canvas id="thisYear" width="900" height="380"></canvas>
</div>
<hr class="mb-4">
<div class="row">
	<canvas id="lastYear" width="900" height="380"></canvas>
</div>	
<hr class="mb-4">
<div class="row">
	<canvas id="lastTwoYears" width="900" height="380"></canvas>
</div>	
<hr class="mb-4">
<div class="row">
    <h3>Otros Departamentos:</h3>
	<canvas id="DepartmentsValues" width="900" height="380"></canvas>
</div>
<script>
 const ctx1 = document.getElementById('requests');
 new Chart(ctx1, {
 	type: 'pie',
 	data: {
 		labels: ['Para comenzar', 'En proceso'],
 		datasets: [{
 			data: ['${toStart}', '${inProcess}'],
 			backgroundColor: [   
                 'rgba(255, 99, 132, 0.2)',
                 'rgba(75, 192, 192, 0.2)'
             ],
             borderColor: [
                 'rgba(255, 99, 132, 1)',
                 'rgba(75, 192, 192, 1)'
             ],
 		}]
 	},
 	options: {
 		responsive: true,
         plugins: {
         	legend: {
         		position: 'top',
         	},
         }
 	}
 });
 
 const ctx2 = document.getElementById('thisYear');
 new Chart(ctx2, {
	    type: 'bar',
	    data: {
			labels: ${labelMonths},
			datasets: [{
				label: 'Solicitudes procesadas en el año ${thisYear}',
				data: ${valuesMonthsThisYear},
				backgroundColor: [
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(75, 192, 192, 0.2)',
	                 'rgba(153, 102, 255, 0.2)',
	                 'rgba(255, 159, 64, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(75, 192, 192, 0.2)',
	                 'rgba(153, 102, 255, 0.2)',
	                 'rgba(255, 159, 64, 0.2)'
	             ],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				y: {
				beginAtZero: true
				}
			}
		}
 });
 
 const ctx3 = document.getElementById('lastYear');
 new Chart(ctx3, {
	    type: 'bar',
	    data: {
			labels: ${labelMonths},
			datasets: [{
				label: 'Solicitudes procesadas en el año ${lastYear}',
				data: ${valuesMonthsLastYear},
				backgroundColor: [
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(75, 192, 192, 0.2)',
	                 'rgba(153, 102, 255, 0.2)',
	                 'rgba(255, 159, 64, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(75, 192, 192, 0.2)',
	                 'rgba(153, 102, 255, 0.2)',
	                 'rgba(255, 159, 64, 0.2)'
	             ],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				y: {
				beginAtZero: true
				}
			}
		}
 });
 
 const ctx4 = document.getElementById('lastTwoYears');
 new Chart(ctx4, {
	    type: 'bar',
	    data: {
			labels: ${labelMonths},
			datasets: [{
				label: 'Solicitudes procesadas en el año ${lastTwoYears}',
				data: ${valuesMonthsLastTwoYears},
				backgroundColor: [
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(75, 192, 192, 0.2)',
	                 'rgba(153, 102, 255, 0.2)',
	                 'rgba(255, 159, 64, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(75, 192, 192, 0.2)',
	                 'rgba(153, 102, 255, 0.2)',
	                 'rgba(255, 159, 64, 0.2)'
	             ],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				y: {
				beginAtZero: true
				}
			}
		}
 });
 
 const obj = JSON.parse('${ListDepartmentsValues}');
 const ctx5 = document.getElementById('DepartmentsValues');
 new Chart(ctx5, {
   type: 'line',
   data: {
     labels: ${labelMonths},
     datasets: [
	{
 		label: obj[0]._description,
 		backgroundColor: "white",
 		borderWidth: 1,
 		borderColor: "#FF0",
 		fill: false,
 		data: obj[0]._values
	},
	{
 		label: obj[1]._description,
 		backgroundColor: "white",
 		borderWidth: 1,
 		borderColor: "#00F",
 		fill: false,
 		data: obj[1]._values
	},
	{
 		label: obj[2]._description,
 		backgroundColor: "white",
 		borderWidth: 1,
 		borderColor: "#080",
 		fill: false,
 		data: obj[2]._values
	},
	{
 		label: obj[3]._description,
 		backgroundColor: "white",
 		borderWidth: 1,
 		borderColor: "#F00",
 		fill: false,
 		data: obj[3]._values
	},
	{
 		label: obj[4]._description,
 		backgroundColor: "white",
 		borderWidth: 1,
 		borderColor: "#864",
 		fill: false,
 		data: obj[4]._values
	}
	]
   },
   options: {
     scales: {
       y: {
         beginAtZero: true
       }
     }
   }
 });
</script>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>
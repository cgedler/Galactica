<%-- 
    Document   : home
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
<title>Home Page</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
$('#myTable').DataTable();
$('#myTable1').DataTable();
$('#myTable2').DataTable();
});
</script>
<%@include file="shared/head.jsp" %>
<div class="container">

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">Bienvenido</h1>
  <p class="lead">...</p>
</div>

</div>
<%@include file="shared/footer.jsp" %>
</body>
</html>

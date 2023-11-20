<%-- 
    Document   : head-link
    Created on : Jul 4, 2023, 2:37:16 PM
    Author     : cge
--%>
<!-- Hojas de estilo -->
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/estilo.css"> -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="icons/font/bootstrap-icons.css">
<link type="text/css" rel="stylesheet" href="css/jquery-ui.v1.13.2.css">
<link type="text/css" rel="stylesheet" href="css/dataTables.jqueryui.min.v1.13.4.css">
<link type="text/css" rel="stylesheet" href="css/fontawesome/css/all.min.css">
<!-- JavaScripts -->
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.v1.13.2.js"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker-es.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.v1.13.4.js"></script>
<script type="text/javascript" src="js/dataTables.jqueryui.min.v1.13.4.js"></script>
<script type="text/javascript" src="js/Chart.min.v2.9.3.js"></script>
<!-- <script type="text/javascript" src="js/bootstrap.min.js"></script>  -->
<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
$('#myTable').DataTable();
$('#myTable1').DataTable();
$('#myTable2').DataTable();
});
$(function(){
$('#accordion').accordion();
// Tabs
$('#tabs').tabs();
// Datepicker
$('#datepicker').datepicker({
    inline: true,
    showWeek: true
});
$('#multidatepicker').datepicker({
    numberOfMonths: 3,
    showButtonPanel: true,
    inline: true
});
});
</script>
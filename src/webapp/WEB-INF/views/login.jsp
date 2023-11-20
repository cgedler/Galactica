<%-- 
    Document   : login
    Created on : Jul 4, 2023, 1:04:02 PM
    Author     : cge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min_v4.6.2.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/icons/font/bootstrap-icons.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slim.min.v3.5.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.min_v4.6.2.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
        <title>Login Page</title>
    </head>
    <body>
	<div class="wrapper fadeInDown">
  	<div id="formContent">
    <!-- Tabs Titles -->
    <!-- Icon -->
    <div class="fadeIn first">
      <c:if test="${param.error!=null}">
			<strong class="formatoError"><i class="bi-exclamation-triangle"></i> Usuario y password incorrectos</strong>
	  </c:if>
	   <c:if test="${param.logout!=null}">
			<strong class="formatoLogout">Has hecho logout</strong>
	  </c:if>
    </div>
    <!-- Login Form -->
    <form:form action="${pageContext.request.contextPath}/user-authentication" method="POST">		
      <input type="text" id="login" class="fadeIn second" name="username" placeholder="login">
      <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
      <input type="submit" class="fadeIn fourth" value="Log In">
	</form:form>    
    <!-- Remind Passowrd -->
    <div id="formFooter">
    	<i class="fa-regular fa-copyright"></i> 2023 Sistema ${systemName}
    </div>
  	</div>
	</div>
</body>
</html>

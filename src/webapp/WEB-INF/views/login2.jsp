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
        <link href="${pageContext.request.contextPath}/css/bootstrap.min_v4.6.2.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/icons/font/bootstrap-icons.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slim.min.v3.5.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.min_v4.6.2.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
        <title>Login Page</title>
    </head>
    <body>
    <form:form class="form-signin" action="${pageContext.request.contextPath}/user-authentication" method="POST">
		<h1 class="h3 mb-3 font-weight-normal">Inicie sesión para continuar</h1>
		<label for="username" class="sr-only"><i class="bi-alarm"></i> Username</label>
		<input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>
		<label for="inputPassword" class="sr-only"><i class="bi-alarm"></i> Password</label>
		<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
		<div class="mb-3">
			<c:if test="${param.error!=null}">
				<strong class="form-error"><i class="bi-exclamation-triangle"></i> Usuario y contraseña incorrectos</strong>
			</c:if>
			<c:if test="${param.logout!=null}">
				<strong class="form-logout">Has hecho logout!!!</strong>
			</c:if>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<p class="mt-5 mb-3 text-muted"><i class="fa-regular fa-copyright"></i> 2023 Sistema ${systemName}</p>
	</form:form>
    </body>
</html>

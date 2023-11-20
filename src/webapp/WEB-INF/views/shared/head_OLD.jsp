<%-- 
    Document   : head
    Created on : Jul 4, 2023, 2:37:16 PM
    Author     : cge
--%>
<header>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
<h5 class="my-0 mr-md-auto font-weight-normal">Sistema ${systemName}</h5>
<a class="p-2 text-dark" href="${pageContext.request.contextPath}/"><i class="bi-house"></i></a>
<nav class="my-2 my-md-0 mr-md-3">
<security:authorize access="hasRole('administrador')">
<div class="dropdown p-2 text-dark">
<a class="p-2 text-dark dropdown-toggle" href="#" id="dropdownMenuLink" data-toggle="dropdown" aria-expanded="false">Administracion <i class="fa-solid fa-user-tie"></i></a>
<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
	<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/list">Departamento</a>
	<div class="dropdown-divider"></div>
	<a class="dropdown-item" href="#">Mis Solicitudes</a>
    <a class="dropdown-item" href=""">Calificar Solicitudes</a>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="#">Mis Tareas</a>
    <a class="dropdown-item" href="#">Mis Calificaciones</a>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="#">Calificar</a>
</div></div>
</security:authorize>
<security:authorize access="hasRole('contador')">
<a class="p-2 text-dark" href="${pageContext.request.contextPath}/contabilidad/list">Contabilidad <i class="bi-calculator"></i></a>
</security:authorize>
<security:authorize access="hasRole('rrhh')">
<a class="p-2 text-dark" href="${pageContext.request.contextPath}/rrhh/list">RRHH <i class="bi-people-fill"></i></a>
</security:authorize>
<security:authorize access="hasRole('sistema')">
<a class="p-2 text-dark" href="${pageContext.request.contextPath}/sistemas/list">Sistemas <i class="bi-terminal"></i></a>
</security:authorize>
<security:authorize access="hasRole('tesorero')">
<a class="p-2 text-dark" href="${pageContext.request.contextPath}/tesoreria/list">Tersoreria <i class="bi-piggy-bank"></i></a>
</security:authorize>
<div class="dropdown p-2 text-dark">
<a class="p-2 text-dark dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false">Configuracion <i class="fa-solid fa-screwdriver-wrench"></i></a>
<div class="dropdown-menu">
	<a class="dropdown-item" href="${pageContext.request.contextPath}/calificaciones/list">Calificaciones</a>
    <a class="dropdown-item" href="${pageContext.request.contextPath}/departamentos/list">Departamentos</a>
    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados/list">Empleados</a>
    <a class="dropdown-item" href="${pageContext.request.contextPath}/prioridades/list">Prioridades</a>
    <a class="dropdown-item" href="${pageContext.request.contextPath}/tareas/list">Tareas</a>
    <div class="dropdown-divider"></div>
    <security:authorize access="hasRole('usuarios')">
    <a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios/list">Usuarios</a>
    </security:authorize>
</div>
</div>
<a class="p-2 text-dark" href="${pageContext.request.contextPath}/help">Ayuda <i class="bi-question"></i></a>
</nav>
<a class="p-3 text-dark" href="#"><security:authentication property="principal.username"/> <i class="bi-person"></i></a>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
<button class="btn btn-outline-primary btn-md btn-block" type="submit" data-toggle="tooltip" data-placement="top" title="Cerrar Sesion">Salir <i class="bi-door-open"></i></button>	
</form:form> 
</div>
</header>
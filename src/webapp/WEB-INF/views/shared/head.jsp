<%-- 
    Document   : head
    Created on : Jul 4, 2023, 2:37:16 PM
    Author     : cge
--%>
<header>
  <nav class="navbar navbar-expand-lg navbar-light bg-light rounded border-bottom">
    <div class="collapse navbar-collapse justify-content-md-center">
      <ul class="navbar-nav">
        <li class="nav-item">
      		<a class="nav-link p-2 text-dark disabled" href="#"><h5>Sistema ${systemName}</h5></a>
        </li>
        <li class="nav-item">
          <a class="nav-link p-2 text-dark" href="${pageContext.request.contextPath}/"><i class="bi-house"></i></a>
        </li>
        <security:authorize access="hasRole('administrador')">
        <li class="nav-item dropdown">
          <a class="nav-item nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Administración <i class="fa-solid fa-user-tie"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown01">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/list">Departamento</a>
            <security:authorize access="hasRole('administradorD')">
			<div class="dropdown-divider"></div>
			<a class="dropdown-item disabled" href="#">Mis Solicitudes:</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/myreqtostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/myreqinprogress"><i class="bi-arrow-right-short"></i> En proceso</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/myreqfinished"><i class="bi-arrow-right-short"></i> Calificar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item disabled" href="#">Mis Tareas:</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/mytaskstostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/mytasksinprogress"><i class="bi-arrow-right-short"></i> Por Cerrar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/administracion/myqualify">Mis Calificaciones</a>
            </security:authorize>
          </div>
        </li>
        </security:authorize>
		<security:authorize access="hasRole('contador')">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Contabilidad <i class="bi-calculator"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown02">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/list">Departamento</a>
            <security:authorize access="hasRole('contadorD')">
			<div class="dropdown-divider"></div>
			<a class="dropdown-item disabled" href="#">Mis Solicitudes:</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/myreqtostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/myreqinprogress"><i class="bi-arrow-right-short"></i> En proceso</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/myreqfinished"><i class="bi-arrow-right-short"></i> Calificar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item disabled" href="#">Mis Tareas:</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/mytaskstostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/mytasksinprogress"><i class="bi-arrow-right-short"></i> Por Cerrar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/contabilidad/myqualify">Mis Calificaciones</a>
            </security:authorize>
          </div>
        </li>
        </security:authorize>
        <security:authorize access="hasRole('gerencia')">
        <li class="nav-item dropdown">
          <a class="nav-item nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gerencia <i class="bi-graph-up-arrow"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown01">
			<a class="dropdown-item disabled" href="#">Mis Solicitudes:</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/gerencia/myreqtostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/gerencia/myreqinprogress"><i class="bi-arrow-right-short"></i> En proceso</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/gerencia/myreqfinished"><i class="bi-arrow-right-short"></i> Calificar</a>
          </div>
        </li>
        </security:authorize>
		<security:authorize access="hasRole('rrhh')">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">RRHH <i class="bi-people-fill"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown03">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/list">Departamento</a>
            <security:authorize access="hasRole('rrhhD')">
			<div class="dropdown-divider"></div>
			<a class="dropdown-item disabled" href="#">Mis Solicitudes:</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/myreqtostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/myreqinprogress"><i class="bi-arrow-right-short"></i> En proceso</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/myreqfinished"><i class="bi-arrow-right-short"></i> Calificar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item disabled" href="#">Mis Tareas:</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/mytaskstostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/mytasksinprogress"><i class="bi-arrow-right-short"></i> Por Cerrar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/rrhh/myqualify">Mis Calificaciones</a>
            </security:authorize>
          </div>
        </li>
        </security:authorize>
		<security:authorize access="hasRole('sistema')">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Sistemas <i class="bi-terminal"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown04">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/list">Departamento</a>
            <security:authorize access="hasRole('sistemaD')">
			<div class="dropdown-divider"></div>
			<a class="dropdown-item disabled" href="#">Mis Solicitudes:</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/myreqtostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/myreqinprogress"><i class="bi-arrow-right-short"></i> En proceso</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/myreqfinished"><i class="bi-arrow-right-short"></i> Calificar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item disabled" href="#">Mis Tareas:</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/mytaskstostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/mytasksinprogress"><i class="bi-arrow-right-short"></i> Por Cerrar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/sistemas/myqualify">Mis Calificaciones</a>
            </security:authorize>
          </div>
        </li>
        </security:authorize>
		<security:authorize access="hasRole('tesorero')">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tersorería <i class="bi-piggy-bank"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown05">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/list">Departamento</a>
            <security:authorize access="hasRole('tesoreroD')">
			<div class="dropdown-divider"></div>
			<a class="dropdown-item disabled" href="#">Mis Solicitudes:</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/myreqtostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/myreqinprogress"><i class="bi-arrow-right-short"></i> En proceso</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/myreqfinished"><i class="bi-arrow-right-short"></i> Calificar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item disabled" href="#">Mis Tareas:</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/mytaskstostart"><i class="bi-arrow-right-short"></i> Por comenzar</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/mytasksinprogress"><i class="bi-arrow-right-short"></i> Por Cerrar</a>
    		<div class="dropdown-divider"></div>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/tersoreria/myqualify">Mis Calificaciones</a>
            </security:authorize>
          </div>
        </li>
        </security:authorize>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown06" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Configuración <i class="fa-solid fa-screwdriver-wrench"></i></a>
          <div class="dropdown-menu" aria-labelledby="dropdown06">
          	<security:authorize access="hasRole('sistema')">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/calificaciones/list">Calificaciones</a>
    		</security:authorize>
    		<security:authorize access="hasRole('sistema')">
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/departamentos/list">Departamentos</a>
    		</security:authorize>
    		<security:authorize access="hasRole('sistema')">
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/empleados/list">Empleados</a>
    		</security:authorize>
    		<security:authorize access="hasRole('sistema')">
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/prioridades/list">Prioridades</a>
    		</security:authorize>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/tareas/list">Tareas</a>
    		<div class="dropdown-divider"></div>
    		<security:authorize access="hasRole('usuarios')">
    		</security:authorize>
    		<security:authorize access="hasRole('sistema')">
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios/list">Usuarios</a>
    		</security:authorize>
          </div>
        </li>      
        <li class="nav-item">
        	<a class="nav-link p-2 text-dark" href="${pageContext.request.contextPath}/help">Ayuda <i class="bi-question"></i></a>
        </li>
        <li class="nav-item">
        	<a class="nav-link p-2 text-dark" href="#"><security:authentication property="principal.username"/> <i class="bi-person"></i></a>
        </li>  
      	<li class="nav-item">
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<button class="btn btn-outline-primary btn-md btn-block" type="submit" data-toggle="tooltip" data-placement="top" title="Cerrar Sesion">Salir <i class="bi-door-open"></i></button>	
			</form:form>
        </li>
      </ul>
    </div>
  </nav>
</header>
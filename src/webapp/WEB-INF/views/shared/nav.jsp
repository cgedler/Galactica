<%-- 
    Document   : header-nav-user-tools
    Created on : Jul 4, 2023, 3:19:21 PM
    Author     : cge
--%>
<ul class="nav flex-column mb-auto">
  <li class="nav-item">
  	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
      	<button class="" type="submit"><svg class="bi"><use xlink:href="#door-closed"/></svg> Salir</button>	
  	</form:form>
  </li>
</ul>
<hr class="my-3">





 <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-body-secondary text-uppercase">
            <span>Saved reports</span>
            <a class="link-secondary" href="#" aria-label="Add a new report">
              <svg class="bi"><use xlink:href="#plus-circle"/></svg>
            </a>
          </h6>
          <ul class="nav flex-column mb-auto">
            <li class="nav-item">
              <a class="nav-link d-flex align-items-center gap-2" href="#">
                <svg class="bi"><use xlink:href="#file-earmark-text"/></svg>
                Current month
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link d-flex align-items-center gap-2" href="#">
                <svg class="bi"><use xlink:href="#file-earmark-text"/></svg>
                Last quarter
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link d-flex align-items-center gap-2" href="#">
                <svg class="bi"><use xlink:href="#file-earmark-text"/></svg>
                Social engagement
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link d-flex align-items-center gap-2" href="#">
                <svg class="bi"><use xlink:href="#file-earmark-text"/></svg>
                Year-end sale
              </a>
            </li>
          </ul>














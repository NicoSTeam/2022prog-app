<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlets.Login"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtProfesorExtra"%>
<%@ page import="java.util.Set"%>
<%! @SuppressWarnings("unchecked") %>
<% 
DtUsuarioExtra u = (DtUsuarioExtra)request.getSession().getAttribute("loggedUser");
if (u!=null){
	%>
	<nav style="margin-bottom: 3em;margin-top: 15px;" class="extraInfoDiv ins-cat-section nav flex-column">
	<h1 class="fs-5" style="color:white;">Acciones</h1>
       <button type="button" id="btn-myUser" class="btn btn-link" style="color: white;" onclick="location.href='<%=request.getContextPath()%>/usuarios?nickname=<%=u.getNickname()%>'">
        	Ir a mi perfil
    	</button>
	<% if (u instanceof DtProfesorExtra){ %>
	   <button type="button" id="btn-altaActDep" class="btn btn-link" style="color: white;" data-bs-toggle="modal" data-bs-target="#altaActModal">
            Alta Actividad Deportiva
      </button>
      <% } %>
      
	  </nav>
<%}%>


<nav class="extraInfoDiv ins-cat-section nav flex-column" style="color: black;border-top-style: solid;margin-top: 15px;margin-left: 1px;">
  <h1 class="fs-5" style="color: white">Instituciones</h1>
  <%
  Set<String> s = (Set<String>)request.getAttribute("stdInstituciones");
  for (String x: s){ %>
  	<a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrI1=<%=x%>"><%=x%></a>
  <%} %>
</nav>
<nav class="extraInfoDiv ins-cat-section nav flex-column mt-5" style="margin-left: 1px;margin-top: 15px;">
   <h1 class="fs-5" style="color: white">Categorías</h1>
  <%
  Set<String> s2 = (Set<String>)request.getAttribute("stdCategorias");
  for (String x: s2){ %>
  	<a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrC1=<%=x%>"><%=x%></a>
  <%} %>
</nav>
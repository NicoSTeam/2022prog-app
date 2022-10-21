<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
    
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtProfesorExtra"%>
<%@ page import="datatypes.DtSocioExtra"%>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtClaseExtra"%>
<%@ page import="datatypes.DtActividadDeportivaExtra"%>
<%@ page import="datatypes.tipoEstado"%>


<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/usuarios.css">

    <title>Entrenamos.uy</title>
  </head>
  <body>
    <jsp:include page="/template/header.jsp"/>

    <div class="container-fluid mt-4">

      <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-2">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        </div>
        
        <!--  Comienzo consulta usuario -->
        
        <% DtUsuarioExtra usrLogged = (DtUsuarioExtra) request.getSession().getAttribute("loggedUser"); %>
        <% DtUsuarioExtra usrProfile = (DtUsuarioExtra) request.getAttribute("datoUsuario"); %>
        
        <% String imagenPerfil = (usrProfile.getImagen() != null) ? new String(usrProfile.getImagen(), "UTF-8"):"default.png"; %>
        
        <div id="user-general" class="col-sm-8">
		<div id="user-superior" class="row ">
			<div class="row" style="background-color: rgba(0, 0, 0, 0.79); border-radius: 10% / 50%; margin-bottom: 15px;">
				<div id="user-img-btn" class="col-auto py-4" >
					<div id="user-imagen" class="">
						<img id="img-perfil" width="180" height="180" alt="<%=usrProfile.getNickname()%>" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=usrProfile.getNickname()%>"></img>
					</div>
					<div>
						<% if (usrLogged != null) { /*Está logueado*/%>
							<% if (usrLogged.getNickname() == usrProfile.getNickname()) { /* Son el mismo usuario */ %>
							<div id="user-editar" class="flex-sm-fill text-sm-center nav-link ">
							<button type="button" class="btn" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);" data-bs-toggle="modal" data-bs-target="#modifModal">
				            	Editar Perfil
				            </button>
							</div>
							<% } else if (!usrLogged.getSeguidosNickname().contains(usrProfile.getNickname())) { /*No sigue al usuario del perfil*/ %>
							<div id="user-seguir" class="flex-sm-fill text-sm-center nav-link ">
								 <a href="<%=request.getContextPath()%>/seguir?nickname=<%=usrProfile.getNickname()%>">
								  <button class="btn-ir btn " type="submit" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);">
					            	Seguir
					              </button>
					              </a>
							</div>
							<% } else{ /* Sigue al usuario*/ %>
							<div id="user-dejarSeguir" class="flex-sm-fill text-sm-center nav-link ">
								<a href="<%=request.getContextPath()%>/dejarDeSeguir?nickname=<%=usrProfile.getNickname()%>">
								<button class="btn" type="submit" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);" >
					            	Dejar de Seguir
					            </button>
					            </a>
							</div>
							<% } %>
						<% } %>
					</div>
					
				</div>
				<div id="user-info" class="col-auto py-3" >
					<% String tipo = (usrProfile instanceof DtProfesorExtra) ? "Profesor":"Socio"; %>
					<p style="color: white;"><strong id="user-nickname"> <%=usrProfile.getNickname()%> </strong> <a id="user-type"><small class="text-muted"> (<%=tipo%>)</small>  </a></p>
					<p style="color: white;"><strong>Nombre: </strong> <%=usrProfile.getNombre()%> <strong>Apellido: </strong> <%=usrProfile.getApellido()%> </p>
					<p style="color: white;"><strong>Correo: </strong> <%=usrProfile.getEmail()%> </p>
					<strong style="color: white;">Seguidores: </strong> <%=usrProfile.getSeguidoresNickname().size()%> <strong style="color: white;">Seguidos: </strong> <%=usrProfile.getSeguidosNickname().size()%>
				</div>
			</div>
		</div>

		<div id="user-inferior" class= "row ">
			<div id="user-navegador" class="row">
				<nav class="nav nav-pills flex-column flex-sm-row">
					<button id="nav-perfil" type="button" onclick="cambioNavegador('user-consultaPerfil', 'nav-perfil')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-radius-0 active" style= "color: white ;background-color: rgba(0, 0, 0, 0.79);">Perfil</button>
					<% if (usrProfile instanceof DtSocioExtra) {%>
						<button id="nav-inscripciones" type="button" onclick="cambioNavegador('user-consultaInscripciones', 'nav-inscripciones')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " style= "color: white ;background-color: rgba(0, 0, 0, 0.79);">Inscripciones</button>
					<% } else { %>
						<button id="nav-clasesDictadas" type="button" onclick="cambioNavegador('user-consultaClases', 'nav-clasesDictadas')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " style= "color: white ;background-color: rgba(0, 0, 0, 0.79);">Clases Dictadas</button>
					<% } %>
					<button id="nav-seguidores" type="button" onclick="cambioNavegador('user-seguidores', 'nav-seguidores')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 "  style= "color: white ;background-color: rgba(0, 0, 0, 0.79);">Seguidores</button>
					<button id="nav-seguidos" type="button" onclick="cambioNavegador('user-seguidos', 'nav-seguidos')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " style= "color: white ;background-color: rgba(0, 0, 0, 0.79);" >Seguidos</button>
					<% if ((usrProfile instanceof DtSocioExtra) && (usrLogged != null) && usrLogged.getNickname() == usrProfile.getNickname()) {	/*Socio mirando su propio perfil*/ %>	
						<button id="nav-cuponeras" type="button" onclick="cambioNavegador('user-cuponeras', 'nav-cuponeras')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " style= "color: white ;background-color: rgba(0, 0, 0, 0.79);" >Cuponeras</button>
					<% } %>
					<% if (usrProfile instanceof DtProfesorExtra) { %>
						<button id="nav-actAsoc" type="button" onclick="cambioNavegador('user-consultaAD', 'nav-actAsoc')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " style= "color: white ;background-color: rgba(0, 0, 0, 0.79);" >Actividades Asociadas</button>
						<button id="nav-actIng"  type="button" onclick="cambioNavegador('user-consultaADI', 'nav-actIng')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " style= "color: white ;background-color: rgba(0, 0, 0, 0.79);" >Actividades Ingresadas</button>
					<% } %>
				</nav>
			</div>
			<div id="user-consultaPerfil" class="col-sm-11 border" style="background-color: rgba(0, 0, 0, 0.79);">
              <div class="mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0" style="color: white;"><strong>Nombre completo:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary" style="color: white;">
                      <%=usrProfile.getNombre()%> <%=usrProfile.getApellido()%>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0" style="color: white;"><strong>Correo electrónico:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary" style="color: white;">
                      <%=usrProfile.getEmail()%>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0" style="color: white;"><strong>Fecha de nacimiento:<strong></strong></strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary" style="color: white;">
                      <%=usrProfile.getFechaNacimiento().toFechaHora()%>
                    </div>
                  </div>
                  
                  <div>
                  	  <% if (usrProfile instanceof DtProfesorExtra) { %>
                  	  <br>
	                  <div class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0" style="color: white;"><strong>Institución Asociada:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary" style="color: white;">
	                      <%= ((DtProfesorExtra)usrProfile).getNombreInstitucion() %>
	                      <br>
	                    </div>
	                  </div>
	                  <br>
	                  <div  class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0" style="color: white;"><strong>Descripción: </strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary" style="color: white;">
	                      <%= ((DtProfesorExtra)usrProfile).getDescripcion() %>
	                      <br>
	                    </div>
	                  </div>
	                  <br>
	                  <div  class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0" style="color: white;"><strong>Biografía:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary" style="color: white;">
	                      <%= ((DtProfesorExtra)usrProfile).getBiografia() %>
	                      <br>
	                    </div>
	                  </div>
	                  <br>
	                  <div  class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0" style="color: white;"><strong>Website:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary" style="color: white;">
	                    	<a style="color: white;" href="<%= ((DtProfesorExtra)usrProfile).getLink() %>">
							  <%= ((DtProfesorExtra)usrProfile).getLink() %>
	                    	</a>
	                    </div>
	                  </div>
	            	<% } %>
	            </div>
               </div>
              </div>
				</div>
				<% if (usrProfile instanceof DtProfesorExtra) {	/*Clases dictadas*/ %>
				<div id= "user-consultaClases" style="background-color: rgba(0, 0, 0, 0.79);" class=" border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						
						<% List<?> clases = (List<?>) request.getAttribute("clasesDictadas"); %>
						
						<% for ( Object cl: clases ) { %>
						<% String imagenClase = (((DtClaseExtra)cl).getImgName() != null) ? ((DtClaseExtra)cl).getImgName().toString():"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExtra)cl).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExtra)cl).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtClaseExtra)cl).getNombre()%></b></a>
								<% for ( Map.Entry<String, Set<String>> xy: (((DtProfesorExtra)usrProfile).getClasesxActividades()).entrySet() ) { %>
									<% if (xy.getValue().contains(((DtClaseExtra)cl).getNombre())) { %>
										<small class="text-muted">(<%=xy.getKey()%>)</small>
									<% break; } %>
								<% } %>
							 </li>
						 <% } %>
						
						
					</ul>
				</div>
				<% } else {		/*Inscripciones*/%>
				
				<div id= "user-consultaInscripciones" style="background-color: rgba(0, 0, 0, 0.79);" class=" border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
					
						<% List<?> clases = (List<?>) request.getAttribute("clasesInscripto"); %>
						<% for ( Object cl: clases ) { %>
						<% String imagenClase = (((DtClaseExtra)cl).getImgName() != null) ? ((DtClaseExtra)cl).getImgName().toString():"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExtra)cl).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExtra)cl).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtClaseExtra)cl).getNombre()%></b></a>
								<% for ( Map.Entry<String, Set<String>> xy: (((DtSocioExtra)usrProfile).getAguadeUwu()).entrySet() ) { %>
									<% if (xy.getValue().contains(((DtClaseExtra)cl).getNombre())) { %>
										<small class="text-muted">(<%=xy.getKey()%>)</small>
									<% break; } %>
								<% } %>
							 </li>
						 <% } %>
						 
					</ul>
				</div>
				<% }%>
				<div id= "user-seguidores" style="background-color: rgba(0, 0, 0, 0.79);"  class=" border card-body">
				<% {  //Truco para que no se guarde la lista en memoria%>
					<% List<?> seguidores = (List<?>) request.getAttribute("seguidores"); %>
					<ul id="listaActividadesActDep" class="list-group list-group-horizontal-sm">
						<% for ( Object u: seguidores ) { %>
						<% String imagenSeguidor = (((DtUsuarioExtra)u).getImagen() != null) ? new String(((DtUsuarioExtra)u).getImagen(), "UTF-8"):"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/usuarios?nickname=<%=((DtUsuarioExtra)u).getNickname()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=((DtUsuarioExtra)u).getNickname()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtUsuarioExtra)u).getNickname()%></b></a>
							 </li>
						 <% } %>
					</ul>
				<% } %>
				</div>
				<div id= "user-seguidos" style="background-color: rgba(0, 0, 0, 0.79);"  class=" border card-body" >
				<% {  //Truco para que no se guarde la lista en memoria%>
					<% List<?> seguidos = (List<?>) request.getAttribute("seguidos"); %>
					<ul id="listaActividadesActDep" class="list-group list-group-horizontal-sm">
						<% for ( Object u: seguidos ) { %>
						<% String imagenSeguido = (((DtUsuarioExtra)u).getImagen() != null) ? new String(((DtUsuarioExtra)u).getImagen(), "UTF-8"):"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/usuarios?nickname=<%=((DtUsuarioExtra)u).getNickname()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=((DtUsuarioExtra)u).getNickname()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtUsuarioExtra)u).getNickname()%></b></a>
							 </li>
						 <% } %>
					</ul>
				</div>
				<% } %>
				<% if (usrProfile instanceof DtSocioExtra && (usrLogged != null) && usrProfile.getNickname() == usrLogged.getNickname()) {	/* Socio viendo su propio perfil */ %>
				<% List<?> cups = (List<?>) request.getAttribute("cuponeras"); %>
				<div id= "user-cuponeras" class="col-sm-9 border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for ( Object cup: cups ) { %>
						<% String imagenCup = (((DtCuponera)cup).getNombre() != null) ? ((DtCuponera)cup).getNombre():"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/cuponeras?cuponera=<%=((DtCuponera)cup).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=cup&id=<%=((DtCuponera)cup).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtCuponera)cup).getNombre()%></b></a>
							 </li>
						 <% } %>
						</ul>
				</div>
				<% } else if (usrProfile instanceof DtProfesorExtra) {%>
				
				<% List<?> dtadas = (List<?>) request.getAttribute("actividadesAsociadas"); %>
				
				<div id= "user-consultaAD" style="background-color: rgba(0, 0, 0, 0.79);" class="col-sm-9 border card-body" >
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for ( Object ad: dtadas ) { %>
						<% String imagenAct = (((DtActividadDeportivaExtra)ad).getImgName() != null) ? ((DtActividadDeportivaExtra)ad).getImgName().toString():"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtActividadDeportivaExtra)ad).getNombre()%></b></a>
							 </li>
						 <% } %>
						</ul>
				</div>
				<div id= "user-consultaADI" style="background-color: rgba(0, 0, 0, 0.79);"  class="col-sm-9 border card-body" >
				
				<% List<?> dtad = (List<?>) request.getAttribute("actividadesIngresadas"); %>
				
				<% if ((usrLogged != null) && (usrProfile.getNickname() == usrLogged.getNickname())) { /* Profesor viendo su perfil*/%>
				<h5 style="color: white;">Actividades Aceptadas</h5>
				<% } %>
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for ( Object ad: dtad ) { %>
							<% if (((DtActividadDeportivaExtra)ad).getEstado() == tipoEstado.aceptada) { %>
							<% String imagenAct = (((DtActividadDeportivaExtra)ad).getImgName() != null) ? ((DtActividadDeportivaExtra)ad).getImgName().toString():"default.png"; %>
							<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtActividadDeportivaExtra)ad).getNombre()%></b></a>
							 </li>
							<% } %>
						 <% } %>
					</ul>
					<% if ((usrLogged != null) && (usrProfile.getNickname() == usrLogged.getNickname())) { %>
					<br>
					<h5 style="color: white;">Actividades Ingresadas</h5>
					  <ul id="listaActividadesClases" style="background-color: rgba(0, 0, 0, 0.79);"  class="list-group list-group-horizontal">
					  	<% for ( Object ad: dtad ) { %>
					  		<% if (((DtActividadDeportivaExtra)ad).getEstado() == tipoEstado.ingresada) { %>
					  		<% String imagenAct = (((DtActividadDeportivaExtra)ad).getImgName() != null) ? ((DtActividadDeportivaExtra)ad).getImgName().toString():"default.png"; %>
							<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtActividadDeportivaExtra)ad).getNombre()%></b></a>
							 </li>
							<% } %>
						<% } %>
					  </ul>
					<br>
					<h5 style="color: white;">Actividades Rechazadas</h5>
					  <ul id="listaActividadesClases" style="background-color: rgba(0, 0, 0, 0.79);"  class="list-group list-group-horizontal">
					  	<% for ( Object ad: dtad ) { %>
							<% if (((DtActividadDeportivaExtra)ad).getEstado() == tipoEstado.rechazada) { %>
							<% String imagenAct = (((DtActividadDeportivaExtra)ad).getImgName() != null) ? ((DtActividadDeportivaExtra)ad).getImgName().toString():"default.png"; %>
							<li class="list-group-item container border card-body elementoLista">
							 <a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="link-dark">
							 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExtra)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								<b><%=((DtActividadDeportivaExtra)ad).getNombre()%></b></a>
							 </li>
							 <% } %>
						 <% } %>
					  </ul>
					<% } %>
				</div>
				<% } %>
		</div>
	</div>
	
	<!--MODALS-->
    <div class="modal fade" id="modifModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="color: white;">
                <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                <h2 class="fw-bold mb-0" >Modificar datos</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" style="color: white;">
                <form id="formulario-modif" action="<%=request.getContextPath()%>/modificarDatosUsuario?nickname=<%=usrProfile.getNickname()%>" method="POST" onsubmit="return modif()" enctype="multipart/form-data" accept-charset="UTF-8">
                   <h5 style="color: white;">Cambiar contraseña <small class="text-muted" >(Opcional)</small></h5>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" name="pas1" id="pas1">
                        <label for="pas1">Contraseña</label>                  
                    </div>
                    <div class="form-floating mb-3" style="color: white;">
                        <input type="password" class="form-control rounded-4" name="pas2" id="pas2">
                        <label for="pas2">Confirmar Contraseña</label>                  
                    </div>
                    <h5>Sobre ti</h5>
                    <div id="nombreCompletoDiv" class="row form-floating mb-3">
                        <div id="divNombre" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="nomm" name="nomm" value="<%=usrProfile.getNombre()%>">
                                <label for="nomm">Nombre</label>           
                            </div>      
                        </div>
                        <div id="divApellido" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="ape" name="ape" value="<%=usrProfile.getApellido()%>">
                                <label for="ape">Apellido</label>           
                            </div>                           
                        </div>             
                    </div>
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control rounded-4" id="nac" name="nac" value="<%=usrProfile.getFechaNacimiento().toWebFecha()%>">
                        <label for="nac">Fecha de nacimiento</label>     
                    </div>
                  	<div class="mb-3">
						  <label for="formFile" class="form-label">Cambiar foto de Perfil</label>
						  <input class="form-control" type="file" name="formFile" id="formFile" accept=".jpg, .jpeg, .png, .webp, .gif, .tiff">
					</div>
					<% if (usrProfile instanceof DtProfesorExtra) { %>
						<div id="modifdescDiv" class="form-group form-floating mb-3">
	                        <textarea class="form-control" id="desc" name="desc" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ><%=((DtProfesorExtra)usrProfile).getDescripcion()%>
	                        </textarea>
	                        <label for="desc">Descripción</label>     
	                    </div>
	                    <div id="newbioDiv" class="form-group form-floating mb-3">
	                        <textarea class="form-control" id="bio" name="bio" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ><%=((DtProfesorExtra)usrProfile).getBiografia()%></textarea>
	                        <label for="desc">Biografía <i style="font-size:0.7rem;"> (opcional)</i></label>     
	                    </div>
	                    <div id="newwebDiv" class="form-floating mb-3">
	                        <input type="text" class="form-control rounded-4" name="webs" id="webs"  value="<%=((DtProfesorExtra)usrProfile).getLink()%>">
	                        <label for="webs">Sitio web <i style="font-size:0.7rem;"> (opcional)</i></label>
	                    </div>
                  	<% } %>
                    <button class="w-100 mb-2 btn btn-lg rounded-4" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);"type="submit" >Confirmar</button>
                </form>
            </div>
            <div class="modal-footer">
                <hr class="my-6">
                <div>
                    <i>Entrenamos.uy - modificar datos de usuario</i>
                </div>
            </div>
        </div>
      </div>
    </div>
    
        <!-- Fin consulta usuario -->

        <div class="col-2 ps-1 ps-sm-2">
           <jsp:include page="/template/stdRightSection.jsp"/>
        </div>
      </div>
    </div>

    <!--FOOTER-->
    <jsp:include page="/template/footer.jsp"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/scripts/usuarios.js"></script>
	
</body>
</html>

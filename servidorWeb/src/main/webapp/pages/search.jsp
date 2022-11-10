<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtProfesorExtra"%>
<%@ page import="datatypes.DtClaseExtra"%>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtActividadDeportivaExtra"%>
<%@ page import="datatypes.DtSocioExtra"%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/search.css">
</head>
<body>
	<!--BARRA DE NAVEGACION-->
    <jsp:include page="/template/header.jsp"/>

    <!--CUERPO DE LA PAGINA WEB-->
    <div class="container-fluid mt-4">
		<div class="row mx-3 mx-md-5">
        	<div class="ins-cat col-2">
          		<jsp:include page="/template/stdLeftSection.jsp"/>
        	</div>
			<!-- Comienzo Listado usuario -->		
			<%List<List<?>> listaDeListas = new ArrayList<List<?>>();
			listaDeListas.add((List<?>) request.getAttribute("actividades"));
			listaDeListas.add((List<?>) request.getAttribute("clases"));
			listaDeListas.add((List<?>) request.getAttribute("cuponeras"));
			listaDeListas.add((List<?>) request.getAttribute("usuarios"));
			
			// Guarda el link para la nueva request de los formularios:
			String link = "/search?campoTexto=" + ((String) request.getAttribute("searchText"));
			if (request.getAttribute("actividades") != null)
				link += "&actividades=yes";
			if (request.getAttribute("clases") != null)
				link += "&clases=yes";
			if (request.getAttribute("cuponeras") != null)
				link += "&cuponeras=yes";
			if (request.getAttribute("usuarios") != null)
				link += "&usuarios=yes";
			
			// Auxiliares para el orden y filtro:
			String orden = (String) request.getAttribute("orden");
			String mostrarTodas = (String) request.getAttribute("mostrarTodas");
			Set<?> listaInstituciones = (Set<?>) request.getAttribute("instituciones");
			Set<?> listaCategorias = (Set<?>) request.getAttribute("categorias");
			Set<?> filtro = null;
			%>
			
			<% /* Comienza la magia */ %>
		
			<div class="col-md-8">
				<div class="container-fluid">
					<div class="row px-3">
						<div class="col">
							<%int cantidadResultados = 0;
							for (List<?> lista : listaDeListas) {
								if (lista != null) { 
									cantidadResultados += lista.size();
								}
							} %>
				        	<h4><strong><%=cantidadResultados%> resultados.</strong></h4>
				        </div>
				        <%String linkAndFiltro = link;
		        		  filtro = (Set<?>) request.getAttribute("filtroInsti");
		        		  int count = 1;
		        		  for (Object x : filtro) {
		        			  linkAndFiltro += "&fltrI" + count + "=" + (String)x;
		        			  count++;
		        		  }
		        		  filtro = (Set<?>) request.getAttribute("filtroCat");
		        		  count = 1;
		        		  for (Object x : filtro) {
		        			  linkAndFiltro += "&fltrC" + count + "=" + (String)x;
		        			  count++;
		        		  } %>
		        		<% if (request.getAttribute("clases") != null) {%>
				        <div class="col-md-auto">
				        		Mostrar clases:
				        </div>
				        <div class="col-md-auto">
				        	<div id="divMostrar" class="mb-auto">
				        		<form name="estadoMostrar" action="<%=request.getContextPath() + linkAndFiltro%>&sort=<%=orden%>" method="POST">
			                    	<select id="mostrar" name="mostrar" class="form-select" data-live-search="true" onchange="this.form.submit()">
			                            <option value="yes" 
			                            	<%if (mostrarTodas.equals("yes")) {%> selected="selected" <% } %>>Todas</option>
			                            <option value="no"
			                            	<%if (mostrarTodas.equals("no")) {%> selected="selected" <% } %>>Disponibles</option>
			                        </select>
			                	</form>
		                    </div>
				        </div>
				        <% } %>
				        <div class="col-md-auto">
				        		Ordenar por:
				        </div>
				        <div class="col-md-auto">
				        	<div id="divOrder" class="mb-auto">
				        		<form name="orden" action="<%=request.getContextPath() + linkAndFiltro%>&mostrar=<%=mostrarTodas%>" method="POST">
			                    	<select id="sort" name="sort" class="form-select" data-live-search="true" onchange="this.form.submit()">
			                            <option value="alfaDesc" 
			                            	<%if (orden.equals("alfaDesc")) {%> selected="selected" <% } %>>Alfabeticamente (A-Z a-z)</option>
			                            <option value="alfaAsc"
			                            	<%if (orden.equals("alfaAsc")) {%> selected="selected" <% } %>>Alfabeticamente (z-a Z-A)</option>
			                            <%if (request.getAttribute("usuarios") == null) {%>	
			                            <option value="fechaDesc"
			                            	<%if (orden.equals("fechaDesc")) {%> selected="selected" <% } %>>Fecha de registro (descendente)</option>
			                            <option value="fechaAsc"
			                            	<%if (orden.equals("fechaAsc")) {%> selected="selected" <% } %>>Fecha de registro (ascendente)</option>
			                            <% } %>
			                        </select>
			                	</form>
		                    </div>
				        </div>
				        <%if ((request.getAttribute("actividades") != null) || (request.getAttribute("cuponeras") != null)) {%>
				        <div class="col-md-auto">
				        	<button type="button" id="btn-filtro" class="btn-ir btn mb-auto" style="border-color: white; color:white; background-color: rgba(0, 0, 0, 0.79);" data-bs-toggle="modal" data-bs-target="#filtroModal">
			                	Filtrar
			              	</button>
				        </div>
				        <% } %>
					</div>
		        </div>
				<%for (List<?> lista : listaDeListas) {
					if ((lista != null) && (!lista.isEmpty())) {
						String listadoDeX = (lista.get(0) instanceof DtActividadDeportivaExtra) ? "Actividades Deportivas" :
						(lista.get(0) instanceof DtClaseExtra) ? "Clases" :
						(lista.get(0) instanceof DtCuponera) ? "Cuponeras" :
						(lista.get(0) instanceof DtUsuarioExtra) ? "Usuarios" : new String();%>
		        		<div class="cabezal-usuarios py-1 ">
		            		<h2><strong>Listado de <%=listadoDeX%>: </strong></h2>
		            	</div>
		    			<div class="container-fluid">
					  		<div class="">
					    		<div class="row px-3">
					    			<%for (int i = 0; i < 4; i++) { %>
								    	<div class="col-md-3">
								    	<%for (int j = i; j < lista.size(); j = j + 4) {%>
							    			<div class="card img-de-lista">
							    				<% /* En caso de desconocer el operador  _?_:_ googlear conditional operator */
							     				Object obj = lista.get(j);
							     						
									          	String tituloCarta = 
									          	(obj instanceof DtCuponera)? ((DtCuponera)obj).getNombre():
									          	(obj instanceof DtClaseExtra)? ((DtClaseExtra)obj).getNombre():
									          	(obj instanceof DtActividadDeportivaExtra)? ((DtActividadDeportivaExtra)obj).getNombre():
									          	((DtUsuarioExtra)obj).getNickname();
									          	
							     				String descripcionCarta = 
											  	(obj instanceof DtCuponera)? ((DtCuponera)obj).getDescripcion():
											  	(obj instanceof DtClaseExtra)? ((DtClaseExtra)obj).getURL():	/* Clase no tiene descripcion :( */
											  	(obj instanceof DtActividadDeportivaExtra)? ((DtActividadDeportivaExtra)obj).getDescripcion():
											  	(obj instanceof DtProfesorExtra)? ((DtProfesorExtra)obj).getDescripcion():
											  	"Socio/a.";	/* Si no se cumple lo anterior se asume socio */ 
											  	
											  	String dirUrl = 
									          	(obj instanceof DtCuponera)? "/cuponeras?cuponera=" + ((DtCuponera)obj).getNombre():
									          	(obj instanceof DtClaseExtra)? "/clases?clase=" + ((DtClaseExtra)obj).getNombre():
									          	(obj instanceof DtActividadDeportivaExtra)? "/actividades?actividad=" + ((DtActividadDeportivaExtra)obj).getNombre():
									          	"/usuarios?nickname=" +((DtUsuarioExtra)obj).getNickname();
									          	
									          	String dirImagen = 
									          	(obj instanceof DtCuponera)? "/api/content?c=cup&id=" +((DtCuponera)obj).getNombre():
									          	(obj instanceof DtClaseExtra)? "/api/content?c=cla&id="+((DtClaseExtra)obj).getNombre():
									          	(obj instanceof DtActividadDeportivaExtra)? "/api/content?c=act&id="+((DtActividadDeportivaExtra)obj).getNombre():
									          	"/api/content?c=usu&id=" + (new String(((DtUsuarioExtra)obj).getNickname()));%>
									          	
									        	<a class="hyperlink-img" href="<%=request.getContextPath() + dirUrl%>">
								        		<img class="card-img-top" src="<%=request.getContextPath() + dirImagen%>" alt="default">
								        		<div class="card-body">											  
										          	<h5 class="card-title"><strong><%=tituloCarta%></strong></h5>
										          	<p class="card-text"><%=descripcionCarta%></p>
										          	<% if(obj instanceof DtActividadDeportivaExtra && request.getSession().getAttribute("loggedUser") instanceof DtSocioExtra){ 
										          			DtSocioExtra ss = (DtSocioExtra) request.getSession().getAttribute("loggedUser");
										          			if(ss.getActividadesFavoritas().contains(tituloCarta)){ %>
				        											<button style="background-color: #ed2553; border-color: #ed2553;" class="btn btn-primary"><i class="fa-heart fa"></i><span class="text"></span>&nbsp;<span class="nobold">(<span class="count"><%=((DtActividadDeportivaExtra)obj).getFavoritos()%></span>)</span></button>
										          			<%} else{%>
																	<button id="favorite"  style="background-color: #ed2553; border-color: #ed2553;" class="btn btn-primary"><i class="fa-heart far"></i><span class="text"></span>&nbsp;<span class="nobold">(<span class="count"><%=((DtActividadDeportivaExtra)obj).getFavoritos()%></span>)</span></button>
										          			<%} %>
										          	<%}%>
										     	</div>
												</a>
											</div>
										<% } %>
										</div>
									<% } %>
								</div>
							</div>
						</div>
					<% } %>
				<% } %>		    
		    </div>
		    <!-- Fin listado -->
		    <div class="col-2 ps-1 ps-sm-2">
		       	<jsp:include page="/template/stdRightSection.jsp"/>
		    </div>
		</div>
	</div>
    <!--FOOTER-->
   	<jsp:include page="/template/footer.jsp"/>
   	
   	<!--MODAL FILTRO-->
    <div class="modal fade" id="filtroModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header" style="color: white">
	                <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
	                <h2 class="fw-bold mb-0">Entrenamos.uy</h2>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body" style="color: white">
	                <form id="form-filtro" action="<%=request.getContextPath() + link%>&sort=<%=orden%>&mostrar=<%=mostrarTodas%>" method="POST" accept-charset="utf-8"> 
	                	<h5 class="fw-bold mb-0">Instituciones:</h5>
	                	<%int counter = 0;
	                	filtro = (Set<?>) request.getAttribute("filtroInsti");
	                	for (Object obj : listaInstituciones) {
	                		counter++;%>
	                    <div class="form-check float-left">
	                        <input type="checkbox" id="fltrI<%=counter%>" name="fltrI<%=counter%>" value="<%=(String) obj%>" 
	                        	<%if (filtro.contains((String) obj)) {%> checked <% } %>>
	                        <label for="fltrI<%=counter%>"><%=(String) obj%></label>
	                    </div>
	                    <% } %>
	                    <h5 class="fw-bold mb-0">Categorias:</h5>
	                    <%counter = 0;
	                	filtro = (Set<?>) request.getAttribute("filtroCat");
	                    for (Object obj : listaCategorias) {
	                    	counter++;%>
	                    <div class="form-check float-left">
	                        <input type="checkbox" id="fltrC<%=counter%>" name="fltrC<%=counter%>" value="<%=(String) obj%>"
	                        	<%if (filtro.contains((String) obj)) {%> checked <% } %>>
	                        <label for="fltrC<%=counter%>"><%=(String) obj%></label>
	                    </div>
	                    <% } %>
	                    <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Filtrar</button>
	                </form>
	            </div>
	        </div>
	    </div>
    </div>
</body>
</html>
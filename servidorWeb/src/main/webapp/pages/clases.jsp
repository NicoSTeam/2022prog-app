<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Map"%>
<%@ page import="datatypes.DtClaseExtra"%>
<%@ page import="datatypes.DtSocioExtra"%>
<%@ page import="datatypes.DtProfesorExtra"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtFechaHora"%>
<%@ page import="datatypes.DtActividadDeportivaExtra"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/clases.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/stars.css">
</head>
<body>
	<jsp:include page="/template/header.jsp"/>
	<div class="container-fluid mt-4">
		<%  DtClaseExtra datosClase = (DtClaseExtra) request.getAttribute("clase");
			String nombreActividad = (String) request.getAttribute("actividad");
			String nombreInstitucion = (String) request.getAttribute("institucion");
			boolean esSocio = (boolean) request.getAttribute("esSocio");
			boolean estaInscripto = (boolean) request.getAttribute("estaInscripto");
			boolean estaCaducada = (boolean) request.getAttribute("estaCaducada");
			boolean estaLlena = (boolean) request.getAttribute("estaLlena");
			boolean actfin = (boolean) request.getAttribute("actfin");
			DtUsuarioExtra loggedUser = (DtUsuarioExtra) request.getSession().getAttribute("loggedUser");
			boolean noCumpleMinimo = (datosClase.getMinSocios() > datosClase.getNickAlumnos().size());
			%>
		<div class="row mx-3 mx-md-5">
        	<div class="ins-cat col-2">
          		<jsp:include page="/template/stdLeftSection.jsp"/>
        	</div>
        	<div id="user-general" class="col-sm-7">
		      	<div id="user-superior" class="row " style="background-color: rgba(0, 0, 0, 0.79); border-radius: 10% / 10%; margin-bottom: 15px;">
            		<div class="col-3 py-3">
			    		<div id="mainImgDiv" class="">
			    			<% if (actfin) { %>
			    			<img alt="imagenClase" id="mainImgDiv" src="<%=request.getContextPath()%>/assets/images/default/cla_default.png">
			    			<% } else { %>
				    		<img alt="imagenClase" id="mainImgDiv" src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=datosClase.getNombre()%>">
				    		<% } %>
			    		</div>
           			</div>
            		<div class="col-9 py-3" style="color: white">
				      	<div id="user-info" class="row">
                			<p><strong id="user-nickname"> <%=datosClase.getNombre()%> </strong>
				      		<a id="user-type" href="<%=request.getContextPath()%>/actividades?actividad=<%=nombreActividad%>"> (<%=nombreActividad%>) </a>
				      		</p>
				      	</div>
				      	<%if(!actfin) {%>
              			<div id="creatorDiv" class="row" style="color: white">
                			<div class="col-auto">
                 			<h4><strong>Dictada por:</strong></h4>
                			</div>
              				<div class="col-auto">
                 				<img alt="Qries"  src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=datosClase.getNicknameProfesor()%>" class="vertical-align-middle imagenSeleccionable">
                 			</div>
                 			<div class="col-auto">
                 				<a class="clase color-blue" href="<%=request.getContextPath()%>/usuarios?nickname=<%=datosClase.getNicknameProfesor()%>"><%=datosClase.getNicknameProfesor()%></a>
              				</div>
              				<%if (esSocio) { %>
	              				<div class="col-auto">
	              					<% if (estaInscripto || estaCaducada || estaLlena) {%>
	              					<button id="botonQueNoSirveXD" type="button" class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" disabled>
					                  <%   if (estaInscripto) {%>Inscripto
					                  <% } else if (estaCaducada) {%>
					                  <%	 if (noCumpleMinimo) {%>Clase cancelada
					                  <%	 } else {%>Clase finalizada
					                  <% 	 }
					                     } else {%>Clase llena
					                  <% } %>
					                </button>
	              					<%} else {%>
					                <button id="botonInsc" type="button" class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit" data-bs-toggle="modal" data-bs-target="#inscModal">
					                  Inscribirse
					                </button>
					                <%}%>
	              				</div>
	              			<% } %>
             			</div>
             			<%} %>
          			</div>
		      	</div>
		      	<div id="user-inferior" class= "row card-body mb-3" style="background-color: rgba(0, 0, 0, 0.79); border-radius: 10% / 10%; margin-bottom: 15px;">
	           		<div class="row">
	             			<div class="col-sm-3" style="color: white">
	                 			<h6 class="mb-0"><strong>Fecha y Hora de Inicio:</strong></h6>
	             			</div>
	             			<div class="col-sm-9 text-secondary">
	               				<%=datosClase.getFechaClase().toFechaHora()%>
	             			</div>
	           		</div>
		           	<div class="row">
		              	<div class="col-sm-3" style="color: white"> 
		                  	<h6 class="mb-0"><strong>Cupos minimos:</strong></h6>
		              	</div>
		              	<div class="col-sm-9 text-secondary">
		                  	<%=datosClase.getMinSocios()%> socios.
		              	</div>
		            </div>
		            <div class="row">
		              	<div class="col-sm-3" style="color: white">
		                  	<h6 class="mb-0"><strong>Cupos maximos:</strong></h6>
		              	</div>
		              	<div class="col-sm-9 text-secondary">
		                  	<%=datosClase.getMaxSocios()%> socios.
		              	</div>
		            </div>
		            <div class="row">
		              	<div class="col-sm-3" style="color: white">
		                  	<h6 class="mb-0"><strong>URL:</strong></h6>
		              	</div>
		              	<div class="col-sm-9 text-secondary">
		                	<%=datosClase.getURL()%>
		              	</div>
		            </div>
	            	<div class="row">
	              		<div class="col-sm-3" style="color: white"> 
	                  		<h6 class="mb-0"><strong>Fecha de registro:</strong></h6>
	              		</div>
	              		<div class="col-sm-9 text-secondary">
	                		<%=datosClase.getFechaRegistro().toFecha()%>
	              		</div>
	            	</div>
	            	<%if (!actfin && datosClase.getFechaClase().esMenor(new DtFechaHora())){ 
		            	int s=0,n=0;
		            	for(Map.Entry<String,Integer> x: datosClase.getCalificaciones().entrySet()){
		            		n++;
		            		s=s+x.getValue();
		            	}
		            	float promedio= (n>0)? s/n : 0;
		            	%>
		            	<div class="row">
		              		<div class="col-sm-3" style="color: white">
		                  		<h6 class="mb-0"><strong>Valoración promedio:</strong></h6>
		              		</div>
		              		<div class="col-sm-9 text-secondary">
		              			<div class="Stars col" style="--rating: <%=String.valueOf(promedio)%> ;" aria-label=" <%=String.valueOf(promedio)%>">
		              			</div>
		              			<div class="col">
		                		<%=String.valueOf(promedio)%>   (<%=datosClase.getCalificaciones().size() %> valoraci<%=(datosClase.getCalificaciones().size()>1) ? "ones":"ón"%>)
		              		</div>
		              		</div>
		            	</div>
	            	<%} %>
	            	<%if (datosClase.getPremio()!=null && !actfin) { %>
	            	<div class="mt-4" style="color: white">
	            	<h5>Premio</h5>
	            	<div class="row">
	              		<div class="col-sm-3">
	                  		<h6 class="mb-0"><strong>Descripción:</strong></h6>
	              		</div>
	              		<div class="col-sm-9 text-secondary">
	                		<%=datosClase.getPremio().getDescripcion()%>
	              		</div>
	            	</div>
	            	<div class="row">
	              		<div class="col-sm-3">
	                  		<h6 class="mb-0"><strong>Cantidad:</strong></h6>
	              		</div>
	              		<div class="col-sm-9 text-secondary">
	                		<%=datosClase.getPremio().getCantidad()%>
	              		</div>
	            	</div>
	            	<div class="row">
	            		<%if (datosClase.getPremio().getFechaSorteo().equals(new DtFechaHora(0,0,0,0,0,0))){ %>
	                  		<h6 class="mt-2"><strong style="color: yellow;" >El sorteo aún no se ha realizado!</strong></h6>
	            		<% }else{ %>
	              		<div class="col-sm-3">
	                  		<h6 class="mb-0"><strong>Fecha sorteo:</strong></h6>
	              		</div>
	              		<div class="col-sm-9 text-secondary">
	                		<%=datosClase.getPremio().getFechaSorteo().toFechaHora()%>
	              		</div>
	              		<%} %>
	            	</div>
	            	</div>
	            	<%} %>
	            	
	                <% if (!actfin){
	    			DtFechaHora fechaf = new DtFechaHora();
	    			fechaf.setMinutos(fechaf.getMinutos()-((DtActividadDeportivaExtra)request.getAttribute("actDT")).getDuracionMinutos());
	                if (datosClase.getPremio() != null && datosClase.getNickAlumnos().size() > 0 && loggedUser instanceof DtProfesorExtra && ((DtProfesorExtra)loggedUser).getNickname().equals(datosClase.getNicknameProfesor()) &&
	                		datosClase.getFechaClase().esMenor(fechaf) && (datosClase.getPremio().getFechaSorteo()==null || datosClase.getPremio().getFechaSorteo().equals(new DtFechaHora(0,0,0,0,0,0))) 
	                		&& !noCumpleMinimo) { %>
	                	<br>
		                <button class="w-100 mb-2 btn btn-outline-primary btn-lg rounded-4  mt-4" type="submit" data-bs-toggle="modal" data-bs-target="#sorteoModal" style="color: yellow; border-color: yellow;" >
		                   Realizar Sorteo
		                </button>
		            <%} %>
		            <%if (loggedUser instanceof DtSocioExtra && estaInscripto && datosClase.getFechaClase().esMenor(new DtFechaHora()) && datosClase.getNickAlumnos().contains(loggedUser.getNickname())) {
		            	String valLink = request.getContextPath()+"/valorar?usu="+loggedUser.getNickname()+"&ins="+nombreInstitucion+"&act="+nombreActividad+"&cla="+datosClase.getNombre();%>
						<div class="mt-3" data-valoracion="<%=(datosClase.getCalificaciones().containsKey(loggedUser.getNickname())) ? datosClase.getCalificaciones().get(loggedUser.getNickname()): "-1" %>" id="Valoraciones">
							<h6><strong>Su valoración de <%=datosClase.getNicknameProfesor()%> en esta clase: </strong></h6>
						    <div class="rating-group">
						        <input disabled checked class="rating__input rating__input--none" name="rating3" id="rating3-none" value="0" type="radio">
						        
						        <label aria-label="1 star" class="rating__label" for="rating3-1"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
						        <input onclick="location.href='<%=valLink%>&val=1';" class="rating__input" name="rating3" id="rating3-1" value="1" type="radio">
						        
						        <label aria-label="2 stars" class="rating__label" for="rating3-2"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
						       
						        <input onclick="location.href='<%=valLink%>&val=2';" class="rating__input" name="rating3" id="rating3-2" value="2" type="radio">
						       
						        <label aria-label="3 stars" class="rating__label" for="rating3-3"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
						       
						        <input onclick="location.href='<%=valLink%>&val=3';" class="rating__input" name="rating3" id="rating3-3" value="3" type="radio">
						      
						        <label aria-label="4 stars" class="rating__label" for="rating3-4"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
						    
						        <input onclick="location.href='<%=valLink%>&val=4';" class="rating__input" name="rating3" id="rating3-4" value="4" type="radio">
						 
						        <label aria-label="5 stars" class="rating__label" for="rating3-5"><i class="rating__icon rating__icon--star fa fa-star"></i></label>

						        <input onclick="location.href='<%=valLink%>&val=5';" class="rating__input" name="rating3" id="rating3-5" value="5" type="radio">
						    </div>
						</div>
		            <%} }
		            if (actfin){
		            %>
					<div class="alert alert-info mt-4" role="alert">
					  Esta clase pertenece a una actividad <b>FINALIZADA</b>. Usted está visualizando los registros la actividad finalizada disponibles en la base de datos de Entrenamos.uy <i class="fas fa-database"></i>
					</div>
		            <%} else if (estaCaducada) {
		            		if (noCumpleMinimo) { %>
		            			<div class="alert alert-warning mt-4" role="alert">
		  					  	Esta clase fue <b>CANCELADA</b>. Ya que no se llego al minimo de socios antes de su dictado. <i class="fas fa-exclamation-circle"></i>
		  						</div>
		            		<%} else { %>
		            			<div class="alert alert-primary mt-4" role="alert">
		  					  	Esta clase ya fue <b>DICTADA</b> con exito. <i class="fas fa-check-circle"></i>
		  						</div>
		            		<%}
		            }
			            if ((datosClase.getUrlVideo() != null) && !datosClase.getUrlVideo().isEmpty()) {
			            	String u = datosClase.getUrlVideo();
			            	u=u.replace("watch?v=", "embed/");

		            %>
		            <h4 style="color: white">Video</h4>
		            <div class="mt-3 aspectRatioVideo">
		            	
		            	<iframe width="560" height="315" src="<%=u%>" frameborder="0" allowfullscreen></iframe>
		            </div>
		            <% } %>
				</div>
			</div>
		    <div class="col-sm-3 ps-1 ps-sm-3">
		    	<div id= "user-consultaInscriptos" class="cuadritosCos row">
		    		<%  List<String> nickAlumnos = datosClase.getNickAlumnos();%>
		      		<h5>Usuarios Inscriptos (<%=nickAlumnos.size()%>)</h5>
		      		<ul id="listaInscriptos" class="py-3">
		      			<%  
		      			String actfinparam = "";
		      			if(actfin){
		      				actfinparam = "&db=1";
		      			}
		      			for (String alumno : nickAlumnos) { %>
		      				<li class="container border card-body elementoLista">
		      					<%if(!actfin){ %>
				           		<img alt="Default"  src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=alumno%>" class="vertical-align-middle imagenSeleccionable">
				           		<%}else{ %>
				           		<img alt="Default" src="<%=request.getContextPath()%>/assets/images/default/usu_default.png" class="vertical-align-middle imagenSeleccionable">
				           		<%} %>
				           		<a class="clase color-blue" href="<%=request.getContextPath()%>/usuarios?nickname=<%=alumno+actfinparam%>"><%=alumno%></a>
				           		<% // saque lo siguiente del if, ya que solo el profesor veia los ganadores en la clase: loggedUser instanceof DtProfesorExtra && ((DtProfesorExtra)loggedUser).getNickname().equals(datosClase.getNicknameProfesor())
				           		if (datosClase.getPremio() != null && datosClase.getNickAlumnos().size() > 0 && datosClase.getPremio().getGanadores()!=null && datosClase.getPremio().getGanadores().contains(alumno)) { %>
				        		<img alt="ganador"  src="<%=request.getContextPath()%>/assets/images/misc/winner64.png" class="">
				        		<%} %>
				        	</li>
		      			<%  } %>
		      		</ul>
		    	</div>
			    <jsp:include page="/template/stdRightSection.jsp"/>
		  	</div>
		</div>
	</div>
	
	<!--MODALS-->
	<%if ((!estaCaducada) && (!estaLlena) && (esSocio) && (!estaInscripto)) { %>
    <div class="modal fade" id="inscModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content" style="color: white">
              <div class="modal-header">
                  <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                  <h2 class="fw-bold mb-0">Inscripción a Clase</h2>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <form id="form-insc" action="<%=request.getContextPath()%>/api/registroClase?clase=<%=datosClase.getNombre()%>&actividad=<%=nombreActividad%>&institucion=<%=nombreInstitucion%>" 
                  	method="POST">
                  	<%Set<?> cupDisponibles = (Set<?>) request.getAttribute("cupDisponibles");
                  	  String precio = (String) request.getAttribute("precio");%>
                    <div class="form-floating mb-3">
                      <h5>Pago Asociado</h5>
                      <div id="genRadio" class="form-check float-left">
                          <input class="form-check-input" type="radio" name="tipoInsc" id="radioGen" value="general" checked>
                          <label class="form-check-label" for="radioGen">
                          General ($<%=precio%>)
                          </label>
                      </div>
                      <div id="cupRadio" class="form-check float-left">
                          <input class="form-check-input" type="radio" name="tipoInsc" id="radioCup" value="cuponera"
                          	<%if (cupDisponibles.size() == 0) { %> disabled <% } %>>
                          <label class="form-check-label" for="radioCup">
                          Cuponera
                          </label>
                      </div>
                    </div>
                    <div id="cupdiv" class="form-floating mb-3">
                      <select id="cups" name="cups" class="form-select" data-live-search="true"
                          	<%if (cupDisponibles.size() == 0) { %> disabled <% } %>>
                      	  <%for (Object x : cupDisponibles) {%>
                          <option value="<%=(String)x%>" data-tokens="<%=(String)x%>"><%=(String)x%></option>
                          <% } %>
                      </select>
                      <label for="cups">Cuponera a utilizar</label>                               
                    </div>
                    <button id="confInscbtn" class="w-100 mb-2 btn btn-lg rounded-4" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);" type="submit">Confirmar</button>
                  </form>
              </div>
              <div class="modal-footer">
                  <hr class="my-6">
                  <div>
                      <i>Registro de dictado de clase - entrenamos.uy</i>
                  </div>
              </div>
          </div>
      </div>
    </div>	
	<%} %>
	
    <div class="modal fade" id="sorteoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                  <h2 class="fw-bold mb-0" style="color: white">Realizar sorteo</h2>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <h3 style="color: white">Confirmar Sorteo 🎫 </h3>
                  <p style="color: white">¿Está seguro que desea realizar el sorteo con los socios inscriptos a esta clase? Solo puede realizarlo una única vez.</p>
				 <a href='<%=request.getContextPath()%>/realizarSorteo?cla=<%=datosClase.getNombre()%>' >
				  <button class="btn-ir btn" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);"  type="submit" >
	            	Confirmar
	              </button>
	              </a>
              </div>
              <div class="modal-footer">
                  <hr class="my-6">
                  <div>
                      <i style="color: white">Sortear Premios Socios en clases dictadas - entrenamos.uy</i>
                  </div>
              </div>
          </div>
      </div>
    </div>	
    
    
	<jsp:include page="/template/footer.jsp"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/scripts/clases.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set"%>
<%@ page import="datatypes.DtActividadDeportivaExtra"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtProfesorExtra"%>
<%@ page import="datatypes.DtSocioExtra"%>
<%@ page import="datatypes.DtClaseExtra"%>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtFechaHora"%>
<%@ page import="datatypes.tipoEstado"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/consultaActividadDeportiva.css">
</head>
<body>
<jsp:include page="/template/header.jsp"/>
<div class="container-fluid mt-4">
	<%  DtActividadDeportivaExtra datosActDep = (DtActividadDeportivaExtra) request.getAttribute("actDep");
		boolean esSocio = (boolean) request.getAttribute("esSocio");
		boolean finalizable = (boolean) request.getAttribute("finalizable");
		DtUsuarioExtra datosCreador = (DtUsuarioExtra) request.getAttribute("datosCreador");
		String institucion = (String) request.getAttribute("institucion");
		Set<?> datosClases = (Set<?>) request.getAttribute("datosClases");
		Set<?> datosCuponeras = (Set<?>) request.getAttribute("datosCuponeras");
		DtUsuarioExtra loggedUser = (DtUsuarioExtra) request.getSession().getAttribute("loggedUser");
		boolean finalizada = (datosActDep.getEstado() == tipoEstado.finalizada) ? true : false;
		%>
	<div class="row mx-3 mx-md-5">
       	<div class="ins-cat col-2">
         		<jsp:include page="/template/stdLeftSection.jsp"/>
         		<jsp:include page="/template/stdRightSection.jsp"/>
       	</div>
       	<div class="col-sm-7" id="actd-general" >
	        <div id="actd-superior" class="row " style="background-color: rgba(0, 0, 0, 0.79); border-radius: 10% / 10%; margin-bottom: 15px;">
		        <div class="col-3 py-3" >
				    <div id="mainImgDiv" class="">
				    	<% if (!finalizada) { %>
					    <img alt="imagenActividad" id="mainImgDiv" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=datosActDep.getNombre()%>">
				    	<% } else { %>
				    	<img alt="imagenActividad" id="mainImgDiv" src="<%=request.getContextPath()%>/assets/images/default/act_default.png">
				    	<% } %>
				    </div>
                   </div>
               	<div class="col-9 py-3">
			        <div id="user-info" class="row">
			        	<div class="col sm-4" style="color: white">
			        		<p><strong id="user-nickname"><%=datosActDep.getNombre()%></strong></p>
			        	</div>
			        	<div class="col mt-4 sm-8">
			        		<% if(loggedUser instanceof DtSocioExtra && !finalizada) {%>
			        		<a href="<%=request.getContextPath()%>/favoritear?usu=<%=loggedUser.getNickname()%>&act=<%=datosActDep.getNombre()%>">
			        		<% if(((DtSocioExtra) loggedUser).getActividadesFavoritas().contains(datosActDep.getNombre())) {%>
			        		<button id="favorite" style="background-color: #ed2553; border-color: #ed2553;" class="btn btn-primary"><i class="fa-heart fas"></i><span class="text"> Desmarcar como Favorita</span>&nbsp;<span class="nobold">(<span class="count"><%=datosActDep.getFavoritos()%></span>)</span></button>
			        		<% }else{%>
							<button id="favorite"  style="background-color: #ed2553; border-color: #ed2553;" class="btn btn-primary"><i class="fa-heart far"></i><span class="text"> Marcar como Favorita</span>&nbsp;<span class="nobold">(<span class="count"><%=datosActDep.getFavoritos()%></span>)</span></button>
			        		<% }}%>
			        		</a>
			        	</div>
			        </div>
                       <div id="creatorDiv" class="row">
                           <div class="col-auto" style="color: white">
                               <h4><strong>Ingresada por:</strong></h4>
                           </div>
                           	<div class="col-auto" style="color: white">
                           		<%if(!datosActDep.getEstado().equals(tipoEstado.finalizada)){ %>
                               	<img id="actDepCreator" alt="imagenUsuario" id="img-perfil" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=(new String(((DtUsuarioExtra)datosCreador).getNickname()))%>">
                           		<%} else{ %>
                           		<img id="actDepCreator" alt="imagenUsuario" id="img-perfil" src="<%=request.getContextPath()%>/assets/images/default/usu_default.png">
                           		<%} %>
                           	</div>
                           	<div class="col-auto" style="color: white">
                           		<%if (!datosCreador.getNickname().equals("Administrador")) { %>
                               		<a id="user-type" href="<%=request.getContextPath()%>/usuarios?nickname=<%=datosCreador.getNickname() + ( (finalizada) ? "&db=1":"")%>"><%=datosActDep.getCreador()%></a>
                               	<%} else { %>
                               		<%=datosActDep.getCreador()%>
                               	<%} %>
                               </div>
                             </div>
                       </div>
                   </div>
	        <div id="actd-inferior" class= "row card-body mb-3" style="background-color: rgba(0, 0, 0, 0.79); border-radius: 10% / 10%; margin-bottom: 15px;">
                   <div class="row">
                   	   <% if (!finalizada) { %>
                       <div class="col-sm-3" style="color: white">
                           <h6 class="mb-0"><strong>Institución asociada:</strong></h6>
                       </div>
                       <div class="col-sm-9 text-secondary">
                           <%=institucion%>
                       </div>
                       <% } %>
                       <div class="col-sm-3" style="color: white">
                           <h6 class="mb-0"><strong>Descripción:</strong></h6>
                       </div>
                       <div class="col-sm-9 text-secondary">
                           <%=datosActDep.getDescripcion()%>
                       </div>
                   </div>
                   <div class="row">
                       <div class="col-sm-3" style="color: white">
                           <h6 class="mb-0"><strong>Duración:</strong></h6>
                       </div>
                       <div class="col-sm-9 text-secondary">
                           <%=datosActDep.getDuracionMinutos()%> minutos
                       </div>
                   </div>
                   <div class="row">
                       <div class="col-sm-3" style="color: white">
                           <h6 class="mb-0"><strong>Costo:</strong></h6>
                       </div>
                       <div class="col-sm-9 text-secondary">
                           $<%=datosActDep.getCosto()%>
                       </div>
                   </div>
                   <div class="row">
                       <div class="col-sm-3" style="color: white">
                           <h6 class="mb-0"><strong>Fecha de Alta:</strong></h6>
                       </div>
                       <div class="col-sm-9 text-secondary">
                           <%=datosActDep.getFechaRegistro().toFecha()%>
                       </div>
                   </div>
                   <%if (datosActDep.getEstado()==tipoEstado.rechazada) {%>
				<div class="alert alert-danger mt-4" role="alert">
				  Esta actividad fue <b>RECHAZADA</b>. Si usted es el autor de dicha actividad, contacte con nuestro asesor para obtener más información.
				</div>
				<%} else if(datosActDep.getEstado()==tipoEstado.ingresada) {%>
				<div class="alert alert-warning mt-4" role="alert">
				  Esta actividad está en estado <b>INGRESADA</b>. Pongase en contacto con un asesor para obtener más información.
				</div>
				<%} else if(datosActDep.getEstado()==tipoEstado.finalizada) {%>
				<div class="alert alert-info mt-4" role="alert">
				  Esta actividad fue <b>FINALIZADA</b>. Usted está visualizando los registros la actividad finalizada disponibles en la base de datos de Entrenamos.uy <i class="fas fa-database"></i>
				</div>
				<%} %>
	        </div>
               <br>
               <%if (loggedUser instanceof DtProfesorExtra && ((DtProfesorExtra)loggedUser).getNombreInstitucion().equals(institucion) &&
               		datosActDep.getEstado()==tipoEstado.aceptada) { %>
                <button class="w-100 mb-2 btn btn-lg rounded-4" type="submit" data-bs-toggle="modal" data-bs-target="#altaClaseModal" style="background-color: rgba(0, 0, 0, 0.79); color: white; border-color: green"  >
                    Dar de alta una clase para esta actividad
                </button>
            <%} %>
               <%if (loggedUser instanceof DtProfesorExtra && ((DtProfesorExtra)loggedUser).getNickname().equals(datosCreador.getNickname()) &&
               		datosActDep.getEstado()==tipoEstado.aceptada && finalizable) { %>
               	<br>
                <button class="w-100 mb-2 btn btn-outline-danger btn-lg rounded-4  mt-4" type="submit" data-bs-toggle="modal" data-bs-target="#finalizarActividadModal" style="background-color: rgba(0, 0, 0, 0.79); color: white" >
                    Finalizar Actividad
                </button>
            <%} %>
           </div>
           <div class="col-sm-3 ps-1 ps-sm-2">
               <div class="cuadritosCos row">
                   <h5>Clases</h5>
				<ul id="listaActividades" class=" py-3">
					<%if (!finalizada) {
					  for (Object dtClase : datosClases) { %>
						<li class="container border card-body elementoLista" > 
                           	<img alt="imagenClases"  src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExtra)dtClase).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
                           	<a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExtra)dtClase).getNombre()%>" class="clase color-blue"><%=((DtClaseExtra)dtClase).getNombre()%></a>
                       	</li> 
					<% } 
					} else { 
					  for (String nombreCl : datosActDep.getClases()) { %>
					  <li class="container border card-body elementoLista" > 
                           <a href="<%=request.getContextPath()%>/clases?clase=<%=nombreCl%>" class="clase color-blue"><%=nombreCl%></a>
                      </li>
					<% }
					} %>              
				</ul>
               </div>
               <% if (!finalizada) { %>
               <div class="cuadritosCos row">
                   <h5>Cuponeras</h5>
				<ul id="listaActividades" class=" py-3">
					<%for (Object datosCup : datosCuponeras) { %>
						<li class="container border card-body elementoLista"> 
                           	<img alt="imagenCuponera"  src="<%=request.getContextPath()%>/api/content?c=cup&id=<%=((DtCuponera)datosCup).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
                           	<a href="<%=request.getContextPath()%>/cuponeras?cuponera=<%=((DtCuponera)datosCup).getNombre()%>" class="clase color-blue"><%=((DtCuponera)datosCup).getNombre()%></a>
                       	</li>
                       <% } %>                
				</ul>
               </div>
               <div class="cuadritosCos row">
                   <h5>Categorías</h5>
				<ul id="listaActividades" class=" py-3">
					<%Set<String> categorias = datosActDep.getCategorias();%>
					<%for (String cat : categorias) { %>
						<li class="container border card-body elementoLista"> 
                           	<a href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrC1=<%=cat%>" class="clase color-blue"><%=cat%></a>
                       	</li>
                       <% } %>               
				</ul>
               </div>
               <% } %>
           </div>
       </div>
</div>

<jsp:include page="/template/footer.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/scripts/consultaActividadDeportiva.js"></script>

<!--MODAL ALTA CLASE-->
   <div class="modal fade" id="altaClaseModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
       <div class="modal-dialog" >
           <div class="modal-content" style="background-color: rgba(0, 0, 0, 0.79);" style="color: white">
               <div class="modal-header"  style="color: white">
                   <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                   <h2 class="fw-bold mb-0">Alta de Clase</h2>
                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body" >
                   <form method="POST" id="formulario-clase" action="<%=request.getContextPath()%>/altaClase" onsubmit="return altaCL()" enctype="multipart/form-data" accept-charset="UTF-8">
                   	<input name="nombreActDep" value="<%=datosActDep.getNombre()%>" type="hidden">
                   	<input name="institucionAsociada" value="<%=institucion%>" type="hidden">                  	
                       <div class="form-floating mb-3">
                           <input type="text" class="form-control rounded-4" name="nombreClase" id="nomclase" placeholder="" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLnombreClase")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLnombreClase") : "")%>">
                           <label for="nomclase">Nombre</label>
                       </div>
                       <div class="form-floating mb-3">
                           <input type="date" class="form-control rounded-4" name="fechaInicio" id="fechaIni" placeholder="" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLfechaInicio")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLfechaInicio") : "")%>">
                           <label for="fechaIni">Fecha de inicio</label>                  
                       </div>
                       <div id="nombreCompletoDiv" class="row form-floating mb-3">
                           <div id="divNombre" class="col-6 form-check float-left">
                           	<div id="institDiv" class="form-floating mb-2 pe-2">
		                        <select name="hora" id="horaInicio" class="form-select" data-live-search="true">
									     <option <%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLhora")!=null) ? "" : "selected")%>>-</option>
										 <%for(int i = 0; i<24; i++) { %>
									         <option <%=( (request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLhora")!=null && request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLhora").equals(String.valueOf(i)) ) ? "selected" : "")%> value="<%=i%>"><%=i%></option>
									    <%} %>
		                        </select>
		                        <label for="horaInicio">Hora Inicio</label>                            
		                    </div>
                           </div>
                           <div id="divNombre" class="col-6 form-check float-left">
                           	<div id="institDiv" class="form-floating mb-2 me-2 pe-1 ms-2">
		                        <select name="minutos" id="minutosInicio" class="form-select" data-live-search="true">
									     <option <%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLminutos")!=null) ? "" : "selected")%>>-</option>
										 <%for(int i = 0; i<60; i++) { %>
									         <option <%=( (request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLminutos")!=null && request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLminutos").equals(String.valueOf(i)) ) ? "selected" : "")%> value="<%=i%>"><%=i%></option>
									    <%} %>
		                        </select>
		                        <label for="minutosInicio">Minutos Inicio</label>                               
		                    </div>
                           </div>           
                       </div>
                       <h6  style="color: white">Cupos de Inscripción</h6>
                       <div id="nombreCompletoDiv" class="row form-floating mb-3">
                           <div id="divNombre" class="col-6 form-check float-left">
                               <div class="form-floating mb-2">
                                   <input type="number" class="form-control rounded-4" name="cantMin" id="minax" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLcantMin")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLcantMin") : "")%>">
                                   <label for="minax">Mín</label>           
                               </div>      
                           </div>
                           <div id="divApellido" class="col-6 form-check float-left">
                               <div class="form-floating mb-2">
                                   <input type="number" class="form-control rounded-4" name="cantMax" id="asd" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLcantMax")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLcantMax") : "")%>">
                                   <label for="asd">Máx</label>           
                               </div>                           
                           </div>             
                       </div>
                       <div class="form-floating mb-3">
                           <input type="text" class="form-control rounded-4" name="url" id="urlin" placeholder="" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLurl")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLurl") : "")%>">
                           <label for="urlin">URL</label>                  
                       </div>
                       <div class="form-floating mb-3">
                           <input type="text" class="form-control rounded-4" name="urlVideo" id="urlVideo" placeholder="" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLurlVideo")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLurlVideo") : "")%>">
                           <label for="urlVideo">URL Video <i style="font-size:0.7rem;"> (opcional)</i></label>                  
                       </div>
                       <h6  style="color: white">Imagen asociada <i style="font-size:0.7rem;"> (opcional)</i></h6>
                       <div id="imgPick" class="mb-3">
                           <input type="file" class="form-control" name="img" id="customFil2e" accept=".jpg, .jpeg, .png, .webp, .gif, .tiff">
                 
                       </div>
                       <h6  style="color: white">Premio <i style="font-size:0.7rem;"> (opcional)</i></h6>
                    <div id="descPremioDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" id="descPremio" name="descPremio" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ><%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLdescPremio")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLdescPremio") : "")%></textarea>
                        <label for="desc">Descripción</label>     
                    </div>
                       <div class="form-floating mb-3">
                           <input type="number" class="form-control rounded-4" name="cantPremios" id="cantPremios" placeholder="" value="<%=((request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLcantPremios")!=null) ? request.getSession().getAttribute("ctx"+datosActDep.getNombre()+"CLcantPremios") : "")%>">
                           <label for="cantPremios">Cantidad de premios a sortear</label>                  
                       </div>
                       <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Confirmar Registro</button>
                   </form>
               </div>
               <div class="modal-footer">
                   <hr class="my-6" >
                   <div>
                       <i  style="color: white">Entrenamos.uy - Alta Dictado de Clase</i>
                   </div>
               </div>
           </div>
       </div>
   </div>

<% if (finalizable){ %>
   <div class="modal fade" id="finalizarActividadModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
     <div class="modal-dialog">
         <div class="modal-content" style="color: white">
             <div class="modal-header">
                 <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                 <h2 class="fw-bold mb-0">Inscripción a Clase</h2>
                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
             </div>
             <div class="modal-body">
                 <h3>Por favor confirme ⚠ </h3>
                 <p>¿Está seguro que desea finalizar está actividad? Una vez finalizada ya no podrá crear nuevas clases de esta actividad.</p>
			 <a href='<%=request.getContextPath()%>/finalizarActividad?act=<%=datosActDep.getNombre()%>' >
			  <button class="btn-ir btn" style="border-color: black; color:black; background-color: rgba(255, 255, 255, 0.79);" type="submit" >
            	Confirmar
              </button>
              </a>
             </div>
             <div class="modal-footer">
                 <hr class="my-6">
                 <div>
                     <i>Finalizar Actividad - entrenamos.uy</i>
                 </div>
             </div>
         </div>
     </div>
   </div>
<%} %>
</body>
</html>
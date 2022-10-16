package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datatypes.DtActividadDeportiva;
import datatypes.DtFechaHora;
import datatypes.DtProfesorExtra;
import datatypes.tipoEstado;
import excepciones.ActividadDeportivaException;
import logica.IcontroladorActividadDeportiva;
import logica.Fabrica;
import tools.Parametrizer;

@MultipartConfig
@WebServlet ("/alta_ad")
public class AltaAD extends HttpServlet {
	private static IcontroladorActividadDeportiva IADC;
	private static final long serialVersionUID = 1L;
    public AltaAD() {
        super();
        IADC = Fabrica.getInstance().obtenerIcontroladorActDeportiva();
    }
    protected void processRequest(HttpServletRequest r,  HttpServletResponse response) throws ServletException,  IOException {
    	r.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	String ru = r.getParameter("miurl");
        DtProfesorExtra p = (DtProfesorExtra) r.getSession().getAttribute("loggedUser");
        Set<String> cats = new HashSet<>();
        if (rp(r, "catAD") != null)
	        for (String c: rp(r, "catAD").split("[, ]")) {
	        	cats.add(c);
	        }
        String filename=null;
        if (r.getPart("imgAD")!=null && r.getPart("imgAD").getSize()>0) {
        	Part filePart = r.getPart("imgAD");
    		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
    		String ext = s[s.length-1];
    		filename=rp(r, "nombreAD")+"."+ext;
        }
    	int duracion = Integer.parseInt(rp(r, "durAD").trim());
    	float costo = Float.parseFloat(rp(r, "costoAD").trim());
        DtActividadDeportiva datosAD = new DtActividadDeportiva(rp(r, "nombreAD"), rp(r, "descAD"), duracion, costo, 
        		new DtFechaHora(), new HashSet<String>(), tipoEstado.ingresada, p.getNickname(), filename); //cat    
        try {
	        if (IADC.ingresarDatosActividadDep(p.getNombreInstitucion(),  datosAD)) {
	    		ru=Parametrizer.remParam(ru,  "e",  "4");
	    		ru=Parametrizer.addParam(ru,  "e",  "5");
	    		if (r.getPart("imgAD")!=null && r.getPart("imgAD").getSize()>0) {
		        	Part filePart = r.getPart("imgAD");
		        	InputStream fileContent = filePart.getInputStream();
	        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
	        		String ext = s[s.length-1];
		        	String path = r.getServletContext().getRealPath("/assets/images/activities/"+rp(r, "nombreAD")+"."+ext);
		        	Files.copy(fileContent,  Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		           //System.out.println( r.getServletContext().getRealPath("/assets/images/users/"+rp(r, "nombreAD")+"."+ext));
		        }
			} else {
				ru=Parametrizer.remParam(ru,  "e",  "5");
				ru=Parametrizer.addParam(ru,  "e",  "4");
	        }
        }catch(ActividadDeportivaException e) {
        	e.getMessage();
        	ru=Parametrizer.remParam(ru,  "e",  "5");
        	ru=Parametrizer.addParam(ru,  "e",  "4");
        }catch(Exception e) {
		   e.printStackTrace();
		   ru=Parametrizer.remParam(ru,  "e",  "5");
		   ru=Parametrizer.addParam(ru,  "e",  "4");
	   }
       response.sendRedirect(ru);
    }
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}
}
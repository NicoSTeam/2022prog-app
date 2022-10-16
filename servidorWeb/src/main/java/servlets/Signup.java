package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datatypes.DtFechaHora;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import models.GestorWeb;
import tools.Parametrizer;
import logica.IcontroladorUsuario;
import logica.Fabrica;

@MultipartConfig
@WebServlet ("/signup")
public class Signup extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IcontroladorUsuario IUC;
    public Signup() {
        super();
        IUC = Fabrica.getInstance().obtenerIcontroladorUsuario();
    }
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	String r = request.getParameter("miurl");
        try {
        	request.setCharacterEncoding("utf-8");
        	response.setCharacterEncoding("utf-8");
        	byte [] bimgn = null;
        	if (request.getPart("imgPerfil")!=null && request.getPart("imgPerfil").getSize()>0) {
        		Part filePart = request.getPart("imgPerfil");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
        		bimgn = (rp(request, "nickk")+"."+ext).getBytes();
        	}
        	String [] d = rp(request, "nac").split("-");
	        if (rp(request, "tipoU").equals("0")) {
	        	if (IUC.ingresarDatosUsuario(new DtSocio(rp(request, "nickk"), rp(request, "nomm"), rp(request, "ape"), 
	        			rp(request, "emaill"),  rp(request, "pas1"),  new DtFechaHora(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), 0, 0, 0),  bimgn))!=0) {
	        		r=Parametrizer.addParam(r,  "e",  "2");
	        		response.sendRedirect(r);
	        		return;
	        	}
	        }
	        else {
	        	if (IUC.ingresarDatosUsuario(new DtProfesor(rp(request, "nickk"), rp(request, "nomm"), rp(request, "ape"), 
	        			rp(request, "emaill"),  rp(request, "pas1"),  new DtFechaHora(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), 0, 0, 0), 
	        			rp(request, "instit"),  rp(request, "descRU"),  rp(request, "bioRU") , rp(request, "websRU"), 
	        			bimgn))!=0) {
	        		r=Parametrizer.addParam(r,  "e",  "2");
	        		response.sendRedirect(r);
	        		return;
	        	}
	        }
	        if (request.getPart("imgPerfil")!=null && request.getPart("imgPerfil").getSize()>0) {
	        	Part filePart = request.getPart("imgPerfil");
	        	InputStream fileContent = filePart.getInputStream();
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
	        	String path = request.getServletContext().getRealPath("/assets/images/users/"+rp(request, "nickk")+"."+ext);
	        	Files.copy(fileContent,  Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	           //System.out.println( request.getServletContext().getRealPath("/assets/images/users/"+rp(request, "nickk")+"."+ext));
	        }
	        r=Parametrizer.remParam(r,  "e", "1");
	        r=Parametrizer.remParam(r,  "e", "2");
	        r=Parametrizer.addParam(r,  "e",  "3");
	        request.getSession().setAttribute("loggedUser", GestorWeb.buscarUsuario(rp(request, "nickk")));
        } catch(Exception e) {
        	e.printStackTrace();
        	r=Parametrizer.addParam(r,  "e",  "2");
        }
        response.sendRedirect(r);
    }
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        processRequest(request,  response);
	}
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}
}

package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Fabrica;
import tools.Parametrizer;
import excepciones.NoExisteCuponeraException;
import datatypes.DtCuponera;

@WebServlet("/cuponeras")
public class Cuponeras extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public Cuponeras() {
    	super();
    }

    protected void processRequest(HttpServletRequest request,  
    		HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	String cuponera = request.getParameter("cuponera");
    	DtCuponera DatosCup = null;
    	try {
    		DatosCup = buscarCuponera(cuponera);
		} catch(NoExisteCuponeraException ex) {
			request.setAttribute("cuponera",  null);
			response.sendRedirect(request.getContextPath()+"/pages/404.jsp");
			return;
		}
    	if (DatosCup != null) {
    		request.setAttribute("cuponera",  DatosCup);
        	request.getRequestDispatcher("/pages/cuponeras.jsp").forward(request,  response);
    	}
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}

	private DtCuponera buscarCuponera(String cc) throws NoExisteCuponeraException  {
		DtCuponera cup = Fabrica.getInstance().obtenerIcontroladorCuponera().seleccionarCuponera(cc);
		return cup;
	}
	
}

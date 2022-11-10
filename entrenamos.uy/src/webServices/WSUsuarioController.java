package webServices;




import java.util.Properties;
import java.util.Set;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import datatypes.DtProfesorExtra;
import datatypes.DtSocioExtra;
import datatypes.DtUsuarioExtra;
import datatypesWS.DtCapsula;
import datatypesWS.DtFechaWS;
import datatypesWS.DtProfesorWS;
import datatypesWS.DtSocioWS;
import datatypesWS.DtUsuarioWS;
import excepciones.ClaseException;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.IcontroladorUsuario;
import logica.Fabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSUsuarioController {

	private Endpoint endpoint = null;
	private Fabrica factory = Fabrica.getInstance();
	private IcontroladorUsuario IUC = factory.obtenerIcontroladorUsuario();
	
	public WSUsuarioController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("usuarioController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }
    

    @WebMethod
	public DtCapsula<String[]> obtenerInstituciones(){
    	Set<String> r = IUC.obtenerInstituciones();
    	String [] x = new String[r.size()];
    	int i=0;
    	for(String s : r) {
    		x[i++]=s;
    	}
    	return new DtCapsula<String[]>(x);
	}
    
    @WebMethod
    public DtCapsula<String[]> obtenerUsuarios() {
    	Set<String> r = IUC.obtenerUsuario();
    	String [] x = new String[r.size()];
    	int i=0;
    	for(String s : r) {
    		x[i++]=s;
    	}
    	return new DtCapsula<String[]>(x);
    }

    @WebMethod
	public int ingresarDatosUsuario(DtUsuarioWS datoUser)  throws InstitucionException {
		return IUC.ingresarDatosUsuario(datoUser.adapt());
	}
	
    @WebMethod
	public DtUsuarioWS seleccionarUsuario(String userNick) throws UsuarioNoExisteException{
		DtUsuarioExtra d = IUC.seleccionarUsuario(userNick);
		if(d instanceof DtSocioExtra)
			return new DtSocioWS((DtSocioExtra) d);
		else
			return new DtProfesorWS((DtProfesorExtra) d);
	}

    @WebMethod
	public DtUsuarioWS seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException{
		DtUsuarioExtra d = IUC.seleccionarUsuarioEmail(userEmail);
		if(d instanceof DtSocioExtra)
			return new DtSocioWS((DtSocioExtra) d);
		else
			return new DtProfesorWS((DtProfesorExtra) d);
	}
	
    @WebMethod
	public void editarDatosBasicos(String userNick,  DtUsuarioWS datoUser) throws UsuarioNoExisteException{
		IUC.editarDatosBasicos(userNick, datoUser.adapt());
	}
	
    @WebMethod
	public void seguir(String seguidor,  String seguido) throws UsuarioNoExisteException{
		IUC.seguir(seguidor, seguido);
	}
	
    @WebMethod
	public void dejarDeSeguir(String seguidor,  String seguido) throws UsuarioNoExisteException{
		IUC.dejarDeSeguir(seguidor, seguido);
	}

    @WebMethod
	public void comprarCuponera(String cuponera,  String socio,  DtFechaWS fechaCompra) throws UsuarioNoExisteException,  CuponeraNoExisteException{
		IUC.comprarCuponera(cuponera, socio, fechaCompra.adapt());
	}
	
    @WebMethod
	public boolean verificarIdentidadEmail(String email,  String pass) {
		return IUC.verificarIdentidadEmail(email, pass);
	}
    @WebMethod
	public boolean verificarIdentidadNickname(String nick,  String pass) {
		return IUC.verificarIdentidadNickname(nick, pass);
	}
	
    @WebMethod
	public void favoritearActividad(String nick, String ins, String actDep) throws UsuarioNoExisteException, InstitucionException{
		IUC.favoritearActividad(nick, ins, actDep);
	}
    
    @WebMethod
	public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor) throws UsuarioNoExisteException, ClaseException, InstitucionException{
		IUC.valorarProfesor(nickSocio, ins, actDep, cla, valor);
	}
    
    
    /*
     * Curiosamente estas dos funciones son necesarias para que JAXBL o como demonios se llame utilize los tipos de datos DtSocioWS y DtProfesorWS
     * en las funciones abstractas con usuario. Si se quitan estas funciones los DtSocios y DtProfesor se retornan como DtUsuarios.
     */
    @WebMethod
	public DtProfesorWS seleccionarProfesor(String nick) throws UsuarioNoExisteException{
		DtUsuarioExtra d = IUC.seleccionarUsuario(nick);
		return new DtProfesorWS((DtProfesorExtra) d);
	}
    
    @WebMethod
	public DtSocioWS seleccionarSocio(String nick) throws UsuarioNoExisteException{
		DtUsuarioExtra d = IUC.seleccionarUsuario(nick);
		return new DtSocioWS((DtSocioExtra) d);
	}
    
    /*@WebMethod
    public Set<Integer> obtenerValoraciones(String nickProfesor) throws UsuarioNoExisteException {
		return IUC.obtenerValoraciones(nickProfesor);
    }*/
    
	/*
	 * Esta función no solamente sirve para verificar que el ws está operativo pero tambien para verificar que estén utilizando la codificación correcta en el
	 * eclipse;
	 */
    @WebMethod
    public String ping() {
    	return "🏓";
    }
}

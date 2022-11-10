package webServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import datatypesWS.LogEntryWS;
import logica.Fabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSContentController {
	private String contentRootPath = Main.config.getProperty("assetfolderPath");
	@SuppressWarnings("serial")
	Map<String,String> typeMap = new HashMap<String,String>(){{
	    this.put("cla", "images/classes/");
	    this.put("cup", "images/cups/");
	    this.put("act", "images/activities/");
	    this.put("usu", "images/users/");
	}};
	private Endpoint endpoint = null;

	public WSContentController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("contentController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }
    
    @WebMethod
    public byte[] get(String type, String id) throws IOException {    	
    	byte[] byteArray = null;
		try {
		    File f = new File(contentRootPath + typeMap.get(type) + id);
		    FileInputStream streamer = new FileInputStream(f);
		    byteArray = new byte[streamer.available()];
		    streamer.read(byteArray);
		    streamer.close();
		} catch (IOException e) {
		    throw e;
		}
		return byteArray;
    }
    
    @WebMethod
    public void post(String type, String id, byte[] content) throws IOException {
		try {
		    File f = new File(contentRootPath + typeMap.get(type) + id);
		    FileOutputStream streamer = new FileOutputStream(f);
		    streamer.write(content);
		    streamer.close();
		} catch (IOException e) {
		    throw e;
		}
    }
    
    @WebMethod
    public void sendReports(LogEntryWS[] entries) {
    	Fabrica.getInstance().getILogger().addLogs(entries);
    }
}

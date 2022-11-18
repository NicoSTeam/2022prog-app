
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import net.java.dev.jaxb.array.StringArray;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSClaseController", targetNamespace = "http://webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    net.java.dev.jaxb.array.ObjectFactory.class,
    webservices.ObjectFactory.class
})
public interface WSClaseController {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     * @throws FechaInvalidaException_Exception
     * @throws UsuarioNoExisteException_Exception
     * @throws InstitucionException_Exception
     * @throws ClaseException_Exception
     * @throws ActividadDeportivaException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/ingresarDatosClaseRequest", output = "http://webServices/WSClaseController/ingresarDatosClaseResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/ingresarDatosClase/Fault/InstitucionException"),
        @FaultAction(className = FechaInvalidaException_Exception.class, value = "http://webServices/WSClaseController/ingresarDatosClase/Fault/FechaInvalidaException"),
        @FaultAction(className = ClaseException_Exception.class, value = "http://webServices/WSClaseController/ingresarDatosClase/Fault/ClaseException"),
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://webServices/WSClaseController/ingresarDatosClase/Fault/UsuarioNoExisteException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSClaseController/ingresarDatosClase/Fault/ActividadDeportivaException")
    })
    public int ingresarDatosClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        DtClaseWS arg2)
        throws ActividadDeportivaException_Exception, ClaseException_Exception, FechaInvalidaException_Exception, InstitucionException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     * @throws FechaInvalidaException_Exception
     * @throws UsuarioNoExisteException_Exception
     * @throws NoExisteCuponeraException_Exception
     * @throws ClaseException_Exception
     * @throws InstitucionException_Exception
     * @throws ActividadDeportivaException_Exception
     */
    @WebMethod
    @Action(input = "http://webServices/WSClaseController/inscribirSocioRequest", output = "http://webServices/WSClaseController/inscribirSocioResponse", fault = {
        @FaultAction(className = ClaseException_Exception.class, value = "http://webServices/WSClaseController/inscribirSocio/Fault/ClaseException"),
        @FaultAction(className = FechaInvalidaException_Exception.class, value = "http://webServices/WSClaseController/inscribirSocio/Fault/FechaInvalidaException"),
        @FaultAction(className = NoExisteCuponeraException_Exception.class, value = "http://webServices/WSClaseController/inscribirSocio/Fault/NoExisteCuponeraException"),
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/inscribirSocio/Fault/InstitucionException"),
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://webServices/WSClaseController/inscribirSocio/Fault/UsuarioNoExisteException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSClaseController/inscribirSocio/Fault/ActividadDeportivaException")
    })
    public void inscribirSocio(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        TRegWS arg4,
        @WebParam(name = "arg5", partName = "arg5")
        DtFechaWS arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws ActividadDeportivaException_Exception, ClaseException_Exception, FechaInvalidaException_Exception, InstitucionException_Exception, NoExisteCuponeraException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerActividadesRequest", output = "http://webServices/WSClaseController/obtenerActividadesResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/obtenerActividades/Fault/InstitucionException")
    })
    public StringArray obtenerActividades(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerClasesRequest", output = "http://webServices/WSClaseController/obtenerClasesResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/obtenerClases/Fault/InstitucionException")
    })
    public StringArray obtenerClases(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerUsuariosRequest", output = "http://webServices/WSClaseController/obtenerUsuariosResponse")
    public StringArray obtenerUsuarios();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerInstitucionActDepRequest", output = "http://webServices/WSClaseController/obtenerInstitucionActDepResponse")
    public String obtenerInstitucionActDep(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerActividadesAprobadasRequest", output = "http://webServices/WSClaseController/obtenerActividadesAprobadasResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/obtenerActividadesAprobadas/Fault/InstitucionException")
    })
    public StringArray obtenerActividadesAprobadas(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerProfesoresRequest", output = "http://webServices/WSClaseController/obtenerProfesoresResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/obtenerProfesores/Fault/InstitucionException")
    })
    public StringArray obtenerProfesores(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtClaseWS
     * @throws ClaseException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/buscarClaseRequest", output = "http://webServices/WSClaseController/buscarClaseResponse", fault = {
        @FaultAction(className = ClaseException_Exception.class, value = "http://webServices/WSClaseController/buscarClase/Fault/ClaseException")
    })
    public DtClaseWS buscarClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ClaseException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/getCuponerasSocioClaseRequest", output = "http://webServices/WSClaseController/getCuponerasSocioClaseResponse")
    public StringArray getCuponerasSocioClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws UsuarioNoExisteException_Exception
     * @throws InstitucionException_Exception
     * @throws ActividadDeportivaException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/getCuponerasDisponiblesRequest", output = "http://webServices/WSClaseController/getCuponerasDisponiblesResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://webServices/WSClaseController/getCuponerasDisponibles/Fault/UsuarioNoExisteException"),
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/getCuponerasDisponibles/Fault/InstitucionException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSClaseController/getCuponerasDisponibles/Fault/ActividadDeportivaException")
    })
    public StringArray getCuponerasDisponibles(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2)
        throws ActividadDeportivaException_Exception, InstitucionException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws ClaseException_Exception
     * @throws InstitucionException_Exception
     * @throws ActividadDeportivaException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/sortearPremiosRequest", output = "http://webServices/WSClaseController/sortearPremiosResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/sortearPremios/Fault/InstitucionException"),
        @FaultAction(className = ClaseException_Exception.class, value = "http://webServices/WSClaseController/sortearPremios/Fault/ClaseException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSClaseController/sortearPremios/Fault/ActividadDeportivaException")
    })
    public StringArray sortearPremios(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2)
        throws ActividadDeportivaException_Exception, ClaseException_Exception, InstitucionException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerInstitucionesRequest", output = "http://webServices/WSClaseController/obtenerInstitucionesResponse")
    public StringArray obtenerInstituciones();

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns webservices.DtClaseWS
     * @throws ClaseException_Exception
     * @throws InstitucionException_Exception
     * @throws ActividadDeportivaException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/seleccionarClaseRequest", output = "http://webServices/WSClaseController/seleccionarClaseResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSClaseController/seleccionarClase/Fault/InstitucionException"),
        @FaultAction(className = ClaseException_Exception.class, value = "http://webServices/WSClaseController/seleccionarClase/Fault/ClaseException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSClaseController/seleccionarClase/Fault/ActividadDeportivaException")
    })
    public DtClaseWS seleccionarClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2)
        throws ActividadDeportivaException_Exception, ClaseException_Exception, InstitucionException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSClaseController/obtenerSociosRequest", output = "http://webServices/WSClaseController/obtenerSociosResponse")
    public StringArray obtenerSocios();

}
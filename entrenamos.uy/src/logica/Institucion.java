package logica;

import datatypes.DtActividadDeportiva;
import datatypes.DtCategoria;
import datatypes.DtInstitucion;

import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;
import logica.persistencia.Entidades.ActividadesDeportivas;
import logica.persistencia.Entidades.Instituciones;
import excepciones.ActividadDeportivaException;
import excepciones.InstitucionException;

import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;


public class Institucion {
	
    private String nombre;
    private String urlw;
    private String descripcion;
    private Map<String,   ActividadDeportiva> actsDeps;
    private Set<Profesor> profesores;
	private Logger log;
    public Institucion(String nombre,   String descripcion,   String url) {
        this.nombre = nombre;
        this.urlw = url;
        this.descripcion = descripcion;
        this.actsDeps = new HashMap<>();
        this.profesores = new HashSet<>();
        
		log = Logger.getLogger(manejadorInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
    }

    public String getNombre() {
    	return nombre;
    }
    public String getURL() {
    	return urlw;
    }
    public String getDescripcion() {
    	return this.descripcion;
    }
    public Map<String,   ActividadDeportiva> getActsDeps(){ 
    	return this.actsDeps;
    }
    public Set<Profesor> getProfesores() {
    	return profesores;
    }  
    public ActividadDeportiva findActividad(String actDepNombre) {
    	return actsDeps.get(actDepNombre);
    }

    public void addProfesor(Profesor profe) {
    	profesores.add(profe);
    	DataPersistencia.getInstance().persistirProfesor(profe);
    	log.info("Institucion "+nombre+" event: "+" new prof "+profe.getNickname());
    }
    
//    public void nuevaActDep(DtActividadDeportiva datosAD) {
//        ActividadDeportiva actDep = new ActividadDeportiva(datosAD);
//        actsDeps.put(datosAD.getNombre(),  actDep);
//    }

    public int addActividadDeportiva(DtActividadDeportiva datosAD,   Map<String,   Categoria> cat,   Profesor creador, Institucion ins) {
        ActividadDeportiva actDep = new ActividadDeportiva(datosAD,  cat,  creador);
        if (actsDeps.containsKey(datosAD.getNombre()))
        	return 1;
		actsDeps.put(datosAD.getNombre(),   actDep);
		DataPersistencia.getInstance().persistirActividad(getActsDeps().get(datosAD.getNombre()), ins);
    	log.info("Institucion "+nombre+" event: "+" new actDep "+actDep.getNombre());
		return 0;
    }

    public Boolean existeActDep(String nombreActDep) {
        return actsDeps.containsKey(nombreActDep);
    }

    public Set<String> obtenerNombresActDep() {
		return actsDeps.keySet();
    }

//    public Set<String> obtenerNombreClasesActDep(String actDep) {
//        ActividadDeportiva actDept = actsDeps.get(actDep);
//        return actDept.getNombreClases();
//    }

//    public DtClaseExt obtenerDtClase(String actDep,  String clase) {
//        ActividadDeportiva actDept = actsDeps.get(actDep);
//        return actDept.getClaseDatos(clase);
//    }

//    public Set<DtClase> obtenerDtClases(String actDep) {
//        ActividadDeportiva actDept = actsDeps.get(actDep);
//        return actDept.getDatosClases();
//    }

    public ActividadDeportiva getActDep(String nombreActDep) throws ActividadDeportivaException {
    	ActividadDeportiva res = actsDeps.get(nombreActDep);
    	if (res == null) {
    		throw new ActividadDeportivaException("La Actividad Deportiva no pertenece a esta Institucion.");
    	}
    	return res;
    }

//    public void addClase(String actDep,  DtClase datosClase,  Profesor pp){
//        //ActividadDeportiva actDept = actsDeps.get(actDep);
//        //int cod = actDept.addClase(datosClase,  pp);
//    }

//    public Clase findClase(String actDep,   String clase) {
//        ActividadDeportiva actDept = actsDeps.get(actDep);
//        return actDept.findClase(clase);
//    }

    public Set<String> getMiTrabajo(Profesor profe) {
        Set<String> nombreActsDepsProfe = new HashSet<>();
		for (Map.Entry<String,   ActividadDeportiva> x: actsDeps.entrySet())
			if (x.getValue().participaProfesor(profe))
				nombreActsDepsProfe.add(x.getKey());
        return nombreActsDepsProfe;
    }

	public Profesor getProfesor(String nick) throws UsuarioNoExisteException {
		Profesor res = null;
		for (Profesor x: profesores)
			if (x.getNickname().equals(nick)) {
				res = x;
			}
		if (res == null) {
			throw new UsuarioNoExisteException("El Profesor seleccionado no pertenece a esta Institucion.");
		}
		return res;
	}
	
	public DtInstitucion obtenerDatos() {
		return new DtInstitucion(nombre,   getDescripcion(),   getURL());
	}

	public void finalizarAct(String actDep) {
		actsDeps.get(actDep).suicidar();
		actsDeps.remove(actDep);
	}
}

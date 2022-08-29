package datatypes;

import java.util.List;


public class DtClaseExtra extends DtClase {
	
	private List<String> alumnos;
	private List<String> soloNickAlumnos;
	
	public DtClaseExtra(String nom, String nickP,String correoP, int min, int max, String url, DtFechaHora fechC, DtFechaHora fechR, 
			List<String> als, List<String> soloNickAlumnos) {
		super(nom, nickP,correoP, min, max, url, fechC, fechR);
		this.alumnos = als;
		this.soloNickAlumnos = soloNickAlumnos;
	}

	public List<String> getAlumnos() {
		return this.alumnos;
	}
	
	public List<String> getNickAlumnos() {
		return this.soloNickAlumnos;
	}
	
	public String toString() {
		String res = new String();
		res += "Nombre de Clase: " + this.getNombre() + "\n";
		res += "Fecha de Inicio: " + this.getFechaClase().toFechaHora() + "\n";
		res += "Profesor que la Dicta: " + this.getNicknameProfesor() + " <" + this.getCorreoProfesor() + ">\n";
		res += "Cantidad de Cupos: \n";
		res += "    Minimos: " + this.getMinSocios() + " socios.\n";
		res += "    Maximos: " + this.getMaxSocios() + " socios.\n";
		res += "Direccion Web: " + this.getURL() + "\n";
		res += "Fecha de Registro: " + this.getFechaRegistro().toFecha() + "\n";
		if (alumnos.isEmpty()) {
			res += "Esta clase no presenta alumnos ingresados.";
		} else {
			res += "Listado de Alumnos: \n";
			for (String x: alumnos) {
				res += "    " + x + "\n";
			}
		}
		return res;
	}
}

package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DtSocioExtra extends DtUsuarioExtra{
	
	private Map<String,  Set<String>> clasesDeActividades;
	private Map<String, Set<String>> clasesDeActividadesFinalizadas;
	private Set<String> cuponerasCompradas;
	private Set<String> actividadesFavoritas;
	private Map<String, DtPremio> premios;
	
	public DtSocioExtra(String nickname,   String nombre,   String apellido,   String email,   String contrasenia,   DtFechaHora fechaNacimiento,  
					  Map<String,  Set<String>> clases,   byte[] imagen,   Set<String> seguidosNickname,   Set<String> seguidoresNickname,
					  Set<String> cuponeras, Set<String> actividadesFavoritas, Map<String,DtPremio> prizes, Map<String, Set<String>> clasesDeActividadesFinalizadas){
		super(nickname,   nombre,   apellido,   email,   contrasenia,   fechaNacimiento,   imagen,   seguidosNickname,   seguidoresNickname);
		cuponerasCompradas = cuponeras;
		this.premios = prizes;
		this.clasesDeActividades = clases;
		this.actividadesFavoritas = actividadesFavoritas;
		this.clasesDeActividadesFinalizadas = clasesDeActividadesFinalizadas;
	}
	
	public Map<String,  Set<String>> getAguadeUwu(){
		return clasesDeActividades;
	}
	
	public Set<String> getClases(){
		Set<String> res = new HashSet<>();
		for (Entry<String,   Set<String>> clases: clasesDeActividades.entrySet())
			res.addAll(clases.getValue());
		return res;
	}

	public Set<String> getCuponerasCompradas() {
		return cuponerasCompradas;
	}

	public Set<String> getActividadesFavoritas() {
		return actividadesFavoritas;
	}
	
	public Map<String, DtPremio> getPremios(){
		return premios;
	}

	public Map<String, Set<String>> getClasesDeActividadesFinalizadas() {
		return clasesDeActividadesFinalizadas;
	}
	public void setClasesDeActividadesFinalizadas(Map<String, Set<String>> r) {
		this.clasesDeActividadesFinalizadas = r;
	}
	public DtSocio downgrade() {
		return new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getContrasenia(), this.getFechaNacimiento(), this.getImagen());
	}
}
	

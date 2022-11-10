package datatypesWS;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import datatypes.DtUsuarioExtra;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DtUsuarioWS {
	private String nickname,   nombre,   apellido,   email,   contrasenia;
	private DtFechaWS fechaNacimiento;
	private byte[] imagen;
	private String[] seguidosNickname;
	private String[] seguidoresNickname;
	
	public DtUsuarioWS() { }
	public DtUsuarioWS(DtUsuarioExtra d) {
		this.setNickname(d.getNickname());
		this.setNombre(d.getNombre());
		this.setApellido(d.getApellido());
		this.setEmail(d.getEmail());
		this.setFechaNacimiento(new DtFechaWS(d.getFechaNacimiento()));
		this.setContrasenia(d.getContrasenia());
		this.setImagen(d.getImagen());
		this.setSeguidoresNickname(d.getSeguidoresNickname().toArray(new String[0]));
		this.setSeguidosNickname(d.getSeguidosNickname().toArray(new String[0]));
	}
	public abstract DtUsuarioExtra adapt();
	
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	public DtFechaWS getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(DtFechaWS fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String[] getSeguidosNickname() {
		return seguidosNickname;
	}
	public void setSeguidosNickname(String[] seguidosNickname) {
		this.seguidosNickname = seguidosNickname;
	}
	public String[] getSeguidoresNickname() {
		return seguidoresNickname;
	}
	public void setSeguidoresNickname(String[] seguidoresNickname) {
		this.seguidoresNickname = seguidoresNickname;
	}
}

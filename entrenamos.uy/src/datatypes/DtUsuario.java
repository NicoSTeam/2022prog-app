package datatypes;

public class DtUsuario {

	private String nickname, nombre, apellido, email;
	private DtFechaHora fechaNacimiento;
	private byte[] imagen;
	
	public DtUsuario (String nickname, String nombre, String apellido, String email, DtFechaHora fechaNacimiento,  byte[] imagen) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public DtFechaHora getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
}

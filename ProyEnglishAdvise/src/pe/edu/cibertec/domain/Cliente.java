package pe.edu.cibertec.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * En esta se registrara los datos de un cliente. El/los clientes perteneceran a
 * una institucion con diferente rol: representante, coordinador (para las
 * capacitaciones)
 * 
 * @author FERNANDO
 *
 */

@Entity
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue
	private Long id;
	private String nombres;
	private String apellidos;
	private String dni;
	private String telefono;
	private String usuario;
	private String clave;

	@ManyToOne
	private Cargo cargo = new Cargo();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}

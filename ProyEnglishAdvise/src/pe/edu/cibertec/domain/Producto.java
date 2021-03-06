package pe.edu.cibertec.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * En esta clase se registrara los atributos de un libro. Producto que la
 * empresa vendera.
 * 
 * @author FERNANDO
 *
 */

@Entity
@Table(name = "tb_producto")
public class Producto {

	@Id
	@GeneratedValue
	private Long id;
	private String isbn;
	private String titulo;
	private String imagen;
	private String descripcion;
	private double precio;
	private int stock;
	@Transient
	private int cantidad;

	public Producto(){}
	
	public Producto(long id){this.id = id;}
	
	public Producto(String isbn, String titulo, String imagen, String descripcion, double precio, int stock) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}

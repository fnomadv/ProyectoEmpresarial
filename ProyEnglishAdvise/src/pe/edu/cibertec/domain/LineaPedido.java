package pe.edu.cibertec.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_lineaPedido")
public class LineaPedido {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Pedido pedido = new Pedido();
	@ManyToOne
	private Producto producto = new Producto();
	private Integer cantidad;
	private double monto;

	public LineaPedido(){}
	
	public LineaPedido(Pedido pedido) {
		super();
		this.pedido = pedido;
	}

	public LineaPedido(Producto producto) {
		this.producto = producto;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getValorTotal() {
		return monto;
	}

	public void setValorTotal(double valorTotal) {
		this.monto = valorTotal;
	}

}

package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transaction;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import pe.edu.cibertec.domain.Cliente;
import pe.edu.cibertec.domain.LineaPedido;
import pe.edu.cibertec.domain.Pedido;
import pe.edu.cibertec.domain.Producto;
import pe.edu.cibertec.util.JPAUtil;

/**
 * ManagedBean donde se agregar un producto a una lista.
 * 
 * @author FERNANDO
 *
 */
@ManagedBean
@SessionScoped
public class CarritoCompraMB {

	private Producto productoSeleccionado = new Producto();
	private LineaPedido lineaPedido = new LineaPedido();
	private Pedido pedido = new Pedido();
	private Cliente cliente = new Cliente();
	private List<Producto> listaProdCarrito = new ArrayList<Producto>();
	private List<LineaPedido> listaLineaPedido;

	private long idProductoSeleccionado;
	private double totalCompra = 0.0;
	private double monto;
	private int cantidad;

	/**
	 * Metodo que recibe el id del producto para capturar sus datos y retorna un
	 * lista del producto mas detallada redirecciona a pagina producto_detalle.
	 * 
	 * @param idProducto
	 * @return
	 */
	public String seleccionarProducto(long idProducto) {
		cantidad = 0;
		listaProdCarrito.removeAll(getListaProdCarrito());
		productoSeleccionado = getProductoSeleccionado(idProducto);
		listaProdCarrito.add(productoSeleccionado);

		return "producto_detalle";
	}

	public String agregarAlCarrito(long idProducto) {
		productoSeleccionado = getProductoSeleccionado(idProducto);

		if (cantidad != 0) {
			if (listaLineaPedido == null) {
				// creamos un lista de pedido
				listaLineaPedido = new ArrayList<LineaPedido>();

				// guardamos los datos del producto en una linea del carrito
				lineaPedido = new LineaPedido(productoSeleccionado);
				lineaPedido.setCantidad(cantidad);
				monto = productoSeleccionado.getPrecio() * lineaPedido.getCantidad();
				lineaPedido.setMonto(monto);
				totalCompra += lineaPedido.getMonto();

				// agregamos la linea del pedido a una lista del pedido
				// (carrito)
				listaLineaPedido.add(lineaPedido);
				return "carrito_actual";
			} else {

				// si ya existe un carrito buscamos el producto selecciona
				// dentro de la lista
				for (LineaPedido lp : listaLineaPedido) {
					if (lp.getProducto().getId() == productoSeleccionado.getId()) {
						System.out.println("modificando el producto que estaba en el carrito");
						lineaPedido.setCantidad(cantidad + lp.getCantidad());
						monto = productoSeleccionado.getPrecio() * lineaPedido.getCantidad();
						lineaPedido.setMonto(monto);
						totalCompra += lineaPedido.getMonto();
						return "carrito_actual";
					}
				}

				System.out.println("creando una nueva linea de pedido");
				lineaPedido = new LineaPedido(productoSeleccionado);
				lineaPedido.setCantidad(cantidad);
				monto = productoSeleccionado.getPrecio() * lineaPedido.getCantidad();
				lineaPedido.setMonto(monto);
				totalCompra += lineaPedido.getMonto();

				listaLineaPedido.add(lineaPedido);

				// guardarCarritoTemporal();

			}
			return "carrito_actual";
		} else {
			System.out.println("Error cantidad no puede ser 0");
			return null;
		}

	}
	
	public String eliminarProductoDelCarrito(long id){
		LineaPedido lp = obtenerProductoDelCarrito(id);
		listaLineaPedido.remove(lp);
		return "carrito_actual";
	}
	
	public String anularCarrito(){
		limpiarCarrito();
		return "listado_productos";
	}
	
	private void limpiarCarrito(){
		listaLineaPedido = null;
	}

	private void guardarCarritoTemporal() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Date fecha = Calendar.getInstance().getTime();
		pedido = new Pedido(fecha, cliente);
		// actualizarProductoSeleccionado(lineaPedido.getProducto().getId(),lineaPedido.getCantidad());
		tx.begin();
		em.persist(pedido);
		// em.merge(lineaPedido);
		// em.contains(arg0)
		System.out.println("**********Pedido**********");
		System.out.println("ID Pedido: " + pedido.getId());
		System.out.println("ID Pedido: " + pedido.getId());
		System.out.println("ID Pedido: " + pedido.getId());
		System.out.println("ID Pedido: " + pedido.getId());
	}

	// private Producto buscarProductoCarrito(long idProducto) {
	// Producto p = null;
	// for (Producto prod : listaProdCarrito) {
	// if (prod.getId() == idProducto) {
	// p = prod;
	// break;
	// }
	// }
	// return p;
	// }

	public Producto getProductoSeleccionado(long idproducto) {
		EntityManager em = JPAUtil.getEntityManager();
		productoSeleccionado = em.createQuery("select e from Producto e where e.id = :idprod", Producto.class)
				.setParameter("idprod", idproducto).getSingleResult();
		return productoSeleccionado;
	}
	
	public LineaPedido obtenerProductoDelCarrito(long id){
		EntityManager em = JPAUtil.getEntityManager();
		lineaPedido = em.createQuery("select e from LineaPedido e where e.id = :id", LineaPedido.class)
				.setParameter("id", id).getSingleResult();
		return lineaPedido;
	}

	public LineaPedido getLineaPedido() {
		return lineaPedido;
	}

	public void setLineaPedido(LineaPedido lineaPedido) {
		this.lineaPedido = lineaPedido;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<Producto> getListaProdCarrito() {
		return listaProdCarrito;
	}

	public void setListaProdCarrito(List<Producto> listaProdCarrito) {
		this.listaProdCarrito = listaProdCarrito;
	}

	public double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public long getIdProductoSeleccionado() {
		return idProductoSeleccionado;
	}

	public void setIdProductoSeleccionado(long idProductoSeleccionado) {
		this.idProductoSeleccionado = idProductoSeleccionado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<LineaPedido> getListaLineaPedido() {
		return listaLineaPedido;
	}

	public void setListaLineaPedido(List<LineaPedido> listaLineaPedido) {
		this.listaLineaPedido = listaLineaPedido;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}

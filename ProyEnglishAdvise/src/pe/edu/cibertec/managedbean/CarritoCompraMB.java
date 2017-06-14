package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pe.edu.cibertec.domain.LineaPedido;
import pe.edu.cibertec.domain.Pedido;
import pe.edu.cibertec.domain.Producto;
import pe.edu.cibertec.util.JPAUtil;

/**
 * ManagedBean donde se agregar un producto a una lista.
 * @author FERNANDO
 *
 */
@ManagedBean
@SessionScoped
public class CarritoCompraMB {

	private Producto productoSeleccionado = new Producto();
	private LineaPedido lineaPedido = new LineaPedido();
	private Pedido pedido = new Pedido();
	private List<Producto> listaProdCarrito = new ArrayList<Producto>();
	private List<LineaPedido> listaLineaPedido = new ArrayList<LineaPedido>();
	
	private long idProductoSeleccionado;
	private double totalCompra = 0.0;
	private Double monto;
	private Integer cantidad;
	

	/**
	 * Metodo que recibe el id del producto para capturar sus datos y retorna un lista del
	 * @param idProducto
	 * @return
	 */
	public String comprarProd(long idProducto) {
		EntityManager em = JPAUtil.getEntityManager();
		listaProdCarrito.removeAll(getListaProdCarrito());
		productoSeleccionado = em.createQuery("select e from Producto e where e.id = :idprod",Producto.class)
				.setParameter("idprod",idProducto).getSingleResult();
		
        listaProdCarrito.add(productoSeleccionado);

        return "producto_detalle";
    }
	
	public String agregarAlCarrito(long idProducto){
		EntityManager em = JPAUtil.getEntityManager();
		productoSeleccionado = em.createQuery("select e from Producto e where e.id = :idprod",Producto.class)
				.setParameter("idprod",idProducto).getSingleResult();
//	
//		lineaPedido = new LineaPedido(productoSeleccionado);
//		lineaPedido.setCantidad(cantidad);
//		lineaPedido.setMonto(productoSeleccionado.getPrecio()*cantidad);
//		System.out.println("cantidad"+lineaPedido.getCantidad());
//		System.out.println("Monto"+productoSeleccionado.getPrecio());
//		listaLineaPedido.add(lineaPedido);
//		
//		guardarCarritoTemporal();
//		
		return "carrito_actual";
	}
	
	void guardarCarritoTemporal(){
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			em.merge(pedido);
			em.merge(lineaPedido);
	}
	
//	private Producto buscarProductoCarrito(long idProducto) {
//        Producto p = null;
//        for (Producto prod : listaProdCarrito) {
//            if (prod.getId() == idProducto) {
//                p = prod;
//                break;
//            }
//        }
//        return p;
//    }
//	
//	public Producto buscarProductoParaElCarrito(long idProductoSeleccionado) {
//		EntityManager em = JPAUtil.getEntityManager();
//		productoSeleccionado = em.createQuery("select e from Producto e where e.id = :idprod",Producto.class)
//				.setParameter("idprod",productoSeleccionado.getId()) .getSingleResult();
//		return productoSeleccionado;
//	}
	
	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
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
	
}

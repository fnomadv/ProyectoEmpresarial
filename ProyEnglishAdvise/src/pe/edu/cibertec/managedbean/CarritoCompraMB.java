package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

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
	
	private List<Producto> listaProdCarrito = new ArrayList<Producto>();
	
	private long idProductoSeleccionado;
	private double totalCompra = 0.0;

	
	public String agregarAlCarrito(long idProducto) {
		EntityManager em = JPAUtil.getEntityManager();
		productoSeleccionado = em.createQuery("select e from Producto e where e.id = :idprod",Producto.class)
				.setParameter("idprod",idProducto) .getSingleResult();
		
        listaProdCarrito.add(productoSeleccionado);

        return "carrito_actual";
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

	
}

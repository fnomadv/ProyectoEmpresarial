package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import pe.edu.cibertec.domain.Producto;
import pe.edu.cibertec.util.JPAUtil;

@ManagedBean
@SessionScoped
public class ProductoMB {

	private Producto producto = new Producto();
	
	private List<Producto> productos = new ArrayList<Producto>();

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getProductos() {
		EntityManager em = JPAUtil.getEntityManager();
		productos = em.createQuery("from Producto",Producto.class).getResultList();
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
}

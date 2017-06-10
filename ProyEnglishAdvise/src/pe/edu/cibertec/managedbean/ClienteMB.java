package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pe.edu.cibertec.domain.Cargo;
import pe.edu.cibertec.domain.Cliente;
import pe.edu.cibertec.util.JPAUtil;

@ManagedBean
@SessionScoped
public class ClienteMB {

	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	private List<Cargo> cargos = new ArrayList<>();
	
//	public ClienteMB(){
//		EntityManager em = JPAUtil.getEntityManager();
//	}
	
	public void guardarCliente(){
		EntityManager em = JPAUtil.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
			em.merge(cliente);
		tx.commit();
	}
	
	public String guardar(){
		
		guardarCliente();
		
		FacesMessage message = 
				new FacesMessage("Se guardó correctamente",
						"El cliente se guardó de manera satisfactoria");
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
		cliente = new Cliente();
		
		
		return "registrar_cliente";
	}
	
	/*
	public String guardarYEnviar(){
		guardar();
		return "registrar_laptop";
	}

	public String volver(){
		empleado = new Empleado();
		return "registro_empleado";
	}*/

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		EntityManager em = JPAUtil.getEntityManager();
		clientes= em.createQuery("from Cliente",Cliente.class).getResultList();
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cargo> getCargos() {
		EntityManager em = JPAUtil.getEntityManager();
		cargos = em.createQuery("from Cargo",Cargo.class).getResultList();
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}

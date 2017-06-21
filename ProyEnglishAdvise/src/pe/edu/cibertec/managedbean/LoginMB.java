package pe.edu.cibertec.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import pe.edu.cibertec.domain.Cliente;
import pe.edu.cibertec.util.JPAUtil;

@ManagedBean
@SessionScoped
public class LoginMB {
	
	private Cliente cliente =  new Cliente();

	private String usuario;
	private String clave;
	
	public String validarLogin(String username, String pass){
		EntityManager em = JPAUtil.getEntityManager();
		Cliente c = em.createNamedQuery("select l from login l where l.usuario = :usu and l.clave = :cla" , Cliente.class)
				.setParameter("usu", username)
				.setParameter("cla", pass)
				.getSingleResult();
		
		if(c != null)
			return "welcome";
		else
			return "";
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


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}

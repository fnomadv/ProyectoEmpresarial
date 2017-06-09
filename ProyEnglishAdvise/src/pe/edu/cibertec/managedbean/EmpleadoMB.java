package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pe.edu.cibertec.domain.Departamento;
import pe.edu.cibertec.domain.Empleado;
import pe.edu.cibertec.util.JPAUtil;

@ManagedBean
@SessionScoped
public class EmpleadoMB {

	private Empleado empleado = new Empleado();
	
	private List<Empleado> empleados = new ArrayList<Empleado>();
	
	private List<Departamento> departamentos = new ArrayList<>();
	
//	public EmpleadoMB(){
//		EntityManager em = JPAUtil.getEntityManager();
//	}
	
	public void guardarEmpleado(){
		EntityManager em = JPAUtil.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
			em.merge(empleado);
		tx.commit();
	}
	
	public String guardar(){
		
		guardarEmpleado();
		
		FacesMessage message = 
				new FacesMessage("Se guardó correctamente",
						"La laptop se guardó de manera satisfactoria");
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
		empleado = new Empleado();
		
		
		return "registrar_empleado";
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
	

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Empleado> getEmpleados() {
		EntityManager em = JPAUtil.getEntityManager();
		empleados = em.createQuery("from Empleado",Empleado.class).getResultList();
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public List<Departamento> getDepartamentos() {
		EntityManager em = JPAUtil.getEntityManager();
		departamentos = em.createQuery("from Departamento",Departamento.class).getResultList();
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
}

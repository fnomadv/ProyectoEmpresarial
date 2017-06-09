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
public class DepartamentoMB {

	private Departamento departamento = new Departamento();

	private List<Departamento> departamentos = new ArrayList<>();

//	public DepartamentoMB() {
//		EntityManager em = JPAUtil.getEntityManager();
//	}

	public void guardarDepartamento(){
		EntityManager em = JPAUtil.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
			em.merge(departamento);
		tx.commit();
	}
	
	public String guardar(){
		
		guardarDepartamento();
		
		FacesMessage message = 
				new FacesMessage("Se guardó correctamente",
						"El departamento se guardó de manera satisfactoria");
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);

		departamento = new Departamento();

		return "registrar_departamento";
	}
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getDepartamentos() {
		EntityManager em = JPAUtil.getEntityManager();
		departamentos = em.createQuery("from Departamento", Departamento.class).getResultList();
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

}

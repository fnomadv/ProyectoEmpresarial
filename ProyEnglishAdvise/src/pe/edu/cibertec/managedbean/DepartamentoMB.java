package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import pe.edu.cibertec.domain.Departamento;
import pe.edu.cibertec.util.JPAUtil;

public class DepartamentoMB {

	private Departamento departamento = new Departamento();
	
	private List<Departamento> departamentos = new ArrayList<>();

	public DepartamentoMB(){
	EntityManager em = JPAUtil.getEntityManager();
}
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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

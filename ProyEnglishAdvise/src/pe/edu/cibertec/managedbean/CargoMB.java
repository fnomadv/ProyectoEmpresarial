package pe.edu.cibertec.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import pe.edu.cibertec.domain.Cargo;
import pe.edu.cibertec.util.JPAUtil;

@ManagedBean
@SessionScoped
public class CargoMB {

	private Cargo cargo = new Cargo();
	private List<Cargo> cargos = new ArrayList<Cargo>();

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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

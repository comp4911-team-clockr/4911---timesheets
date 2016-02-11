package comp4911.controllers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp4911.models.Employee;

@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author jackee
 *<p>The class will attempt to handle create, reead, update, delete.
 */
public class EmployeeManager implements Serializable {
	@PersistenceContext(unitName="clockr") EntityManager em;

	/**
	 *<p>Some Random SerialVersionUID.</p> 
	 */
	private static final long serialVersionUID = -7520188301250130738L;

	public Employee find(int id) {
		return em.find(Employee.class, id);
	}
	
	public void persist(Employee employee) {
		em.persist(employee);
	}
	
	public void merge(Employee employee) {
		em.merge(employee);
	}
	
	public void remove(Employee employee) {
		employee = find(employee.getEmpNumber());
		em.remove(employee);
	}
	
}

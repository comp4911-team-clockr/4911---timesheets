package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.Credential;
import comp4911.models.Employee;
import comp4911.models.WorkPackage;

@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author jackee
 *<p>The class will attempt to handle create, read, update, delete.
 */
public class EmployeeManager implements Serializable {
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;

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

	public Employee findByUserId(String id) {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.credential.userId = :userID ", Employee.class);
		query.setParameter("userID", id);
		java.util.List<Employee> results = query.getResultList();
		Employee emp = results.get(0);
		return emp;
	}

	public java.util.List<Employee> getAll() {
		TypedQuery<Employee> query = em.createQuery("select e from Employee e WHERE e.active=TRUE",
				Employee.class);
		java.util.List<Employee> employees = query.getResultList();

		return employees;
	}

	public java.util.List<Employee> getAllBySupervisor(int supId) {
		TypedQuery<Employee> query = em.createQuery("select e from Employee e " +
				"WHERE e.supNum LIKE " + supId,
				Employee.class);
		java.util.List<Employee> employees = query.getResultList();

		return employees;
	}

	public java.util.List<Employee> getAllProjectManagers(CredentialManager credManager) {
		java.util.List<Employee> employees = new java.util.ArrayList<Employee>();

		for(Credential c : credManager.getAllProjectManagers()) {
			employees.add(c.getEmployee());
		}
		return employees;
	}

	public java.util.List<Employee> getAllEmployeesToAddForWP(EmployeeWPManager empWPManager, WorkPackage wp) {
		java.util.List<Integer> empInWP = empWPManager.listEmpNumByWP(wp.getWpId());
		if (!empInWP.isEmpty()) {
			TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e " +
					"WHERE e.empNumber NOT IN :empNumList",
					Employee.class);
			query.setParameter("empNumList", empInWP);
			java.util.List<Employee> employees = query.getResultList();
			return employees;
		}
		return getAll();
	}
	
	public java.util.List<Employee> getAllSupervisors() {
		TypedQuery<Employee> query = em.createQuery("select e from Employee e WHERE e.active=TRUE " 
				+ " AND e.credential.role='HumanResource' OR e.credential.role='ProjectManager'",
				Employee.class);
		java.util.List<Employee> employees = query.getResultList();

		return employees;
	}

	public int getNewPK() {
		TypedQuery<Employee> query = em.createQuery("select e from Employee e",
				Employee.class);
		java.util.List<Employee> employees = query.getResultList();

		if (employees.isEmpty())
			return 1;
		return (employees.size() + 1);
	}
}

package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.Employee;
import comp4911.models.EmployeeWPList;
import comp4911.models.Project;

@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author jackee
 *<p>The class handles create, read, update, delete.
 */
public class EmployeeWPManager implements Serializable {
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;

	/**
	 *<p>Some Random SerialVersionUID.</p> 
	 */
	private static final long serialVersionUID = -7520188301250130738L;

//	private EmployeeManager empManager;
//	
//	public EmployeeWPManager() {
//		empManager = new EmployeeManager();
//	}
	
	public EmployeeWPList find(String id) {
		return em.find(EmployeeWPList.class, id);
	}
	
	public void persist(EmployeeWPList ewp) {
		em.persist(ewp);
	}
	
	public void merge(EmployeeWPList ewp) {
		em.merge(ewp);
	}
	
	public void remove(EmployeeWPList ewp) {
		EmployeeWPList empList = find(ewp.getWpEmpId()); 
		em.remove(empList);
	}
	
	public java.util.List<EmployeeWPList> listByWP(String wp) {
		TypedQuery<EmployeeWPList> query = em.createQuery("select e from EmployeeWPList e "
				+ "WHERE e.wpID ='" + wp +"'",
                EmployeeWPList.class); 
		java.util.List<EmployeeWPList> empList = query.getResultList();
		return empList;
	}
	
	public java.util.List<Employee> listEmpByWP(String wp, EmployeeManager empManager) {
		java.util.List<Employee> empList = new java.util.ArrayList<Employee>();
		Employee temp = null;
		if (!listByWP(wp).isEmpty()){
			
			for (EmployeeWPList empWP : listByWP(wp)) {
				if ((temp = empManager.find(empWP.getEmpNum())) != null)
					empList.add(temp);
			}
		
		}
		return empList;
	}
	
	public java.util.List<Integer> listEmpNumByWP(String wp) {
		java.util.List<Integer> empNumList = new java.util.ArrayList<Integer>();
		if (!listByWP(wp).isEmpty()){
			
			for (EmployeeWPList empWP : listByWP(wp)) {
				empNumList.add(empWP.getEmpNum());
			}
		}
		return empNumList;
	}
	
	public java.util.List<EmployeeWPList> listByProj(int proj) {
		TypedQuery<EmployeeWPList> query = em.createQuery("select e from EmployeeWPList e "
				+ "WHERE e.projectId ='" + proj +"' AND e.wpID IS NULL",
                EmployeeWPList.class); 
		java.util.List<EmployeeWPList> empList = query.getResultList();
		return empList;
	}
	
	public java.util.List<Employee> listEmpByProj(int proj, EmployeeManager empManager) {
		java.util.List<Employee> empList = new java.util.ArrayList<Employee>();
		Employee temp = null;
		if (listByProj(proj) != null){
			
			for (EmployeeWPList empProj : listByProj(proj)) {
				if ((temp = empManager.find(empProj.getEmpNum())) != null)
					empList.add(temp);
			}
		
		}
		return empList;
	}
	
	public java.util.List<EmployeeWPList> listEverythingByEmpProj(Project proj, int empNo) {
		TypedQuery<EmployeeWPList> query = em.createQuery("select e from EmployeeWPList e "
				+ "WHERE e.projectId ='" + proj.getProjectId() +"' AND e.empNum =" 
				+ empNo,
                EmployeeWPList.class); 
		java.util.List<EmployeeWPList> empList = query.getResultList();
		return empList;
	}
	
	public void removeAllByProj(Project proj, int empNo) {
		java.util.List<EmployeeWPList> empList = listEverythingByEmpProj(proj, empNo);
		
		for (EmployeeWPList empWP : empList) {
			remove(empWP);
		}
	}
}

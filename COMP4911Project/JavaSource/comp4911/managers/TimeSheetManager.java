package comp4911.managers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.Employee;
import comp4911.models.StatusReport;
import comp4911.models.TimeSheet;
import comp4911.models.WorkPackage;
import comp4911.models.TimeSheetRow;

@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author Tony
 *<p>The class will attempt to handle create, read, update, delete.
 */

public class TimeSheetManager implements Serializable {
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;
	/**
	 *<p> Some Random SerialVersionUID.</p>
	 */
	private static final long serialVersionUID = 1L;

	public TimeSheet find(int id) {
		return em.find(TimeSheet.class, id);
	}

	public void persist(TimeSheet timesheet) {
		em.persist(timesheet);
	}

	public void merge(TimeSheet timesheet) {
		em.merge(timesheet);
	}

	public void remove(TimeSheet timesheet) {
		timesheet.setIsActive(false);
		em.merge(timesheet);
	}

	public TimeSheet[] getByTimeSheetId(String timesheetID){
		TypedQuery<TimeSheet> query = em.createQuery("select t from " +
				"TimeSheet t where t.timeSID = " + timesheetID, TimeSheet.class);
		List<TimeSheet> timesheets = query.getResultList();
		TimeSheet[] timeArray = new TimeSheet[timesheets.size()];
		for (int i=0; i < timeArray.length; i++){
			timeArray[i] = timesheets.get(i);
		}
		return timeArray;
	}

	public List<TimeSheet> getAll() {
		TypedQuery<TimeSheet> query = em.createQuery("select t from TimeSheet t where isActive=TRUE", TimeSheet.class);
		List<TimeSheet> timesheets = query.getResultList();

		return timesheets;
	}
	public List<TimeSheet> getAll(int id) {
		TypedQuery<TimeSheet> query = em.createQuery("select t from TimeSheet t where t.isActive=TRUE AND t.empNumber=" + id, TimeSheet.class);
		List<TimeSheet> timesheets = query.getResultList();

		return timesheets;
	}

	public List<TimeSheet> getListForApproval(int supId, EmployeeManager empManager) {
		List<Employee> empList = empManager.getAllBySupervisor(supId);
		List<TimeSheet> timesheets = null;
		TypedQuery<TimeSheet> query;
		boolean first = true;

		for (Employee e: empList) {
			 query = em.createQuery("select t from TimeSheet t where t.isActive=TRUE " +
					 "AND t.submitted=TRUE AND t.approval=FALSE AND t.empNumber= "
					 + e.getEmpNumber(), TimeSheet.class);
			if (!first) {
				timesheets.addAll(query.getResultList());
			} else {
				first = false;
				timesheets = query.getResultList();
			}
		}
		return timesheets;
	}	
//	public List<TimeSheet> getListByEmp(List<Integer> empNumList)
//	{	
//		TypedQuery<TimeSheet> query = em.createQuery("SELECT s FROM Timesheet s "+
//        		"WHERE s.empNum IN :empNumList", TimeSheet.class);
//		query.setParameter("empNumList", empNumList);
//        java.util.List<TimeSheet> reports = query.getResultList();
//        
//        return reports;
//	}
//
//	public List<TimeSheet> getTotalMDsInWP(WorkPackage wp)
//	{
//		List<TimeSheet> timesheets = null;
//
//
//		return timesheets;
//	}

	public String getNewPK(int empNum) {
		TypedQuery<TimeSheet> query = em.createQuery("SELECT t FROM TimeSheet t WHERE " +
		"t.empNumber=:lookUp", TimeSheet.class);
		query.setParameter("lookUp", empNum);
		List<TimeSheet> tsList = query.getResultList();
		if (tsList.isEmpty())
			return empNum + "|1";
		else
			return empNum + "|" + (tsList.size() + 1);
	}
}

package comp4911.managers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.TimeSheet;

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
	
	public TimeSheet[] getByTimeSheetId(int timesheetID){
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
		TypedQuery<TimeSheet> query = em.createQuery("select t from TimeSheet t", TimeSheet.class);
		List<TimeSheet> timesheets = query.getResultList();
		
		return timesheets;
	}
}
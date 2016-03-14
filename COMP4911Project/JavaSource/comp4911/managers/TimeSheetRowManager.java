package comp4911.managers;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.TimeSheetRow;

public class TimeSheetRowManager implements Serializable {
	/**
	 * random serial
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;
	
	public TimeSheetRow find (int id){
		return em.find(TimeSheetRow.class, id);
	}
	
	public void persist(TimeSheetRow timesheetrow){
		em.merge(timesheetrow);
	}
	
	public void merge(TimeSheetRow timesheetrow){
		timesheetrow = find(timesheetrow.getTimeSheetRowId());
		em.remove(timesheetrow);
	}
	
	public TimeSheetRow[] getByTimeSheetRowId(int timesheetrowID){
		TypedQuery<TimeSheetRow> query = em.createQuery("select t from " +
				"TimeSheetRow t where t.rowID = " + timesheetrowID, TimeSheetRow.class);
		List<TimeSheetRow> timesheetrows = query.getResultList();
		TimeSheetRow[] timeArray = new TimeSheetRow[timesheetrows.size()];
		for (int i=0; i < timeArray.length; i++){
			timeArray[i] = timesheetrows.get(i);
		}
		return timeArray;
	}
	
	public List<TimeSheetRow> getAll() {
		TypedQuery<TimeSheetRow> query = em.createQuery("select t from TimeSheetRow t", TimeSheetRow.class);
		List<TimeSheetRow> timesheetrows = query.getResultList();
		
		return timesheetrows;
	}
}
package comp4911.access;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.controllers.TimeSheetManager;
import comp4911.models.Employee;
import comp4911.models.TimeSheet;
import comp4911.models.TimeSheetRow;

@Named("test")
@SessionScoped

public class TimeSheetList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238841317514571931L;
	
	@Inject private TimeSheet timesheet;
	
	private TimeSheetManager timesheetManager;

//	List<TimeSheet> timesheetList;
	
	public TimeSheetList() {
		timesheetManager = new TimeSheetManager();
		timesheet = timesheetManager.getAll()[0];
	}
	
//	public void refreshTimeSheet() {
//		TimeSheet[] timesheets = timesheetManager.getAll();
//	}
//	
//	public List<TimeSheet> getTimeSheetList() {
//		if (timesheetList == null) {
//			refreshTimeSheet();
//		}
//		return timesheetList;
//	}
//	
//	public void refreshTimeSheetRows() {
//		TimeSheet[] timesheets = timesheetManager.getAll();
//	}
	
	public TimeSheetManager getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimeSheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public TimeSheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
	}
	
}
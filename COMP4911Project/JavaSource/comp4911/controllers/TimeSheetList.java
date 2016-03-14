package comp4911.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.transaction.Transactional;

import comp4911.managers.TimeSheetManager;
import comp4911.models.TimeSheet;


@Named("test")
@SessionScoped
public class TimeSheetList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238841317514571931L;
	
	@Inject 
	private TimeSheet timesheet;
	
	@Inject
	private TimeSheetManager timesheetManager;

	//private List<TimeSheet> timesheetList;
	
	public TimeSheetList() {
	}
/*
	public void refreshTimeSheet() {
		timesheetList = timesheetManager.getAll();
	}
	
	public List<TimeSheet> getTimeSheetList() {
		if (timesheetList == null) {
			refreshTimeSheet();
		}
		
		return timesheetList;
	}
	*/
	public TimeSheetManager getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimeSheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}
	
	//@Transactional
	public TimeSheet getTimesheet() {
		if(timesheet.getTimeSID() == 0) {
			//System.out.println("Timesheet is null");
			timesheet = timesheetManager.getAll().get(0);
			//System.out.println(timesheet.getTimeSheetRows().size());
		}
		
		
		return timesheet;
	}
	
	public void setTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
	}
	
}
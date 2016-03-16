package comp4911.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.transaction.Transactional;

import comp4911.managers.TimeSheetManager;
import comp4911.managers.TimeSheetRowManager;
import comp4911.models.TimeSheet;
import comp4911.models.TimeSheetRow;


@Named("test")
@SessionScoped
public class TimesheetController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238841317514571931L;
	
	@Inject 
	private TimeSheet timesheet;
	
	@Inject
	private TimeSheetManager timesheetManager;

	private List<TimeSheet> timesheetList;
	
	public TimesheetController() {
	}

	public void refreshTimeSheet() {
		timesheetList = timesheetManager.getAll();
		timesheet = timesheetList.get(0);
	}
	
	public List<TimeSheet> getTimeSheetList() {
		if (timesheetList == null) {
			refreshTimeSheet();
		}
		return timesheetList;
	}
	
	public TimeSheetManager getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimeSheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}
	
	//@Transactional
	public TimeSheet getTimesheet() {
		System.out.println("Get Timesheet called");
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
	
	public String saveChanges() {
		timesheetManager.merge(timesheet); 
		refreshTimeSheet();
		return "EditTimesheet";
	}
	
	public String createRow() {
		TimeSheetRow row = new TimeSheetRow();
		row.setTimeSheet(timesheet);
		row.setTimeSheetRowId(timesheet.getTimeSheetRows().size() + 1);
		timesheet.getTimeSheetRows().add(row);
		return "EditTimesheet";
	}
	
}
package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.transaction.Transactional;

import comp4911.managers.TimeSheetManager;
import comp4911.models.Employee;
import comp4911.models.TimeSheet;
import comp4911.models.TimeSheetRow;


@Named("test")
@SessionScoped
public class TimesheetController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238841317514571931L;
	
	private Employee employee;
	
	@Inject 
	private TimeSheet timesheet;
	
	@Inject
	private TimeSheetManager timesheetManager;

	private List<TimeSheet> timesheetList;
	
	private int numberOfTimesheets;
	
	public TimesheetController() {
	}

	public void SetEmployee(Employee employee) {
		this.employee = employee;
		refreshTimeSheet();
		numberOfTimesheets = timesheetManager.getAll().size();
	}
	
	public void refreshTimeSheet() {
		timesheetList = timesheetManager.getAll(employee.getEmpNumber());
		if (timesheetList.size() > 0)
			timesheet = timesheetList.get(0);
	}
	
	public List<TimeSheet> getTimesheetList() {
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
			timesheet = timesheetManager.getAll(employee.getEmpNumber()).get(0);
			//System.out.println(timesheet.getTimeSheetRows().size());
		}
		
		
		return timesheet;
	}
	
	public void setTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
	}
	
	public String saveChanges() {
		timesheetManager.merge(timesheet); 
		return "ViewTimesheet";
	}
	
	public void timesheetInit() {
		refreshTimeSheet();
	}
	
	public String deleteTimesheetRow(TimeSheetRow tsRow) {
		timesheet.getTimeSheetRows().remove(tsRow);
		return "EditTimesheet";
	}
	
	public void createTimesheetRow() {
		TimeSheetRow row = new TimeSheetRow();
		row.setTimeSheet(timesheet);
		row.setTimeSheetRowId(timesheet.getTimeSheetRows().size() + 1);
		timesheet.getTimeSheetRows().add(row);
	}
	
	public String createRow(String outcome) {
		createTimesheetRow();
		return outcome;
	}
	
	public String deleteTimesheet(TimeSheet timesheet) {
		timesheetManager.remove(timesheet);
		refreshTimeSheet();
		return "DisplayTimesheetList";
	}
	
	public String editTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
		return "EditTimesheet";
	}
	
	public String addTimesheetButton() {
		timesheet = new TimeSheet();
		
		timesheet.setTimeSID(numberOfTimesheets + 1);
		timesheet.setEmpNumber(employee.getEmpNumber());
		timesheet.setIsActive(true);
		Calendar cal = Calendar.getInstance();
		timesheet.setWeekNumber(cal.get(Calendar.WEEK_OF_YEAR));
		//To have a row available already once a timesheet is created
		List<TimeSheetRow> tsList = new ArrayList<TimeSheetRow>();
		timesheet.setTimeSheetRows(tsList);
		createTimesheetRow();
		
		return "CreateTimesheet";
	}
	
	public String createTimesheet() {
		numberOfTimesheets++;
		timesheetManager.persist(timesheet);
		timesheetList.add(timesheet);
		return "DisplayTimesheetList";
	}
}
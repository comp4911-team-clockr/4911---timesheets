package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.transaction.Transactional;

import comp4911.managers.EmployeeManager;
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
	
	@Inject private EmployeeManager employeeManager;


	private List<TimeSheet> timesheetList;
	
	public TimesheetController() {
	}

	public void SetEmployee(Employee employee) {
		this.employee = employee;
		refreshTimeSheet();
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
		//System.out.println("Get Timesheets called");
		if(timesheet.equals(null)) {
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
		vacationDaysTaken();
		timesheetManager.merge(timesheet);
		refreshTimeSheet();
		return "DisplayTimesheets";
	}
	
	public void timesheetInit() {
		refreshTimeSheet();
	}
	
	public String deleteTimesheetRow(TimeSheetRow tsRow, String outcome) {
		timesheet.getTimeSheetRows().remove(tsRow);
		return outcome;
	}
	
	public void createTimesheetRow() {
		
		int tsRowNum = 1;
		if (timesheet.getTimeSheetRows().size() > 0) {
			TimeSheetRow temp = timesheet.getTimeSheetRows().get(timesheet.getTimeSheetRows().size() - 1);
			tsRowNum = Integer.parseInt(temp.getTimeSheetRowId().substring(temp.getTimeSheetRowId().lastIndexOf('|') + 1));
			tsRowNum++;
		}
		String tsRowId = timesheet.getTimeSID() + "|" + tsRowNum;
		TimeSheetRow row = new TimeSheetRow();
		row.setTimeSheet(timesheet);
		row.setTimeSheetRowId(tsRowId);
		timesheet.getTimeSheetRows().add(row);
	}
	
	public String createRow(String outcome) {
		createTimesheetRow();
		return outcome;
	}
	
	public String deleteTimesheet(TimeSheet timesheet) {
		timesheetManager.remove(timesheet);
		refreshTimeSheet();
		return "DisplayTimesheets";
	}
	
	public String editTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
		return "EditTimesheet";
	}
	
	public String addTimesheetButton() {
		timesheet = new TimeSheet();
		int tsNum = 1;
		
		if (timesheetList.size() > 0) {
			TimeSheet temp = timesheetList.get(timesheetList.size() - 1);
			tsNum = Integer.parseInt(temp.getTimeSID().substring(temp.getTimeSID().lastIndexOf('|')+ 1));
			tsNum++;
		}
		
		String tsID = employee.getEmpNumber() + "|" + tsNum;
		System.out.println(tsID);
		timesheet.setTimeSID(tsID);
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
		vacationDaysTaken();
		timesheetManager.persist(timesheet);
		timesheetList.add(timesheet);
		return "DisplayTimesheets";
	}
	
	public String getTimeSheetRowNumber(TimeSheetRow row) {
		return row.getTimeSheetRowId().substring(row.getTimeSheetRowId().lastIndexOf('|') + 1);
	}
	
	public String editTimeCancel(){
		refreshTimeSheet();
		return "DisplayTimesheets";
	}
	
	public String CreateTimesheetCancel(){
		refreshTimeSheet();
		return "CancelCreateTimesheet";
	}
	
	public String cancelEditTimesheet(){
		refreshTimeSheet();
		return "cancelEditTimesheet";
	}
	
	public String CancelViewTimesheet(){
		return "cancelViewTimesheet";
	}

	public void vacationDaysTaken(){
		int daysTaken = 0;
		for(TimeSheetRow r : timesheet.getTimeSheetRows()){
			daysTaken += r.getVacationDays();
		}
		employee.setVacDays(employee.getVacDays() - daysTaken);
		employeeManager.merge(employee);
	}
}
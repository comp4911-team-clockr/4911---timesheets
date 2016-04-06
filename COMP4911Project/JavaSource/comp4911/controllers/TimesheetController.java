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
	
	private boolean approve;
	
	/* Navigation */
	private final String viewNavigation = "ViewTimesheet";
	private final String approveListNavigation = "DisplayTimesheetsForApproval";
	
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
	}
	
	public String getEmployeeName(int id) {
		return employeeManager.find(id).getFullName();
	}
	
	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
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

	public void initDisplay() {
		refreshTimeSheet();
	}
	
	public void initApproval() {
		timesheetList = timesheetManager
				.getListForApproval(employee.getEmpNumber(), employeeManager);
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
	public String gotoTimesheet(TimeSheet timesheet, boolean approve) {
		this.timesheet = timesheet;
		this.approve = approve;
		return viewNavigation;
	}
	
	public String approveTimesheet(boolean approved) {
		timesheet.setApproval(approved);
		timesheetManager.merge(timesheet);
		return approveListNavigation;
	}
	
	public void setTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
	}
	
	public String saveChanges() {
		vacationDaysTaken();
		calculateWeekTotalHours();
		for(int i = 1; i < 9; i++){
			calculateTotaldayhours(i);
		}
		timesheet.setApproval(false);
		timesheet.setSubmitted(false);
		timesheetManager.merge(timesheet);
		//shouldn't be needed anymore because theres an init now for the display page that
		//automatically refreshes: refreshTimeSheet();
		return "DisplayTimesheets";
	}
	
	public void timesheetInit() {
		if (timesheet == null)
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
		//shouldn't be needed anymore because theres an init now for the display page that
		//automatically refreshes: refreshTimeSheet();
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
		calculateWeekTotalHours();
		for(int i = 1; i < 9; i++){
			calculateTotaldayhours(i);
		}
		timesheetManager.persist(timesheet);
		timesheetList.add(timesheet);
		return "DisplayTimesheets";
	}
	
	public String getTimeSheetRowNumber(TimeSheetRow row) {
		return row.getTimeSheetRowId().substring(row.getTimeSheetRowId().lastIndexOf('|') + 1);
	}
	
	public String editTimeCancel(){
		//refreshTimeSheet();
		return "DisplayTimesheets";
	}
	
	public String CreateTimesheetCancel(){
		//refreshTimeSheet();
		return "CancelCreateTimesheet";
	}
	
	public String cancelEditTimesheet(){
		//refreshTimeSheet();
		return "cancelEditTimesheet";
	}
	
	public String cancelViewTimesheet(){
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
	
	public void calculateWeekTotalHours(){
		double hours = 0.0;
		//boolean isValid = true;
		for(TimeSheetRow r : timesheet.getTimeSheetRows()) {
			r.setWeekTotalHrs((hours = (r.getMonHrs() + r.getTuesHrs() 
				+ r.getWedHrs() + r.getThursHrs() + r.getFriHrs() 
				+ r.getSatHrs() + r.getSunHrs() + r.getFlexTimeHrs())));
			/*if(hours < 40.0 ){
				isValid = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error Hours is Less than 40"));
			}
			*/
		}
	}
	
	public void calculateTotaldayhours(int day) {
		double hours = 0.0;
		for(TimeSheetRow r : timesheet.getTimeSheetRows()) {
			switch (day) {
				case 1: 
					hours += r.getSatHrs();
					timesheet.setSatTotalHrs(hours);
					break;
				case 2:
					hours += r.getSunHrs();
					timesheet.setSunTotalHrs(hours);
					break;
				case 3:
					hours += r.getMonHrs();
					timesheet.setMonTotalHrs(hours);
					break;
				case 4:
					hours += r.getTuesHrs();
					timesheet.setTuesTotalHrs(hours);
					break;
				case 5:
					hours += r.getWedHrs();
					timesheet.setWedTotalHrs(hours);
					break;
				case 6:
					hours += r.getThursHrs();
					timesheet.setThursTotalHrs(hours);
					break;
				case 7:
					hours += r.getFriHrs();
					timesheet.setFriTotalHrs(hours);
					break;
				case 8:
					hours += r.getWeekTotalHrs();
					timesheet.setOverallTotalHrs(hours);
					if(hours > 40) {
						employee.setFlexHrs((hours-40));
						employeeManager.merge(employee);
					}
					break;
				case 9: 
					hours += r.getFlexTimeHrs();
					timesheet.setflextimeHrs(hours);
					break;
			}
		}
	}
	
	public void submitTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
		for(TimeSheetRow r : timesheet.getTimeSheetRows()){
			if(r.getWeekTotalHrs() < 40.0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Submit, hours is less than 40!", null));
				return ;
			}
		}
		
		timesheet.setSubmitted(true);
		timesheetManager.merge(this.timesheet);
	}
}
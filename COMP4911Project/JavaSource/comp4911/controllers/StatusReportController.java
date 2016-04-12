package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.EmployeeManager;
import comp4911.managers.StatusReportManager;
import comp4911.models.Project;
import comp4911.models.WorkPackage;
import comp4911.models.StatusReport;


@Named("statusReportControl")
@SessionScoped
public class StatusReportController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1845883800219294447L;
	
	private final String listNavigation = "DisplayStatusReport";
	private final String editNavigation = "EditStatusReport";
	private final String addNavigation = "AddStatusReport";
	private final String viewNavigation = "ViewStatusReport";
	private final String workPackageNavigation = "DisplayWorkPackages";

	@Inject
	private WorkPackage workPack;
	
	@Inject
	private StatusReport statusReport;
	
	@Inject
	private Project project;
	
	@Inject
	private StatusReportManager statusReportManager;
	
	@Inject
	private EmployeeManager empManager;
	
//	@Inject
//	private EmployeeWPManager empWPManager;
	
//	private List<Employee> empList;
	
	private List<StatusReport> statusReportList;
	
	public List<StatusReport> getStatusReportList() {
		return statusReportList;
	}

	public void setStatusReportList(List<StatusReport> statusReportList) {
		this.statusReportList = statusReportList;
	}
	
	// SR constructor
	public StatusReportController() {
		System.out.println("SR Constructor called");
		statusReportList = new ArrayList<StatusReport>();
	}
	
	public String getListNavigation() {
		return "DisplayStatusReport";
	}

	// get all status reports in db
	public StatusReport getStatusReport() {
		System.out.println("Get StatusReport called");
//		statusReportList = statusReportManager.getAll();
		statusReportList = statusReportManager.getAllByWorkPackage(workPack.getWpId());
		System.out.println(statusReportList.size() + " status report list size");
		return statusReport;
	}
	
	// refresh list of status reports 
	public void refreshList(){
		statusReportList = statusReportManager.getAllByWorkPackage(workPack.getWpId());
	}
	
	// method to get status report list
	public void getStatusReportList(List<StatusReport> statusReportList) {
		this.statusReportList = statusReportList;
	}
	
	// view a list of status report from workpackage list
	public String viewSR(WorkPackage workPack) {
		this.workPack = workPack;
		statusReportList = statusReportManager.getAllByWorkPackage(workPack.getWpId());
		return "DisplayStatusReport";
	}
	
	// get a specific status report
	public StatusReport getReport() {
		return statusReport;
	}

	// set a specific status report
	public void setStatusReport(StatusReport statusReport) {
		this.statusReport= statusReport;
	}
	
	// add status report
	public String addSR() {
		statusReportManager.persist(statusReport);
		refreshList();
		statusReport = null;
		return "DisplayStatusReport";
	}
	
	// going to status report page + setting page to specific status report
	public String editWP(StatusReport sr) {
		statusReport = sr;
		return "EditStatusReport";
	}
	
	// saving edit changes in status report
	public String editWP() {
		statusReportManager.merge(statusReport);
		refreshList();
		statusReport= null;			
		return "DisplayStatusReport";
	}
	
	// cancel to list of status reports
	public String cancel() {
		return "DisplayStatusReport";
	}
	
	// cancel to list of work packages
	public String cancelToWorkPackages(){
		return workPackageNavigation;
	}
	
	public String getEmployeeName(String username) {
		System.out.println("getEmployeeName called");
		return empManager.find(Integer.parseInt(username)).getFullName();
	}
	
	public double getPercent(double pc) {
		System.out.println("getPercent called");
		double temp = pc * 100;
		return temp;
	}

	public String editStatusReport(StatusReport statusreport) {
		this.statusReport = statusreport;
		return "EditStatusReport";
	}
	
	public String addStatusReport() {
		this.statusReport = new StatusReport();
		statusReportList = statusReportManager.getAllByWorkPackage(workPack.getWpId());
		statusReport.setStatusReportId(workPack.getWpId() + "|" + (statusReportList.size() + 1));
		//temp
		statusReport
		.setReportDate(new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime()));
		return "AddStatusReport";
	}
	
	public String saveChangesForAdd() {
		statusReportManager.persist(statusReport);
		statusReportList.add(statusReport); //simulates add rather than doing a refresh
		//which is less costly
		return "DisplayStatusReport";
	}
	public String saveChangesForEdit() {
		statusReportManager.merge(statusReport);
		refreshList();
		return "DisplayStatusReport";
	}
	
	public String backToWP(){
		return "DisplayWorkPackages";
	}
	
	
}
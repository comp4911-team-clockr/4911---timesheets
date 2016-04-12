package comp4911.controllers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.EmployeeManager;
import comp4911.managers.MonthlyReportManager;
import comp4911.managers.ProjectManager;
import comp4911.managers.StatusReportManager;
//import comp4911.managers.StatusReportManager;
import comp4911.models.Project;
import comp4911.models.StatusReport;
//import comp4911.models.StatusReport;
//import comp4911.models.TimeSheet;
import comp4911.models.MonthlyReport;
import comp4911.managers.WorkPackageManager;
import comp4911.models.WorkPackage;

@Named("monthlyReportControl")
@SessionScoped
public class MonthlyReportController implements Serializable 
{
	/**
	 * generated serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	private final String listNavigation = "DisplayMonthlyReport";
	private final String viewNavigation = "ViewMonthlyReport";
	
	@Inject
	private MonthlyReport monthlyReport;
	
	@Inject
	private Project project;
	
	@Inject 
	private StatusReportManager srManager;
	
	@Inject
	private WorkPackageManager wpManager;
		
	@Inject
	private MonthlyReportManager monthlyReportManager;
	
	private List<WorkPackage> wpList;
	
	private List<MonthlyReport> monthlyReportList;
	
	private List<StatusReport> srList;
	
//	private MonthlyReport newMonthlyReport;
	
	public List<MonthlyReport> getMonthlyReportList() {
		return monthlyReportList;
	}
	
	public void setMonthlyReportList(List<MonthlyReport> monthlyReportList) {
		this.monthlyReportList = monthlyReportList;
	}

	// MR constructor
	public MonthlyReportController() {
		System.out.println("MR Constructor called");
		monthlyReportList = new ArrayList<MonthlyReport>();
	}

	// get all monthly reports in db
	public MonthlyReport getMonthlyReport() {
		System.out.println("Get MonthlyReport called");
		monthlyReportList = monthlyReportManager.getAll();
		System.out.println(monthlyReportList.size() + " size monthlyreport");
		return monthlyReport;
	}
	
	// refresh list of monthly reports 
	public void refreshList(){
		monthlyReportList = monthlyReportManager.getAllByProject(project.getProjectId());
	}
	
	// method to get monthly report list
	public void getMonthlyReportList(List<MonthlyReport> monthlyReportList) {
		this.monthlyReportList = monthlyReportList;
	}
	
	// view a list of monthly report from project list
	public String viewMR(Project project) {
		this.project = project;
		monthlyReportList = monthlyReportManager.getAllByProject(project.getProjectId());
		return "DisplayMonthlyReport";
	}
			
	public String getListNavigation() {
		return "DisplayMonthlyReport";
	}
	
	// get a specific monthly report
	public MonthlyReport getReport() {
		return monthlyReport;
	}
	
	// set a specific monthly report
	public void setMonthlyReport(MonthlyReport monthlyReport) {
		this.monthlyReport = monthlyReport;
	}
	
	// generate a monthly report based on user request
	public String generateReport()
	{		
		monthlyReport = new MonthlyReport();
		int mrNum = 1;
		double totalMD = 0;
		double totalCost = 0;
		double completion = 0;
		
		System.out.println(project.getProjectId() + " project id");
		wpList = wpManager.getAllByProject(project.getProjectId());

		if (monthlyReportList.size() > 0) 
		{
			MonthlyReport temp = monthlyReportList.get(monthlyReportList.size() - 1);
			System.out.println(temp.getMonthlyReportId() + " last MR id");
			String test = temp.getMonthlyReportId().substring(temp.getMonthlyReportId().lastIndexOf('|')+ 1);
			System.out.println(test + " monthlyreportId");
			mrNum = Integer.parseInt(test);
			mrNum++;
			System.out.println(mrNum + " MR rep num set");
			
			for (int i = 0; i < wpList.size(); i++)
			{
				totalMD += wpManager.getAllWorkPackMD(wpList.get(i).getWpId());
				totalCost += wpManager.getAllWorkPackBudget(wpList.get(i).getWpId());
			}
			System.out.println(totalMD + " total MDs for all work packages");
		}
		monthlyReport.setMonthlyReportId(project.getProjectId() + "|" + Integer.toString(mrNum));
		monthlyReport.setPmEmpId(project.getSupervisor());
		// initial budget cost
		monthlyReport.setpBudgetCost(totalCost);

		// initial MD cost
		monthlyReport.setpBudgetMD(totalMD);
		srList = srManager.getAllByProject(project.getProjectId());
		System.out.println(srList.size() + " size of sr list");
		for (int i = 0; i < srList.size(); i++)
		{
			completion += srList.get(i).getPcComplete();
		}
		completion /= srList.size();
		
		System.out.println(completion + "% complete");
		//srManager.getAllByWorkPackage(wpList.get(index))
		
		//monthlyReport.setPercentComplete()
		//monthlyReport.setTotalMDVar(totalMDVar);
		//monthlyReport.setTotalCostVar(totalCostVar);
		//monthlyReport.setPercentComplete(percentComplete);
		
		System.out.println(totalCost + " total budget");
		System.out.println(totalMD + " total MD");
		
//		monthlyReportManager.getAllByProject(project.getProjectId());
//		temp.setMonthlyReportId("");
//		System.out.println(mrNum);
//		return "RequestMonthlyReport";
		return "DisplayMonthlyReport";
	}
	
	public String addMR() {
		monthlyReportManager.persist(monthlyReport);
		refreshList();
		monthlyReport = null;
		
		return "RequestMonthlyReport";
	}
	
	public String backToProj(){
		return "DisplayProjects";
	}

}
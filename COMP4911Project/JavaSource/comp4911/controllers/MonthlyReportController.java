package comp4911.controllers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.EmployeeManager;
import comp4911.managers.EmployeeWPManager;
import comp4911.managers.MonthlyReportManager;
import comp4911.managers.PayRateManager;
import comp4911.managers.ProjectManager;
import comp4911.managers.StatusReportManager;
import comp4911.managers.TimeSheetManager;
//import comp4911.managers.StatusReportManager;
import comp4911.models.Project;
import comp4911.models.StatusReport;
import comp4911.models.TimeSheet;
import comp4911.models.Employee;
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
	private EmployeeWPManager empWPManager;
	
	@Inject
	private EmployeeManager newEmp;
	
	@Inject
	private TimeSheetManager tsManager; 
	
	@Inject
	private MonthlyReportManager monthlyReportManager;
	
	@Inject
	private PayRateManager prManager;
	
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
		double totalTSHrs = 0;
		double totalTSCost = 0;
		
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
		
		List<Employee> empList = new ArrayList<Employee>();
		List<Integer> empNumList = new ArrayList<Integer>();
		for (int i = 0; i < srList.size(); i++)
		{
			completion += srList.get(i).getPcComplete();
//			empList.addAll(empWPManager.listEmpByWP(wpList.get(i).getWpId(), newEmp));
//			empNumList.addAll(empWPManager.listEmpNumByWP(wpList.get(i).getWpId()));
		}
		completion /= srList.size();
		
//		List<TimeSheet> tsList = tsManager.getListByEmp(empNumList);
//
//		if (tsList.size() == empList.size())
//		{
//			for (int i = 0; i < empList.size(); i++)
//			{
//				String tempPR = empList.get(i).getPayRateId(); 
//				totalTSHrs += tsList.get(i).getOverallTotalHrs();
//				totalTSCost += prManager.find(tempPR).getCostInMD() * totalTSHrs;
//			}	
//		}
		
		System.out.println(completion + "% complete");
		//srManager.getAllByWorkPackage(wpList.get(index))
//		double finalMD = srManager.
		monthlyReport.setPercentComplete(completion);
//		monthlyReport.setTotalMDVar(totalMD/(totalTSHrs/8));
//		monthlyReport.setTotalCostVar(totalCost/totalTSCost);
		
		System.out.println(totalCost + " total budget");
		System.out.println(totalMD + " total MD");
		
		//System.out.println(totalCost/totalTSCost + " total cost var");
		//System.out.println(totalMD/(totalTSHrs/8) + " total MD var");
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
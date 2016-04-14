package comp4911.controllers;
import java.io.Serializable;
import java.text.DecimalFormat;
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
		System.out.println(project.getInitBudget() + " project initial budget");
		wpList = wpManager.getAllByProject(project.getProjectId());
		List<Employee> empList = new ArrayList<Employee>();
		List<Integer> empNumList = new ArrayList<Integer>();
		
		if (wpList.isEmpty())
		{
			System.out.println("No work packages in project");
			return "DisplayMonthlyReport";
		}
		else
		{
			System.out.println("adding employees to empList");
			for (int i = 0; i < wpList.size(); i++)
			{
				totalMD += wpManager.getAllWorkPackMD(wpList.get(i).getWpId());
				totalCost += wpManager.getAllWorkPackBudget(wpList.get(i).getWpId());
				empList.addAll(empWPManager.listEmpByWP(wpList.get(i).getWpId(), newEmp));
			}
			System.out.println(empList.size() + " empList size");
			System.out.println(totalMD + " total MDs for all work packages");
			
			for (int i = 0; i < wpList.size(); i++)
			{
				empNumList.addAll(empWPManager.listEmpNumByWP(wpList.get(i).getWpId()));
				System.out.println(empWPManager.listEmpNumByWP(wpList.get(i).getWpId()));
			}
			System.out.println(empNumList.size() + " empNumList size");
		}
		
		if (monthlyReportList.size() > 0) 
		{
			MonthlyReport temp = monthlyReportList.get(monthlyReportList.size() - 1);
			System.out.println(temp.getMonthlyReportId() + " last MR id");
			String test = temp.getMonthlyReportId().substring(temp.getMonthlyReportId().lastIndexOf('|')+ 1);
			System.out.println(test + " monthlyreportId");
			mrNum = Integer.parseInt(test);
			mrNum++;
			System.out.println(mrNum + " MR rep num set");
		}
		//monthlyReportManager.getAllByProject(project.getProjectId());
		//temp.setMonthlyReportId("");
		//System.out.println(mrNum);

		monthlyReport.setMonthlyReportId(project.getProjectId() + "|" + Integer.toString(mrNum));
		monthlyReport.setPmEmpId(project.getSupervisor());
		
		// initial budget cost
		monthlyReport.setpBudgetCost(project.getInitBudget());
		
		// initial MD cost
		int plannedMD = project.getManDaysDS() 
				+ project.getManDaysSS()
				+ project.getManDaysP1()
				+ project.getManDaysP2()
				+ project.getManDaysP3()
				+ project.getManDaysP4()
				+ project.getManDaysP5();
				
		monthlyReport.setpBudgetMD(plannedMD);
		srList = srManager.getAllByProject(project.getProjectId());
		System.out.println(srList.size() + " size of sr list");
		
		monthlyReport.setReportDate(new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime()));
		
		if (srList.size() > 0)
		{
			System.out.println("getting % complete from status reports");
			for (int i = 0; i < srList.size(); i++)
			{
				completion += srList.get(i).getPcComplete();
				
			}
			System.out.println(completion + " total complete%");
			completion = completion / srList.size();
			System.out.println(completion + " complete%");
		}
		else
		{
			System.out.println("No status reports in wp");
			return "DisplayMonthlyReport";
		}
		
		List<TimeSheet> tsList = tsManager.getListByEmp(empNumList);
		System.out.println(tsList.size() + " timesheet list size");
		System.out.println(empList.size() + " employee list size");
		
		if (!tsList.isEmpty())
		{
			for (int i = 0; i < tsList.size(); i++)
			{
				int empTSNum = tsList.get(i).getEmpNumber();
				System.out.println(empTSNum + " emp num");
				totalTSHrs += tsList.get(i).getOverallTotalHrs();
				System.out.println(totalTSHrs + " emp ts total hrs");
				String prId = "";
				for (int j = 0; j < empList.size(); j++)
				{
					int temp = empList.get(j).getEmpNumber();
					if (temp == empTSNum)
					{
						prId = empList.get(j).getPayRateId();
						System.out.println(prId + " PR id");	
						if (prManager.find(prId) != null)
						{
							totalTSCost += prManager.find(prId).getCostInMD() * totalTSHrs;
						}
						System.out.println(totalTSCost + " added budget cost");

					}
				}
			}
			System.out.println(totalTSHrs + " total budget hrs");
		}
		else
		{
			System.out.println("No employee timesheets in wp/project");
			return "DisplayMonthlyReport";
		}
		
		System.out.println(completion + "% complete");
		
		monthlyReport.setPercentComplete(completion);
		monthlyReport.setToDateMD(totalTSHrs/8);
		monthlyReport.setToDateCost(totalTSCost);
		double pcCost = (totalTSCost/project.getInitBudget())*100;
		double pcMD = ((totalTSHrs/8)/plannedMD)*100;
		
		monthlyReport.setTotalMDVar(Math.round(pcMD));
		monthlyReport.setTotalCostVar(Math.round(pcCost));
		
//		System.out.println(totalCost + " total budget planned");
//		System.out.println(totalTSCost + " total budget actual");
//		System.out.println(totalMD + " total MD planned");
//		System.out.println((totalTSHrs/8) + " total MD actual");
//		System.out.println(((totalTSHrs/8)/totalMD) + " total MD variance");
//		System.out.println(totalTSCost/totalCost + " total Cost variance");
		
		monthlyReportManager.persist(monthlyReport);
		refreshList();
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
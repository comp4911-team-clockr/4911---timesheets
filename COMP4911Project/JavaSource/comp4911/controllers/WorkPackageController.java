package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.EmployeeManager;
import comp4911.managers.EmployeeWPManager;
import comp4911.managers.WorkPackageManager;
import comp4911.models.Employee;
import comp4911.models.EmployeeWPList;
import comp4911.models.Project;
import comp4911.models.WorkPackage;

@Named("workPackControl")
@SessionScoped
public class WorkPackageController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String listNavigation = "DisplayWorkPackages";
	private final String editNavigation = "EditWorkPackage";
	private final String addNavigation = "AddWorkPackage";
	private final String viewNavigation = "ViewWorkPackage";
	private final String projectNavigation = "DisplayProject";

	private final String validWPNumPattern = "[A-Z]{1}[0-9]{4}";
	private final String validRENumPattern = "[0-9]{6}";

	private boolean idReadOnly = true;
	private boolean allowWPAssignment;

	@Inject
	private WorkPackage workPack;

	@Inject
	private Project project;

	@Inject
	private WorkPackageManager workPackManager;

	@Inject
	private EmployeeManager empManager;

	@Inject
	private EmployeeWPManager empWPManager;

	private Employee employee;

	private boolean validWP = true;

	private List<WorkPackage> workPackList;

	private List<Employee> empList;

	private List<Employee> empREList;

	public WorkPackageController() {
		System.out.println("WP Constructor called");
		workPackList = new ArrayList<WorkPackage>();
		empList = new ArrayList<Employee>();
		empREList = new ArrayList<Employee>();
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public String gotoList(Project project) {
		this.project = project;
		refreshList();
		return listNavigation;
	}

	public WorkPackage getWorkPackage() {
		System.out.println("Get WorkPackage called");
		workPackList = workPackManager.getAll();
		System.out.println(workPackList.size() + " size");
		return workPack;
	}

	public void refreshList(){
		workPackList = workPackManager.getAllByProject(project.getProjectId());
	}

	public void refreshEmpList(WorkPackage wp) {
		empList = empWPManager.listEmpByWP(wp.getWpId(), empManager);
		if (empList == null)
			empList = new ArrayList<Employee>(); 
	}

	public List<WorkPackage> getWorkPackList() {
		System.out.println("Get WorkPackage List");
		return workPackList;
	}

	public void setWorkPackList(List<WorkPackage> workPackList) {
		this.workPackList = workPackList;
	}

	public WorkPackage getWorkPack() {
		return workPack;
	}

	public void setWorkPack(WorkPackage workPack) {
		this.workPack = workPack;
	}

	public List<Employee> getEmpREList() {
		return empREList;
	}

	public String viewWP(WorkPackage workPack) {
		this.workPack = workPack;
		refreshEmpList(workPack);
		return viewNavigation;
	}

	public String addWP(Project project) {
		workPack = new WorkPackage();
		workPack.setActive(true);
		setIdReadOnly(true);
		workPack.setProjectId(project.getProjectId());
		//for dropdown list
		empREList = empWPManager.listEmpByProj(workPack.getProjectId(), empManager);
		return addNavigation;
	}

	public String addWPSplash(){
		workPack = new WorkPackage();
		workPack.setActive(true);
		setIdReadOnly(false);
		//for dropdown list
		empREList = empManager.getAll();
		validWP = true;
		return "addWPSplash";
	}

	public String addWP() {
		if (validateAll()) {
			//put this here instead so that adding from splash page still works
			String wpID = project.getProjectId() + "|" + (workPackList.size() + 1);
			workPack.setWpId(wpID);
			workPackManager.persist(workPack);
			refreshList();

			//adding to the many to many table
			String wpFlag = workPack.getWpId() + "|" + Integer.parseInt(workPack.getRespId());
			if (empWPManager.find(wpFlag) == null) {
				EmployeeWPList tempEmpWP = new EmployeeWPList();
				tempEmpWP.setEmpNum(Integer.parseInt(workPack.getRespId()));
				tempEmpWP.setProjectId(workPack.getProjectId());
				tempEmpWP.setWpID(workPack.getWpId());
				tempEmpWP.setWpEmpId(wpFlag);
				empWPManager.persist(tempEmpWP);
			}
			//resets workPack
			workPack = null;
			return listNavigation;
		}
		return "";
	}

	// navigating to page + setting page to specific wp
	public String editWP(WorkPackage wp) {
		workPack = wp;
		//for dropdown list
		empREList = empWPManager.listEmpByProj(workPack.getProjectId(), empManager);
		return editNavigation;
	}

	// saving edit changes
	public String editWP() {
		if (validateAll()) {
			workPackManager.merge(workPack);
			refreshList();
			//adding to the many to many table
			String wpFlag = workPack.getWpId() + "|" + Integer.parseInt(workPack.getRespId());
			if (empWPManager.find(wpFlag) == null) {
				EmployeeWPList tempEmpWP = new EmployeeWPList();
				tempEmpWP.setEmpNum(Integer.parseInt(workPack.getRespId()));
				tempEmpWP.setProjectId(workPack.getProjectId());
				tempEmpWP.setWpID(workPack.getWpId());
				tempEmpWP.setWpEmpId(wpFlag);
				empWPManager.persist(tempEmpWP);
			}
			//resets workPack
			workPack = null;			
			return listNavigation;
		}
		return "";
	}

	public String deleteWP(WorkPackage wp){
		workPackManager.remove(wp);
		refreshList();
		return listNavigation;
	}

	public String cancel() {
		return listNavigation;
	}

	public String cancelToProject(){
		return projectNavigation;
	}

	public boolean isValidWP() {
		return validWP;
	}

	public boolean validateAll() {
		validWP = true;
		if (!validWPNum(workPack.getWpNum())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not a valid Work Package Number. ex: B1111", null));
			validWP = false;
		}

		if (!validRespEngPat(workPack.getRespId())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not a valid Responsibility Engineer Number. ex: 000001", null));
			validWP = false;
		} else {
			if (!validEmp(workPack.getRespId())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "This employee does not exist.", null));
				validWP = false;
			} else {
				if (!validEmpInProj(workPack.getProjectId(), workPack.getRespId())) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "This employee is not in this project.", null));
					validWP = false;
				}
			}
		}
		return validWP;
	}

	public boolean validWPNum(String wpNum) {
		return Pattern.matches(validWPNumPattern, wpNum);
	}

	public boolean validRespEngPat(String reNum) {
		return Pattern.matches(validRENumPattern, reNum);
	}

	public boolean validEmp(String reNum) {
		int id = Integer.parseInt(reNum);
		if (empManager.find(id) != null)
			return true;
		return false;
	}

	public boolean validEmpInProj(int projId, String reNum) {
		int empNo = Integer.parseInt(reNum);
		String projFlag = projId + "|0|" + empNo;
		if (empWPManager.find(projFlag) != null)
			return true;
		return false;
	}

	public String backToProjects(){
		return "DisplayProjects";
	}

	public boolean isIdReadOnly() {
		return idReadOnly;
	}

	public void setIdReadOnly(boolean idReadOnly) {
		this.idReadOnly = idReadOnly;
	}

	public String assignEmployee(Employee employee){
		this.employee = employee;
		workPackList = workPackManager.getAll();
		allowWPAssignment = true;
		return listNavigation;
	}

	public String assignToWP(WorkPackage wp) {
		EmployeeWPList emp_wp = new EmployeeWPList();
		String projFlag = wp.getProjectId() + "|0|" + employee.getEmpNumber();
		String empWpId = wp.getWpId() + "|" + employee.getEmpNumber();
		if (empWPManager.find(projFlag) != null) {
			if (empWPManager.find(empWpId) == null) {
				emp_wp.setWpEmpId(empWpId);
				emp_wp.setEmpNum(employee.getEmpNumber());
				emp_wp.setWpID(wp.getWpId());
				emp_wp.setProjectId(wp.getProjectId());
				empWPManager.persist(emp_wp);
				allowWPAssignment = false;
				refreshEmpList(wp);
				return viewNavigation;
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee is already assigned to this Work Package.", null));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee is not assigned to the Project for this Work Package.", null));
		}
		return "";
	}

	public boolean getAllowWPAssignment() {
		return allowWPAssignment;
	}

	public String cancelViewPackage(){
		return "cancelViewPackage";
	}
}
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
	
	private final String validWPNumPattern = "[A-Z]{1}[0-9]{4}";
	private final String validRENumPattern = "[0-9]{6}";
	
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
	
	private List<WorkPackage> workPackList;
	
	private List<Employee> empList;
	
	public WorkPackageController() {
		System.out.println("WP Constructor called");
		workPackList = new ArrayList<WorkPackage>();
		empList = new ArrayList<Employee>();
	}
	
	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public String gotoList(Project project) {
		this.project = project;
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
		refreshList();
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
	
	public String viewWP(WorkPackage workPack) {
		this.workPack = workPack;
		refreshEmpList(workPack);
		return viewNavigation;
	}
	public String addWP(Project project) {
		workPack = new WorkPackage();
		workPack.setActive(true);
		workPack.setProjectId(project.getProjectId());
		String wpID = project.getProjectId() + "|" + (workPackList.size() + 1);
		workPack.setWpId(wpID);
		return addNavigation;
	}
	
	public String addWP() {
		if (validateAll()) {
			workPackManager.persist(workPack);
			refreshList();
			workPack = null;
			return listNavigation;
		}
		return "";
	}
	
	public String editWP(WorkPackage wp) {
		workPack = wp;
		return editNavigation;
	}
	
	public String editWP() {
		if (validateAll()) {
			workPackManager.merge(workPack);
			refreshList();
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
	
	public boolean validateAll() {
		boolean validWP = true;
		if (!validWPNum(workPack.getWpNum())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Not a valid Work Package Number. ex: B1111"));
			validWP = false;
		}
		
		if (!validRespEngPat(workPack.getRespId())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Not a valid Responsibility Engineer Number. ex: 000001"));
			validWP = false;
		} else {
			if (!validEmp(workPack.getRespId())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("This employee does not exist."));
				validWP = false;
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
}
package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.CredentialManager;
import comp4911.managers.EmployeeManager;
import comp4911.managers.EmployeeWPManager;
import comp4911.managers.ProjectManager;
import comp4911.models.Employee;
import comp4911.models.EmployeeWPList;
import comp4911.models.Project;
import comp4911.models.WorkPackage;

@Named("projectControl")
@SessionScoped
public class ProjectController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Project project;

	@Inject 
	private ProjectManager projectManager;

	private List<Project> projectList;

	private List<Employee> pmList;

	@Inject
	private Project editProject;

	@Inject
	private EmployeeWPManager empWPManager;

	@Inject
	private EmployeeManager empManager;

	@Inject
	private CredentialManager credManager;

	private Employee employee;

	private boolean projectAssignment;

	public boolean isProjectAssignment() {
		return projectAssignment;
	}

	private List<Employee> empList;

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public Project getEditProject() {
		return editProject;
	}

	public void setEditProject(Project editProject) {
		this.editProject = editProject;
	}

	public ProjectController() {
		System.out.println("Project Constructor called");
		projectList = new ArrayList<Project>();
		empList = new ArrayList<Employee>();
		pmList = new ArrayList<Employee>();
	}

	public List<Employee> getPmList() {
		return pmList;
	}

	public Project getProject() {
		System.out.println("Get Project called");
		projectList = projectManager.getAll();
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public void refreshList(){
		projectList = projectManager.getAll();
	}

	public void refreshPMList() {
		pmList = empManager.getAllProjectManagers(credManager);
	}

	public List<Project> getProjectList() {
		System.out.println("Get Project List");
		refreshList();
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public String selectEditProject(Project proj, String navigation){
		editProject = proj;
		refreshEmpList(proj);
		return navigation;
	}

	public double calculateRemainingBudget(Project proj) {
		return proj.getInitBudget() - proj.getFinalBudget();
	}

	public long calculateDaysRemaining(Project proj) {
		long diff = proj.getEstCompletionDate().getTime() - proj.getIssueDate().getTime();
		return java.util.concurrent.TimeUnit.DAYS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);
	}

	public String updateProject(){
		projectManager.merge(editProject);
		//adding to the many to many table
		String projFlag = editProject.getProjectId() + "|0|" + editProject.getSupervisor();
		if (empWPManager.find(projFlag) == null) {
			EmployeeWPList tempEmpWP = new EmployeeWPList();
			tempEmpWP.setEmpNum(editProject.getSupervisor());
			tempEmpWP.setProjectId(editProject.getProjectId());
			tempEmpWP.setWpID(null);
			tempEmpWP.setWpEmpId(projFlag);
			empWPManager.persist(tempEmpWP);
		}		
		editProject = null;
		refreshList();
		return "DisplayProjects";
	}

	public String addProjectSetup() {
		refreshList();
		project = new Project();
		int id = projectList.size() + 1;
		project
		.setIssueDate(new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime()));
		project.setProjectId(id);
		project.setActive(true);
		return "addProject";
	}

	public String addProject(){
		projectManager.persist(project);
		//adding to the many to many table
		String projFlag = project.getProjectId() + "|0|" + project.getSupervisor();
		if (empWPManager.find(projFlag) == null) {
			EmployeeWPList tempEmpWP = new EmployeeWPList();
			tempEmpWP.setEmpNum(project.getSupervisor());
			tempEmpWP.setProjectId(project.getProjectId());
			tempEmpWP.setWpID(null);
			tempEmpWP.setWpEmpId(projFlag);
			empWPManager.persist(tempEmpWP);
		}
		project = null;
		refreshList();
		return "projects";
	}

	public String selectEmpToProject(Employee employee){
		this.employee = employee;
		projectList = projectManager.getAll();
		projectAssignment = true;
		return "EmployeeToProjects";
	}

	public void refreshEmpList(Project proj) {
		empList = empWPManager.listEmpByProj(proj.getProjectId(), empManager);
		if (empList == null)
			empList = new ArrayList<Employee>(); 
	}

	public String assignEmpToProject(Project proj){
		EmployeeWPList employeeProject = new EmployeeWPList();
		String projectEmp = proj.getProjectId() + "|0|" + employee.getEmpNumber();
		/* There's no need to set the work package id in this case because at this
		 * point, the user isn't even assigned to a work package yet. Instead,
		 * we simply give the WpId a null value.*/
		if(empWPManager.find(projectEmp) == null){
			employeeProject.setWpEmpId(projectEmp);
			employeeProject.setProjectId(proj.getProjectId());
			employeeProject.setEmpNum(employee.getEmpNumber());
			employeeProject.setWpID(null);
			empWPManager.persist(employeeProject);
			projectAssignment = false;
			refreshEmpList(proj);

			return "DisplayEmployees";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Employee is already assigned to this Project.", null));
		}
		return "";
	}

	public String deleteProject(Project proj){
		projectManager.remove(projectManager.find(proj.getProjectId()));

		refreshList();

		return "DisplayProjects";
	}

	public String cancelNewProject(){
		return "reloadProjects";
	}

	public String cancelEditProject(){
		return "cancelEditProject";
	}

	public String cancelViewProject(){
		return "cancelViewProject";
	}
}

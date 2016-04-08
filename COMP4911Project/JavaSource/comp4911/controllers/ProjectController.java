package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	@Inject
	private Project editProject;
	
	@Inject
	private EmployeeWPManager empWPManager;
	
	@Inject
	private EmployeeManager empManager;
	
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

	public List<Project> getProjectList() {
		System.out.println("Get Project List");
		refreshList();
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	
	public String selectEditProject(Project proj, String something){
		editProject = proj;
		refreshEmpList(proj);
		return something;
	}
	
	public double calculateRemainingBudget(Project proj) {
		return proj.getInitBudget() - proj.getFinalBudget();
	}
	
	public long calculateDaysRemaining(Project proj) {
		long diff = proj.getEstCompletionDate().getTime() - proj.getIssueDate().getTime();
		return java.util.concurrent.TimeUnit.DAYS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);
	}
	
	public String updateProject(int id){
		Project temp = projectManager.find(id);
		
		temp.setProjectId(editProject.getProjectId());
		temp.setProjName(editProject.getProjName());
		temp.setSupervisor(editProject.getSupervisor());
		//project assistant set to 1, maybe change later
		temp.setProjAssistant(1);
		temp.setEstCompletionDate(editProject.getEstCompletionDate());
		temp.setIssueDate(editProject.getIssueDate());
		temp.setProposal(editProject.getProposal());
		temp.setInitBudget(editProject.getInitBudget());
		temp.setRO1Budget(editProject.getRO1Budget());
		temp.setRO2Budget(editProject.getRO2Budget());
		temp.setFinalBudget(editProject.getFinalBudget());
		temp.setManDaysP1(editProject.getManDaysP1());
		temp.setManDaysP2(editProject.getManDaysP2());
		temp.setManDaysP3(editProject.getManDaysP3());
		temp.setManDaysP4(editProject.getManDaysP4());
		temp.setManDaysP5(editProject.getManDaysP5());
		temp.setManDaysDS(editProject.getManDaysDS());
		temp.setManDaysSS(editProject.getManDaysSS());
		temp.setDesc(editProject.getDesc());
		
		projectManager.merge(temp);
		
		return "editProject";
	}
	
	public String addProject(){
		Project temp = new Project();
		int id = projectList.size() + 1;
		
		temp.setProjectId(id);
		temp.setProjName(project.getProjName());
		temp.setSupervisor(project.getSupervisor());
		temp.setProjAssistant(1);
		temp.setEstCompletionDate(project.getEstCompletionDate());
		temp.setIssueDate(project.getIssueDate());
		temp.setProposal(project.getProposal());
		temp.setInitBudget(project.getInitBudget());
		temp.setRO1Budget(project.getRO1Budget());
		temp.setRO2Budget(project.getRO2Budget());
		temp.setFinalBudget(project.getFinalBudget());
		temp.setManDaysP1(project.getManDaysP1());
		temp.setManDaysP2(project.getManDaysP2());
		temp.setManDaysP3(project.getManDaysP3());
		temp.setManDaysP4(project.getManDaysP4());
		temp.setManDaysP5(project.getManDaysP5());
		temp.setManDaysDS(project.getManDaysDS());
		temp.setManDaysSS(project.getManDaysSS());
		temp.setDesc(project.getDesc());
		
		projectManager.persist(temp);
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
		String wpEmp = proj.getProjectId() + "|" + employee.getEmpNumber();
		if(empWPManager.find(projectEmp) == null){
			employeeProject.setWpEmpId(projectEmp);
			employeeProject.setProjectId(proj.getProjectId());
			employeeProject.setEmpNum(employee.getEmpNumber());
			employeeProject.setWpID(wpEmp);
			empWPManager.persist(employeeProject);
			projectAssignment = false;
			refreshEmpList(proj);
			
			return "DisplayEmployees";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee is already assigned to this Project.", null));
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

package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.ProjectManager;
import comp4911.models.Project;

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
	
	public Project getEditProject() {
		return editProject;
	}

	public void setEditProject(Project editProject) {
		this.editProject = editProject;
	}

	public ProjectController() {
		System.out.println("Project Constructor called");
		projectList = new ArrayList<Project>();
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

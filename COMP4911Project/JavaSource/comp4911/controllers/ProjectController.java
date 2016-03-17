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
	
	public ProjectController() {
		System.out.println("Constructor called");
		projectList = new ArrayList<Project>();
	}

	public Project getProject() {
		System.out.println("getProject() called");
		projectList = projectManager.getAll();
		System.out.println(projectList.size() + " size");
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
		System.out.println("WTF getProjectList");
		refreshList();
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	
}

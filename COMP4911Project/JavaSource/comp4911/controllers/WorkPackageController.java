package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.WorkPackageManager;
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
	
	@Inject
	private WorkPackage workPack;
	
	@Inject
	private Project project;
	
	@Inject
	private WorkPackageManager workPackManager;
	
	private List<WorkPackage> workPackList;
	
	public WorkPackageController() {
		System.out.println("Constructor called");
		workPackList = new ArrayList<WorkPackage>();
	}
	public String gotoList(Project project) {
		this.project = project;
		return listNavigation;
	}
	
	public WorkPackage getWorkPackage() {
		System.out.println("getWorkPackage() called");
		workPackList = workPackManager.getAll();
		System.out.println(workPackList.size() + " size");
		return workPack;
	}
	
	public void refreshList(){
		workPackList = workPackManager.getAllByProject(project.getProjectId());
	}
	
	public List<WorkPackage> getWorkPackList() {
		System.out.println("getWorkPackList");
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
		workPackManager.persist(workPack);
		refreshList();
		workPack = null;
		return listNavigation;
	}
	
	public String editWP(WorkPackage wp) {
		workPack = wp;
		return editNavigation;
	}
	
	public String editWP() {
		workPackManager.merge(workPack);
		refreshList();
		workPack = null;
		return listNavigation;
	}
	public String deleteWP(WorkPackage wp){
		workPackManager.remove(wp);
		refreshList();
		return listNavigation;
	}
	
	public String cancel() {
		return listNavigation;
	}
}
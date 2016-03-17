package comp4911.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.managers.WorkPackageManager;
import comp4911.models.WorkPackage;

@Named("workPackControl")
@SessionScoped
public class WorkPackageController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private WorkPackage workPack;
	
	@Inject
	private WorkPackageManager workPackManager;
	
	private List<WorkPackage> workPackList;
	
	public WorkPackageController() {
		System.out.println("Constructor called");
		workPackList = new ArrayList<WorkPackage>();
	}
	
	public WorkPackage getWorkPackage() {
		System.out.println("getWorkPackage() called");
		workPackList = workPackManager.getAll();
		System.out.println(workPackList.size() + " size");
		return workPack;
	}
	
	public void refreshList(){
		workPackList = workPackManager.getAll();
	}
	
	public List<WorkPackage> getWorkPackList() {
		System.out.println("getWorkPackList");
		refreshList();
		return workPackList;
	}
	
	public void setWorkPackList(List<WorkPackage> workPackList) {
		this.workPackList = workPackList;
	}
	
}
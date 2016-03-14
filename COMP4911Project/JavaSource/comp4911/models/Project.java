//package comp4911.models;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="Project")
//public class Project implements Serializable{
//
//	/**
//	 * Default Serial Version ID
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name="projectId")
//	private int projectId;
//	
//	@Column(name="projName")
//	private String projName;
//	
//	@Column(name="roleId")
//	private String roleId;
//	
//	@Column(name="manDays")
//	private int manDays;
//	
//	@Column(name="empNum")
//	private int empNum;
//	
//	@Column(name="empProjList")
//	private List<Employee> empProjList;
//	
//	@Column(name="wpList")
//	private List<WorkPackage> wpList;
//	
//	@Column(name="issueDate")
//	private Date issueDate;
//	
//	public Project(){}
//
//	public int getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(int projectId) {
//		this.projectId = projectId;
//	}
//
//	public String getProjName() {
//		return projName;
//	}
//
//	public void setProjName(String projName) {
//		this.projName = projName;
//	}
//
//	public String getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(String roleId) {
//		this.roleId = roleId;
//	}
//
//	public int getManDays() {
//		return manDays;
//	}
//
//	public void setManDays(int manDays) {
//		this.manDays = manDays;
//	}
//
//	public int getEmpNum() {
//		return empNum;
//	}
//
//	public void setEmpNum(int empNum) {
//		this.empNum = empNum;
//	}
//
//	public List<Employee> getEmpProjList() {
//		return empProjList;
//	}
//
//	public void setEmpProjList(List<Employee> empProjList) {
//		this.empProjList = empProjList;
//	}
//
//	public List<WorkPackage> getWpList() {
//		return wpList;
//	}
//
//	public void setWpList(List<WorkPackage> wpList) {
//		this.wpList = wpList;
//	}
//
//	public Date getIssueDate() {
//		return issueDate;
//	}
//
//	public void setIssueDate(Date issueDate) {
//		this.issueDate = issueDate;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	
package comp4911.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EmployeeWPList")
public class EmployeeWPList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="WpEmpId")
	private String wpEmpId;
	
	@Column(name="WpId")
	private String wpID;
	
	@Column(name="EmpNum")
	private int empNum;
	
	@Column(name="ProjectId")
	private int projectId;

	public String getWpEmpId() {
		return wpEmpId;
	}
	public void setWpEmpId(String wpEmpId) {
		this.wpEmpId = wpEmpId;
	}
	public String getWpID() {
		return wpID;
	}
	public void setWpID(String wpID) {
		this.wpID = wpID;
	}
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}

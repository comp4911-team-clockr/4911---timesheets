package comp4911.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="WorkPackage")
public class WorkPackage implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WpId")
	private int wpId;
	
	@Column(name="WpNum")
	private int wpNum;
	
	@Column(name="WpTitle")
	private String wpTitle;
	
	@Column(name="Contractor")
	private String contractor;
	
	@Column(name="RespEngId")
	private String respId;
	
	@Column(name="PayRateId")
	private String payRateId;
	
	@Column(name="ManDays")
	private int manDays;
	
	@Column(name="ProjectId")
	private int projectId;
	
	@Column(name="EmpNum")
	private int supervisor;
	
	@OneToMany(mappedBy = "workpackage", fetch = FetchType.EAGER)
	private List<Employee> empWpList;

	public WorkPackage(){}
	
	public int getWpId() {
		return wpId;
	}

	public void setWpId(int wpId) {
		this.wpId = wpId;
	}

	public int getWpNum() {
		return wpNum;
	}

	public void setWpNum(int wpNum) {
		this.wpNum = wpNum;
	}

	public String getWpTitle() {
		return wpTitle;
	}

	public void setWpTitle(String wpTitle) {
		this.wpTitle = wpTitle;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public void setUserId(int supervisor) {
		this.supervisor = supervisor;
	}

	public String getPayRateId() {
		return payRateId;
	}

	public void setPayRateId(String payRateId) {
		this.payRateId = payRateId;
	}

	public int getManDays() {
		return manDays;
	}

	public void setManDays(int manDays) {
		this.manDays = manDays;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public List<Employee> getEmpWpList() {
		return empWpList;
	}

	public void setEmpWPList(List<Employee> empWpList) {
		this.empWpList = empWpList;
	}
}
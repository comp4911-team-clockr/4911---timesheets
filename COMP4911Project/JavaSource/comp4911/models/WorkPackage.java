package comp4911.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WorkPackage")
public class WorkPackage implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="wpId")
	private String wpId;
	
	@Column(name="wpNum")
	private int wpNum;
	
	@Column(name="wpTitle")
	private String wpTitle;
	
	@Column(name="contractorId")
	private String contractorId;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="payRateId")
	private String payRateId;
	
	@Column(name="manDays")
	private int manDays;
	
	@Column(name="projectId")
	private int projectId;
	
	private List<Employee> empWPList;

	public WorkPackage(){}
	
	public String getWpId() {
		return wpId;
	}

	public void setWpId(String wpId) {
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

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public List<Employee> getEmpWPList() {
		return empWPList;
	}

	public void setEmpWPList(List<Employee> empWPList) {
		this.empWPList = empWPList;
	}
}

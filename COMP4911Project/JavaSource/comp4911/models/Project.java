package comp4911.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Project")
public class Project implements Serializable{

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ProjectId")
	private int projectId;
	
	@Column(name="ProjName")
	private String projName;
	
//	@Column(name="RoleId")
//	private String roleId;
	
	@Column(name="ManDays")
	private int manDays;
	
	@Column(name="EmpNum")
	private int supervisor;
	
	@Column(name="IssueDate")
	private Date issueDate;
	
	@Lob
	@Column(name="Descript", columnDefinition = "text", length=512)
	private String desc;

	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<Employee> empProjList;
	
	@OneToMany(mappedBy="head", fetch=FetchType.EAGER)
	private List<WorkPackage> wpList;
	
	public Project(){}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

//	public String getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(String roleId) {
//		this.roleId = roleId;
//	}

	public int getManDays() {
		return manDays;
	}

	public void setManDays(int manDays) {
		this.manDays = manDays;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

	public List<Employee> getEmpProjList() {
		return empProjList;
	}

	public void setEmpProjList(List<Employee> empProjList) {
		this.empProjList = empProjList;
	}

	public List<WorkPackage> getWpList() {
		return wpList;
	}

	public void setWpList(List<WorkPackage> wpList) {
		this.wpList = wpList;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
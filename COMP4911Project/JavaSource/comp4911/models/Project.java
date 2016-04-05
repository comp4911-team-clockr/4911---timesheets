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
	
	@Column(name="EmpNum")
	private int supervisor;
	
	@Column(name="IssueDate")
	private Date issueDate;
	
	@Column(name="EstimatedCompletionDate")
	private Date estCompletionDate;

	@Column(name="ProjAssistant")
	private int projAssistant;

	@Column(name="CostingProposal")
	private double proposal;
	
	@Column(name="InitialBudget")
	private double initBudget;
	
	@Column(name="RO1Budget")
	private double ro1Budget;

	@Column(name="RO2Budget")
	private double ro2Budget;
	
	@Column(name="FinalBudget")
	private double finalBudget;

	@Column(name="MDP1")
	private int manDaysP1;

	@Column(name="MDP2")
	private int manDaysP2;
	
	@Column(name="MDP3")
	private int manDaysP3;
	
	@Column(name="MDP4")
	private int manDaysP4;
	
	@Column(name="MDP5")
	private int manDaysP5;
	
	@Column(name="MDDS")
	private int manDaysDS;

	@Column(name="MDSS")
	private int manDaysSS;
	
	@Lob
	@Column(name="Descript", columnDefinition = "text", length=512)
	private String desc;

//	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
//	private List<Employee> empProjList;
//	
//	@OneToMany(mappedBy="head", fetch=FetchType.EAGER)
//	private List<WorkPackage> wpList;
	
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

	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

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

	public int getProjAssistant() {
		return projAssistant;
	}

	public void setProjAssistant(int projAssistant) {
		this.projAssistant = projAssistant;
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
	
	public double getProposal() {
		return proposal;
	}

	public void setProposal(double proposal) {
		this.proposal = proposal;
	}
	
	public double getInitBudget() {
		return initBudget;
	}

	public void setInitBudget(double initBudget) {
		this.initBudget = initBudget;
	}
	
	public double getRO1Budget() {
		return ro1Budget;
	}

	public void setRO1Budget(double ro1Budget) {
		this.ro1Budget = ro1Budget;
	}
	
	public double getRO2Budget() {
		return ro2Budget;
	}

	public void setRO2Budget(double ro2Budget) {
		this.ro2Budget = ro2Budget;
	}
	
	public double getFinalBudget() {
		return finalBudget;
	}

	public void setFinalBudget(double finalBudget) {
		this.finalBudget = finalBudget;
	}
	
	public int getManDaysP1() {
		return manDaysP1;
	}

	public void setManDaysP1(int manDaysP1) {
		this.manDaysP1 = manDaysP1;
	}

	public int getManDaysP2() {
		return manDaysP2;
	}

	public void setManDaysP2(int manDaysP2) {
		this.manDaysP2 = manDaysP2;
	}

	public int getManDaysP3() {
		return manDaysP3;
	}

	public void setManDaysP3(int manDaysP3) {
		this.manDaysP3 = manDaysP3;
	}

	public int getManDaysP4() {
		return manDaysP4;
	}

	public void setManDaysP4(int manDaysP4) {
		this.manDaysP4 = manDaysP4;
	}

	public int getManDaysP5() {
		return manDaysP5;
	}

	public void setManDaysP5(int manDaysP5) {
		this.manDaysP5 = manDaysP5;
	}

	public int getManDaysDS() {
		return manDaysDS;
	}

	public void setManDaysDS(int manDaysDS) {
		this.manDaysDS = manDaysDS;
	}
	
	public int getManDaysSS() {
		return manDaysSS;
	}

	public void setManDaysSS(int manDaysSS) {
		this.manDaysSS = manDaysSS;
	}
	
	public Date getEstCompletionDate() {
		return estCompletionDate;
	}

	public void setEstCompletionDate(Date estCompletionDate) {
		this.estCompletionDate = estCompletionDate;
	}

	public double getRo1Budget() {
		return ro1Budget;
	}

	public void setRo1Budget(double ro1Budget) {
		this.ro1Budget = ro1Budget;
	}

	public double getRo2Budget() {
		return ro2Budget;
	}

	public void setRo2Budget(double ro2Budget) {
		this.ro2Budget = ro2Budget;
	}

}
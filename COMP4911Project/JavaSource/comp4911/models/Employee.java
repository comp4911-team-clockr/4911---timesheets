package comp4911.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Employees")
public class Employee implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EmpNum")
	private int empNumber;
	
	@OneToOne
	@JoinColumn(name="UserId")
	private Credential credential;
	
	@Column(name="EmpFname")
	private String firstName;
	
	@Column(name="EmpLname")
	private String lastName;
	
	@Column(name="SickDays")
	private int sickDays;
	
	@Column(name="VacDays")
	private int vacationDays;
	
	@Column(name="FlexStart")
	private Time flexStart;
	
	@Column(name="FlexEnd")
	private Time flexEnd;
	
	@Column(name="HireDate")
	private Date hireDate;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="PayRateId")
	private String payRateId;

	@ManyToOne
	@JoinColumn(name="ProjectId")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="WpId")
	private WorkPackage workpackage;

	@Transient
	private boolean active;
	
	public Employee() {}

	public int getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getSickDays() {
		return sickDays;
	}

	public void setSickDays(int sickDays) {
		this.sickDays = sickDays;
	}

	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public Time getFlexStart() {
		return flexStart;
	}

	public void setFlexStart(Time flexStart) {
		this.flexStart = flexStart;
	}

	public Time getFlexEnd() {
		return flexEnd;
	}

	public void setFlexEnd(Time flexEnd) {
		this.flexEnd = flexEnd;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getPayRateId() {
		return payRateId;
	}

	public void setPayRateId(String payRateId) {
		this.payRateId = payRateId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public WorkPackage getWorkpackage() {
		return workpackage;
	}

	public void setWorkpackage(WorkPackage workpackage) {
		this.workpackage = workpackage;
	}
	
}


package comp4911.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Transient
	private Credential credential;
	
	@Column(name="UserId")
	private String userId;
	
	@Column(name="EmpFname")
	private String firstName;
	
	@Column(name="EmpLname")
	private String lastName;
	
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
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userid) {
		this.userId = userid;
	}
	
}


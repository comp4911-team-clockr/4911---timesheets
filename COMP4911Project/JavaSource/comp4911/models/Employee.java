package comp4911.models;

import java.io.Serializable;


public class Employee implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;

	private int empNumber;
	
	private Credential credential;
	
	private String firstName;
	
	private String lastName;
	
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
	
}


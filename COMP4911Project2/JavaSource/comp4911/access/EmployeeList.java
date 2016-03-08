package comp4911.access;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp4911.controllers.CredentialManager;
import comp4911.controllers.EmployeeManager;
import comp4911.models.Credential;
import comp4911.models.Employee;

@Named("user")
@SessionScoped
public class EmployeeList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject private Employee employee;
	
	@Inject private EmployeeManager employeeManager;
	
	@Inject private Credential credential;
	
	@Inject private CredentialManager credentialManager;
	
	List<Employee> empList;
	
	List<Credential>credList;
	
	public List<Employee> getList() {
		if (empList == null) {
			refreshList();
		}
		return empList;
	}
	
	public void refreshList() {
		Employee[] employees = employeeManager.getAll();
	}
	
	public void setList(List<Employee> e) {
		empList = e;
	}
	
	public String checkLogin(String id, String password) {
		credList = Arrays.asList(credentialManager.getAll());
		
		for(int i = 0; i < credList.size(); i++){
			if(credList.get(i).getUserId().equals(id) && credList.get(i).getPassword().equals(password)){
				return "loggedin";
			}
		}
		return "indexproto?faces-redirect=true";
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeManager getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public CredentialManager getCredentialManager() {
		return credentialManager;
	}

	public void setCredentialManager(CredentialManager credentialManager) {
		this.credentialManager = credentialManager;
	}
	public String addEmployeeButton(){
		return "AddEmployee";
	}
}
package comp4911.access;

import java.io.Serializable;
//import java.util.Arrays;
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
	
	@Inject private Employee currentEmployee;
	
	@Inject private EmployeeManager employeeManager;
	
	@Inject private Credential credential;
	
	@Inject private Credential credToAdd;
	
	@Inject private CredentialManager credentialManager;
	
	List<Employee> empList;
	
	List<Credential>credList;
	
	public List<Employee> getEmpList() {
		if (empList == null) {
			refreshList();
		}
		return empList;
	}
	
	public void refreshList() {
		empList = employeeManager.getAll();
	}
	
	public void refreshCurrentEmployee() {
		currentEmployee = employeeManager.findByUserId(credential.getUserId());
	}
	
	public void setEmpList(List<Employee> e) {
		empList = e;
	}
	
	public String checkLogin(String id, String password) {
		Credential cred;
		
		if ((cred = credentialManager.find(id)) != null) {
			if (cred.getPassword().equals(password)) {
				currentEmployee = employeeManager.findByUserId(id);
				setCredential(credentialManager.find(id));
				refreshList();
				return "loggedin";
			}
		}
		return "indexproto?faces-redirect=true";
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
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
	
	public Credential getCredToAdd() {
		return credToAdd;
	}
	
	public void setCredToAdd(Credential c) {
		credToAdd = c;
	}
	
	public String addEmployee() {
		Employee temp = new Employee();
		Credential tempCred = new Credential();
		Integer empNumber = empList.size() + 1;
		String userID = empNumber.toString();
		
		for (int i = userID.length(); i < 6; i++)
			userID = '0' + userID;
		
		temp.setFirstName(employee.getFirstName());
		temp.setLastName(employee.getLastName());
		temp.setActive(true);
		temp.setEmpNumber(empNumber);
		//temp.setUserId(userID);
		
		tempCred.setUserId(userID);
		tempCred.setPassword("cafebabe");
		tempCred.setEmail(credToAdd.getEmail());
		tempCred.setRole(credToAdd.getRole());
		
		temp.setCredential(tempCred);
		
		credentialManager.persist(tempCred);
		employeeManager.persist(temp);
		
		refreshList();
		
		//any better way to "reset" the fields after it's used?
		setEmployee(new Employee());
		setCredToAdd(new Credential());
		
		return "displayEmployeeList";
	}
	
	
	
	public boolean showDelete(Employee e) {
		if (currentEmployee.getEmpNumber() == e.getEmpNumber())
			return false;
		return true;
	}
	
	public String deleteEmployee(Employee e) {
		employeeManager.remove(employeeManager.find(e.getEmpNumber()));
		credentialManager.remove(credentialManager.find(e.getCredential().getUserId()));
		
		refreshList();
		
		return "displayEmployeeList";
	}
	
	public String updateProfile() {
		Employee temp = currentEmployee;
		Credential tempCred = credential;
		
		tempCred.setEmail(credToAdd.getEmail());
		temp.setFirstName(employee.getFirstName());
		temp.setLastName(employee.getLastName());
		temp.setCredential(tempCred);
		
		credentialManager.merge(tempCred);
		employeeManager.merge(temp);
		
		refreshCurrentEmployee();
		refreshList();
		
		return "userProfile";
	}
<<<<<<< HEAD
	public String AddProject(){
		return "addProject";
	}
	public String AddWorkPackage(){
		return "addWP";
	}
=======
>>>>>>> 7f457aa6079d4eb0dbb006e4a4b40bb61702ff04
}

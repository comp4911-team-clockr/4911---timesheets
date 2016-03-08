package comp4911.access;

import java.io.Serializable;
//import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.transaction.Transactional;

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
	
	@Inject private Employee editEmployee;
	
	@Inject private EmployeeManager employeeManager;
	
	@Inject private Credential credential;
	
	@Inject private Credential credToAdd;
	
	@Inject private CredentialManager credentialManager;
	
//	@Inject private TimeSheetManager timesheetManager;
//	
//	@Inject private TimeSheet timesheet;

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
		
		//Timesheet test
//		timesheet = timesheetManager.getAll()[0];
//		System.out.println(timesheet.toString());
//		for(int i = 0; i < timesheet.getTimeSheetRows().size(); i++) {
//			System.out.println(timesheet.getTimeSheetRows().toArray()[i].toString());
//		}
		
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
	
	public Employee getEditEmployee() {
		return editEmployee;
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
	
	//DigiSign
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
		
		String deFaultPW = "cafebabe";
		
		tempCred.setUserId(userID);
		tempCred.setPassword(deFaultPW);
		tempCred.setEmail(credToAdd.getEmail());
		tempCred.setRole(credToAdd.getRole());
		
		int hashFN = employee.getFirstName().hashCode();
		int hashLN = employee.getLastName().hashCode();
		//int hashPW = tempCred.getPassword().hashCode();
		int hashPW = tempCred.getPassword().hashCode();
		
		int cal = hashFN + hashLN + hashPW;
		
		String hashCode =  Integer.toString(cal);
		
		tempCred.setDigiSign(hashCode);
		System.out.println(hashCode);
		temp.setCredential(tempCred);
		
		credentialManager.persist(tempCred);
		employeeManager.persist(temp);
		
		refreshList();
		
		//any better way to "reset" the fields after it's used?
		setEmployee(new Employee());
		setCredToAdd(new Credential());
		
		return "displayEmployeeList";
	}
	
	public String showEmployeeToEdit(Employee emp) {
		editEmployee = emp;
		credToAdd = emp.getCredential();
		return "EditEmployee";
	}
	
	
	public String updateEmployee(int id) {
		Employee temp = employeeManager.find(id);
		
		temp.setFirstName(editEmployee.getFirstName());
		temp.setLastName(editEmployee.getLastName());
		temp.getCredential().setEmail(credToAdd.getEmail());
		temp.getCredential().setRole(credToAdd.getRole());
		
		credentialManager.merge(temp.getCredential());
		employeeManager.merge(temp);
		
		refreshCurrentEmployee();
		refreshList();
		credToAdd = new Credential();
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
	
//	public TimeSheet getTimesheet() {
//		return timesheet;
//	}
//
//	public void setTimesheet(TimeSheet timesheet) {
//		this.timesheet = timesheet;
//	}
}

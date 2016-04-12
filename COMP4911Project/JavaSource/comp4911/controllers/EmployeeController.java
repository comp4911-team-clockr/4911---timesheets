package comp4911.controllers;

import java.io.Serializable;
//import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
//import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.transaction.Transactional;

import comp4911.managers.CredentialManager;
import comp4911.managers.EmployeeManager;
import comp4911.models.Credential;
import comp4911.models.Employee;

@Named("user")
@SessionScoped
public class EmployeeController implements Serializable {

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

	private boolean isPM;

	private boolean isHR;
	
	@ManagedProperty("#{loginMsg}")
	private ResourceBundle msg;

	List<Employee> empList;

	List<Credential>credList;

	//The pattern used to check if password have an uppercase
	private final static Pattern hasUppercase = Pattern.compile("[A-Z]");

	//The pattern used to check if password have a lowercase
	private final static Pattern hasLowercase = Pattern.compile("[a-z]");

	//The pattern used to check if password have a number
	private final static Pattern hasNumber = Pattern.compile("\\d");

	//The pattern used to check if password have a special character
	private final static Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");

	private String resetPassword;

	private HtmlInputText inputComponent = new HtmlInputText();
	
	@PostConstruct
	public void init(){
		inputComponent.setValue(" ");
	}
	
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
				System.out.println("Check Login passed");
				return "loggedin";		
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username/Password is incorrect.",null));
		}
		System.out.println("Check Login failed");
		return "MainIndex?faces-redirect=true";
	}

	public String cancelEditEmployee(){
		return "cancelEditEmployee";
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
		System.out.println("Add Employee called");
		Employee temp = new Employee();
		Credential tempCred = new Credential();
		Integer empNumber = empList.size() + 1;
		String userID = empNumber.toString();

		//Padding zeroes in front of userId accordingly
		for (int i = userID.length(); i < 6; i++)
			userID = '0' + userID;

		temp.setFirstName(employee.getFirstName());
		temp.setLastName(employee.getLastName());
		temp.setActive(true);
		temp.setEmpNumber(empNumber);

		String deFaultPW = "cafebabe";

		tempCred.setUserId(userID);
		tempCred.setPassword(deFaultPW);
		tempCred.setEmail(credToAdd.getEmail());
		tempCred.setRole(credToAdd.getRole());

		int hashFN = employee.getFirstName().hashCode();
		int hashLN = employee.getLastName().hashCode();
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

		return "DisplayEmployees";
	}

	public String showEmployeeToEdit(Employee emp) {
		System.out.println("Edit Employee called");
		editEmployee = emp;
		credToAdd = emp.getCredential();
		return "EditEmployee";
	}

	public String updateEmployee(int id) {
		System.out.println("Update Employee called");
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
		return "DisplayEmployees";
	}


	public boolean showDelete(Employee e) {
		if (currentEmployee.getEmpNumber() == e.getEmpNumber())
			return false;
		return true;
	}

	public String deleteEmployee(Employee e) {
		System.out.println("Delete Employee called");
		e.setActive(false);
		employeeManager.merge(e);
//		employeeManager.remove(employeeManager.find(e.getEmpNumber()));
		credentialManager.remove(credentialManager.find(e.getCredential().getUserId()));
		refreshList();

		return "DisplayEmployees";
	}

	public void updateEmpInit() {
		employee = currentEmployee;
		credToAdd = credential;
		credToAdd.setPassword("");
		resetPassword = "";
	}

	public String updateProfile() {
		if (isValidEmailAddress(credToAdd.getEmail())) {

			Employee temp = currentEmployee;
			Credential tempCred = credential;

			tempCred.setEmail(credToAdd.getEmail());
			tempCred.setPassword(employee.getCredential().getPassword());
			temp.setFirstName(employee.getFirstName());
			temp.setLastName(employee.getLastName());
			tempCred.setRecovery1(credToAdd.getRecovery1());
			tempCred.setRecovery2(credToAdd.getRecovery2());
			tempCred.setRecovery3(credToAdd.getRecovery3());

			temp.setCredential(tempCred);

			credentialManager.merge(tempCred);
			employeeManager.merge(temp);

			refreshCurrentEmployee();
			refreshList();
			return "ViewDetails";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email address entered.", null));
			return "ResetCreds";
		}
	}

	public String resetEmpPassword() {
		String validate = validateNewPass(credToAdd.getPassword(), resetPassword); 
		if (validate.equals("Success")) {
			credential.setPassword(credToAdd.getPassword());
			credentialManager.merge(credential);

			return "ResetCreds";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, validate, null));
		}
		return "ResetCreds";
	}

	public String AddProject(){
		return "addProject";
	}

	public String AddWorkPackage(){
		return "addWP";
	}

	//Please change the length of the password to the desired length
	//This function checks if the new input as new password are valid
	//Conditions are:
	//both not null or empty
	//both are the same string
	//have a uppercase
	//have a lowercase
	//have a number
	//have a special character
	//
	//If the result is invalid the problem will be printed into a string that will be returned at the end
	//If the result is valid a "success" string will be returned
	public static String validateNewPass(String pass1, String pass2) {
		if (pass1 == null || pass2 == null) {
			return "One or both passwords are null";
		}

		//StringBuilder retVal = new StringBuilder();

		if (pass1.isEmpty() || pass2.isEmpty()) {
			//retVal.append("Empty fields\n");
			return "Empty field(s)\n";
		}

		if (pass1.equals(pass2)) {
			if (pass1.length() < 7) {
				//retVal.append("Password is too short. Needs to have 11 characters\n");
				return "Password is too short. Needs to have 11 characters\n";
			}

			if (!hasUppercase.matcher(pass1).find()) {
				//retVal.append("Password needs an upper case\n");
				return "Password needs an upper case\n";
			}

			if (!hasLowercase.matcher(pass1).find()) {
				//retVal.append("Password needs a lowercase\n");
				return "Password needs a lowercase\n";
			}

			if (!hasNumber.matcher(pass1).find()) {
				//retVal.append("Password needs a number\n");
				return "Password needs a number\n";
			}

			if (!hasSpecialChar.matcher(pass1).find()) {
				//retVal.append("Password needs a special character i.e. !,@,#, etc.\n");
				return "Password needs a special character i.e. !,@,#, etc.\n";
			}
		} else {
			//retVal.append("Passwords don't match\n");
			return "Passwords don't match\n";
		}

		//if (retVal.length() == 0) {
			//retVal.append("Success");
			return "Success";
		//}

		//return retVal.toString();
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}
	public String getResetPassword() {
		return this.resetPassword;
	}

	public String cancelAddEmp(){
		System.out.println("Cancel was called");
		return "reloadEmpList";
	}

	public boolean isEmployee(int id){
		Employee temp = employeeManager.find(id);
		int roleId = temp.getCredential().getRoleId();
		if(roleId == 1){
			return true;
		}
		return false;
	}

	public boolean isProjectManager(int id){
		Employee temp = employeeManager.find(id);
		int roleId = temp.getCredential().getRoleId();
		if(roleId == 2){
			return true;
		}
		return false;
	}

	public boolean isHumanResource(int id){
		Employee temp = employeeManager.find(id);
		int roleId = temp.getCredential().getRoleId();
		if(roleId == 3){
			return true;
		}
		return false;
	}

	public String ForgotPassword(){
		return "ForgotPasswordCancel";
	}

	public String GoForgotPassword(){
		return "ForgotPasswordPage";
	}

	public String GetRecoveryQuestions(String id){
		//Employee set to entered user id to grab their question answers
		Credential cred = credentialManager.find(credential.getUserId());
		credToAdd.setRecovery1("");
		credToAdd.setRecovery2("");
		credToAdd.setRecovery3("");
		if (cred != null) {
			currentEmployee = employeeManager.findByUserId(credential.getUserId());
			setCredential(credentialManager.find(credential.getUserId()));
			refreshList();
			System.out.println("Check User Id passed");
			credential  = cred;
			return "GetRecoveryQuestions";
		} else {
			FacesContext.getCurrentInstance().addMessage("forgotForm:userId",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "User ID is incorrect.", null));
		}
		System.out.println("Check User id fail");
		return "ForgotPasswordPage";
	}

	//this method should take in 3 parameters (3 strings for questions)
	public String SubmitRecovery(){
		//add if statement and what not for entered values before returning.
		credToAdd.setPassword("");
		if (credToAdd.getRecovery1().equals(credential.getRecovery1()) &&
				credToAdd.getRecovery2().equals(credential.getRecovery2()) &&
				credToAdd.getRecovery3().equals(credential.getRecovery3()) ){
			System.out.println(credToAdd.getRecovery1() + " " + credential.getRecovery1());
			System.out.println(credToAdd.getRecovery2() + " " + credential.getRecovery2());
			System.out.println(credToAdd.getRecovery3() + " " + credential.getRecovery3());
			return "RecoveryPassed";
		} else {
			System.out.println("Check Answers!!!");
			FacesContext.getCurrentInstance().addMessage("recoverForm:submitBtn",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Given answers are incorrect.", null));
			return "GetRecoveryQuestions";
		}		
	}

	public String RecoveryQuestionsCancel(){
		return "RecoveryQuestionsCancel";
	}

	public String CancelChangePassword(){
		return "CancelChangePassword";
	}

	//this method should have two parameters. one for password, then re-entered password
	public String ConfirmChangePassword(){
		//check if they're equal then change password value for that user and return this
		String validate = validateNewPass(credToAdd.getPassword(), resetPassword); 
		if (validate.equals("Success")) {
			credential.setPassword(credToAdd.getPassword());
			credentialManager.merge(credential);
			return "ChangePasswordConfirmed";
		} else {
			FacesContext.getCurrentInstance().addMessage("changePassForm:submitBtn",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, validate, null));
		}
		//else return same page
		return "RecoveryPassed";
	}

	public boolean getIsPM() {
		isPM = (currentEmployee.getCredential().getRole().equals("ProjectManager"));
		return isPM;
	}

	public String cancelViewDetails(){
		return "cancelViewDetails";
	}


	public boolean getIsHR() {
		isHR = (currentEmployee.getCredential().getRole().equals("HumanResource"));
		return isHR;
	}

}

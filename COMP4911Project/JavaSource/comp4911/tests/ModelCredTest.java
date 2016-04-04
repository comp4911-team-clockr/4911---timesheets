package comp4911.tests;

import junit.framework.TestCase;

import java.sql.Date;
//import java.sql.Time;

import org.junit.Test;
import comp4911.models.Credential;
import comp4911.models.Employee;
//import comp4911.models.Project;
//import comp4911.models.WorkPackage;

/**
 * Testing the Credential Model
 */
public class ModelCredTest {
	
	@Test
	public void CredInitTest(){
		
		// Dummy Data
		final String userId = "000099";
		final String password = "password";
		final int roleId = 005;
		final String role = "Employee";
		final String email = "zz@zz.zz";
		
		final Credential cred = new Credential(userId, password, roleId, role, email);
		
		// Test Results
		final String testUserId = cred.getUserId();
		final String testPassword = cred.getPassword();
		final int testRoleId = cred.getRoleId();
		final String testRole = cred.getRole();
		final String testEmail = cred.getEmail();
		
		// Expected Results
		final String expUserId = userId;
		final String expPassword = password;
		final int expRoleId = roleId;
		final String expRole = role;
		final String expEmail = email;
		
		// Testing
		TestCase.assertEquals
		(
			"CredInitTest UserId has FAILED", 
			expUserId, 
			testUserId
		);
		
		TestCase.assertEquals
		(
			"CredInitTest Password has FAILED", 
			expPassword, 
			testPassword
		);
		
		TestCase.assertEquals
		(
			"CredInitTest RoleId has FAILED", 
			expRoleId, 
			testRoleId
		);

		TestCase.assertEquals
		(
			"CredInitTest Role has FAILED", 
			expRole, 
			testRole
		);
		
		TestCase.assertEquals
		(
			"CredInitTest Email has FAILED", 
			expEmail, 
			testEmail
		);
	}
	
	@Test
	public void CredGetSetTest(){
		
		// Dummy Data
		final String userId = "000099";
		final String password = "password";
		final int roleId = 005;
		final String role = "Employee";
		final String email = "zz@zz.zz";
		final String digiSign = "DigiSign";
		
		final int empNum = 1;
		final String empFName = "John";
		final String empLName = "Doe";
		final boolean isActive = true;
		//final int sickDays = 10;
		final int vacDays = 10;
		//final Time flexStart = Time.valueOf("11:30:00");
		//final Time flexEnd = Time.valueOf("19:30:00");
		final Date hireDate = Date.valueOf("2010-10-10");
		final String payRateId = "P1";
		
		final Credential newCred = new Credential(userId, password, roleId, role, email);
		//final Project proj = new Project();		
		//final WorkPackage wp = new WorkPackage();
		
		// Creating a new Employee object
		final Employee emp = new Employee();
		emp.setEmpNumber(empNum);
		emp.setCredential(newCred);
		emp.setFirstName(empFName);
		emp.setLastName(empLName);
		emp.setActive(isActive);
		//emp.setSickDays(sickDays);
		emp.setVacDays(vacDays);
		//emp.setFlexStart(flexStart);
		//emp.setFlexEnd(flexEnd);
		emp.setHireDate(hireDate);
		emp.setPayRateId(payRateId);
		emp.setEmail(email);
		//emp.setProject(proj);
		//emp.setWorkpackage(wp);
		
		final Credential cred = new Credential();
		
		// Test for Setters
		cred.setUserId(userId);
		cred.setPassword(password);
		cred.setRoleId(roleId);
		cred.setRole(role);
		cred.setEmail(email);
		cred.setDigiSign(digiSign);
		cred.setEmployee(emp);
		
		// Test Results
		final String testUserId = cred.getUserId();
		final String testPassword = cred.getPassword();
		final int testRoleId = cred.getRoleId();
		final String testRole = cred.getRole();
		final String testEmail = cred.getEmail();
		final String testDigiSign = cred.getDigiSign();
		Employee testEmp = new Employee();
		testEmp = cred.getEmployee();
		
		// Expected Results
		final String expUserId = userId;
		final String expPassword = password;
		final int expRoleId = roleId;
		final String expRole = role;
		final String expEmail = email;
		final String expDigiSign = digiSign;
		Employee expEmp = new Employee();
		expEmp = emp;
		
		// Testing
		TestCase.assertEquals
		(
			"CredGetSetTest UserId has FAILED", 
			expUserId, 
			testUserId
		);
		
		TestCase.assertEquals
		(
			"CredGetSetTest Password has FAILED", 
			expPassword, 
			testPassword
		);
		
		TestCase.assertEquals
		(
			"CredGetSetTest RoleId has FAILED", 
			expRoleId, 
			testRoleId
		);

		TestCase.assertEquals
		(
			"CredGetSetTest Role has FAILED", 
			expRole, 
			testRole
		);
		
		TestCase.assertEquals
		(
			"CredGetSetTest Email has FAILED", 
			expEmail, 
			testEmail
		);
		
		TestCase.assertEquals
		(
			"CredGetSetTest UserId has FAILED", 
			expDigiSign, 
			testDigiSign
		);
		
		TestCase.assertEquals
		(
			"CredGetSetTest UserId has FAILED", 
			expEmp, 
			testEmp
		);
	}
}
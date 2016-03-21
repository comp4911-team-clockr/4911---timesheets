package comp4911.tests;

import java.sql.Date;
import java.sql.Time;

import javax.inject.Inject;

import org.junit.Test;

import comp4911.managers.CredentialManager;
import comp4911.models.Credential;
import comp4911.models.Employee;
import comp4911.models.Project;
import comp4911.models.WorkPackage;
import junit.framework.TestCase;

/**
 * Testing Credential Manager
 * 
 * @author Roscef
 *
 */
public class ManagerCredTest {

	@Inject private CredentialManager cm;
	
	@Test
	public void CredFindTest(){
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
		final int sickDays = 10;
		final int vacDays = 10;
		final Time flexStart = Time.valueOf("11:30:00");
		final Time flexEnd = Time.valueOf("19:30:00");
		final Date hireDate = Date.valueOf("2010-10-10");
		final String payRateId = "P1";
		
		final Credential tempCred = new Credential(userId, password, roleId, role, email);
		final Project proj = new Project();		
		final WorkPackage wp = new WorkPackage();
		
		// Creating a new Employee object
		final Employee emp = new Employee();
		emp.setEmpNumber(empNum);
		emp.setCredential(tempCred);
		emp.setFirstName(empFName);
		emp.setLastName(empLName);
		emp.setActive(isActive);
		emp.setSickDays(sickDays);
		emp.setVacationDays(vacDays);
		emp.setFlexStart(flexStart);
		emp.setFlexEnd(flexEnd);
		emp.setHireDate(hireDate);
		emp.setPayRateId(payRateId);
		emp.setEmail(email);
		emp.setProject(proj);
		emp.setWorkpackage(wp);
		
		final Credential cred = new Credential();
		
		// Test for Setters
		cred.setUserId(userId);
		cred.setPassword(password);
		cred.setRoleId(roleId);
		cred.setRole(role);
		cred.setEmail(email);
		cred.setDigiSign(digiSign);
		cred.setEmployee(emp);
		
		cm.persist(cred);
		
		Credential newCred = new Credential();
		newCred = cm.find(userId);
		
		// Testing
		TestCase.assertEquals
		(
			"CredFindTest UserId has FAILED", 
			userId, 
			newCred.getUserId()
		);
		
		TestCase.assertEquals
		(
			"CredFindTest Password has FAILED", 
			password, 
			newCred.getPassword()
		);
		
		TestCase.assertEquals
		(
			"CredFindTest UserId has FAILED", 
			roleId, 
			newCred.getRoleId()
		);
		
		TestCase.assertEquals
		(
			"CredFindTest UserId has FAILED", 
			role, 
			newCred.getRole()
		);
		
		TestCase.assertEquals
		(
			"CredFindTest UserId has FAILED", 
			email, 
			newCred.getEmail()
		);
	}
}

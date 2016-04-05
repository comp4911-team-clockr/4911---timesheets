package comp4911.tests;

import junit.framework.TestCase;

import java.sql.Date;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;

import org.junit.Test;
import comp4911.models.Employee;
import comp4911.models.Credential;
//import comp4911.models.WorkPackage;
//import comp4911.models.Project;

/**
 *	Testing Employee Model
 */
public class ModelEmpTest {
	 
	@Test
	public void EmpGetSetTest(){
		
		// Dummy Data
		final int empNum = 1;
		final String empFName = "John";
		final String empLName = "Doe";
		final boolean isActive = true;
		final Date hireDate = Date.valueOf("2010-10-10");
		final String email = "zz@zz.zz";
		final String payRateId = "P1";
		final int supNum = 1;
		final int vacDays = 10;
		//final int sickDays = 10;
		//final Time flexStart = Time.valueOf("11:30:00");
		//final Time flexEnd = Time.valueOf("19:30:00");
		
		// Dummy Data for Credential
		final String userId = "000099";
		final String password = "password";
		final int roleId = 005;
		final String role = "Employee";
		final Credential cred = new Credential(userId, password, roleId, role, email);
		//final List<Employee> empList = new ArrayList<Employee>();
		//final List<WorkPackage> wpList = new ArrayList<WorkPackage>();
		
		//final Date issueDate = Date.valueOf("2016-02-01");
		
		// Creating a new Employee object
		final Employee emp = new Employee();
		emp.setEmpNumber(empNum);
		emp.setCredential(cred);
		emp.setFirstName(empFName);
		emp.setLastName(empLName);
		emp.setActive(isActive);
		emp.setHireDate(hireDate);
		emp.setPayRateId(payRateId);
		emp.setEmail(email);
		emp.setSupNum(supNum);
		emp.setVacDays(vacDays);
		//emp.setSickDays(sickDays);
		//emp.setFlexStart(flexStart);
		//emp.setFlexEnd(flexEnd);
		//emp.setProject(proj);
		//emp.setWorkpackage(wp);
		
		
		// Test Result
		final int testEmpNum = emp.getEmpNumber();
		final String testEmpFName = emp.getFirstName();
		final String testEmpLName = emp.getLastName();
		final boolean testIsActive = emp.isActive();
		final Date testHireDate = emp.getHireDate();
		final String testEmail = emp.getEmail();
		final String testPayRateId = emp.getPayRateId();
		final int testVacDays = emp.getVacDays();
		final int testSupNum = emp.getSupNum();
		
		Credential testCred = new Credential();
		testCred = emp.getCredential();
		
		//final int testSickDays = emp.getSickDays();
		//final Time testFlexStart = emp.getFlexStart();
		//final Time testFlexEnd = emp.getFlexEnd();
		
		//WorkPackage testWP = new WorkPackage();
		//testWP = emp.getWorkpackage();
		//Project testProj = new Project();
		//testProj = emp.getProject();
		
		// Expected Result
		final int expEmpNum = empNum;
		final String expEmpFName = empFName;
		final String expEmpLName = empLName;
		final boolean expIsActive = isActive;
		final Date expHireDate = hireDate;
		final String expEmail = email;
		final String expPayRateId = payRateId;
		final int expVacDays = vacDays;
		final int expSupNum = supNum;
		
		Credential expCred = new Credential();
		expCred = cred;
		
		//final int expSickDays = sickDays;
		//final Time expFlexStart = flexStart;
		//final Time expFlexEnd = flexEnd;
		
		//WorkPackage expWP = new WorkPackage();
		//expWP = wp;
		//Project expProj = new Project();
		//expProj = proj;
		
		// Testing EmpNumber
		TestCase.assertEquals
		(
			"EmpGetSetTest EmpNumber has FAILED", 
			expEmpNum, 
			testEmpNum
		);
		
		// Testing FirstName
		TestCase.assertEquals
		(
			"EmpGetSetTest FirstName has FAILED", 
			expEmpFName, 
			testEmpFName
		);
		
		// Testing LastName
		TestCase.assertEquals
		(
			"EmpGetSetTest LastName has FAILED", 
			expEmpLName, 
			testEmpLName
		);
		
		// Testing IsActive
		TestCase.assertEquals
		(
			"EmpGetSetTest IsActive has FAILED", 
			expIsActive, 
			testIsActive
		);
		
		// Testing SickDays
//		TestCase.assertEquals
//		(
//			"EmpGetSetTest SickDays has FAILED", 
//			expSickDays, 
//			testSickDays
//		);
		
		// Testing FlexStart
//		TestCase.assertEquals
//		(
//			"EmpGetSetTest FlexStart has FAILED", 
//			expFlexStart, 
//			testFlexStart
//		);
		
		// Testing FlexEnd
//		TestCase.assertEquals
//		(
//			"EmpGetSetTest FlexEnd has FAILED", 
//			expFlexEnd, 
//			testFlexEnd
//		);
		
		// Testing HireDate
		TestCase.assertEquals
		(
			"EmpGetSetTest HireDate has FAILED", 
			expHireDate, 
			testHireDate
		);
		
		// Testing Email
		TestCase.assertEquals
		(
			"EmpGetSetTest Email has FAILED", 
			expEmail, 
			testEmail
		);
		
		// Testing PayRateId
		TestCase.assertEquals
		(
			"EmpGetSetTest PayRateId has FAILED", 
			expPayRateId, 
			testPayRateId
		);
		
		// Testing Credential
		TestCase.assertEquals
		(
			"EmpGetSetTest Credential has FAILED", 
			expCred, 
			testCred
		);
		
		// Testing SupNum
		TestCase.assertEquals
		(
			"EmpGetSetTest SupNum has FAILED", 
			expSupNum, 
			testSupNum
		);
		
		// Testing VacDays
		TestCase.assertEquals
		(
			"EmpGetSetTest VacDays has FAILED", 
			expVacDays, 
			testVacDays
		);
		
//		// Testing WorkPackage
//		TestCase.assertEquals
//		(
//			"EmpGetSetTest WorkPackage has FAILED", 
//			expWP, 
//			testWP
//		);
//		
//		// Testing Project
//		TestCase.assertEquals
//		(
//			"EmpGetSetTest Project has FAILED", 
//			expProj, 
//			testProj
//		);
	}
}
package comp4911.tests;

import junit.framework.TestCase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import comp4911.models.Employee;
import comp4911.models.WorkPackage;
import comp4911.models.Project;

/**
 * Testing Project Model
 */
public class ModelProjTest {
	
	@Test
	public void ProjGetSetTest() {
		
		final int projId = 10;
		final String projName = "Project Alpha";
		final int manDays = 300;
		final int supervisor = 2;
		
		final List<Employee> empList = new ArrayList<Employee>();
		final List<WorkPackage> wpList = new ArrayList<WorkPackage>(); 
		
		final Date issueDate = Date.valueOf("2016-02-01");
		final String desc = "";
		
		final Project proj = new Project();
		proj.setProjectId(projId);
		proj.setProjName(projName);
		proj.setManDays(manDays);
		proj.setSupervisor(supervisor);
		proj.setEmpProjList(empList); 	// List of Employee
		proj.setWpList(wpList); 		// List of WorkPackage
		proj.setIssueDate(issueDate);
		proj.setDesc(desc);
		
		// Test Result
		final int testProjId = proj.getProjectId();
		final String testProjName = proj.getProjName();
		final int testManDays = proj.getManDays();
		final int testSupervisor = proj.getSupervisor();
		
		List<Employee> testEmpList = new ArrayList<Employee>();
		testEmpList = proj.getEmpProjList();
		List<WorkPackage> testWpList = new ArrayList<WorkPackage>();
		testWpList = proj.getWpList();
		
		final Date testIssueDate = (Date) proj.getIssueDate();
		final String testDesc = proj.getDesc();
		
		// Expected Result
		final int expProjId = projId;
		final String expProjName = projName;
		final int expManDays = manDays;
		final int expSupervisor = supervisor;
		
		List<Employee> expEmpList = new ArrayList<Employee>();
		expEmpList = empList;
		List<WorkPackage> expWpList = new ArrayList<WorkPackage>();
		expWpList = wpList;
		
		final Date expIssueDate = issueDate;
		final String expDesc = desc;
		
		// Testing ProjId
		TestCase.assertEquals
		(
			"ProjGetSetTest ProjId has FAILED", 
			expProjId, 
			testProjId
		);
		
		// Testing ProjName
		TestCase.assertEquals
		(
			"ProjGetSetTest ProjName has FAILED", 
			expProjName, 
			testProjName
		);
		
		// Testing ManDays
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDays has FAILED", 
			expManDays, 
			testManDays
		);
		
		// Testing Supervisor
		TestCase.assertEquals
		(
			"ProjGetSetTest Supervisor has FAILED", 
			expSupervisor, 
			testSupervisor
		);
		
		// Testing EmpList
		TestCase.assertEquals
		(
			"ProjGetSetTest EmpList has FAILED", 
			expEmpList, 
			testEmpList
		);
		
		// Testing WPList
		TestCase.assertEquals
		(
			"ProjGetSetTest WPList has FAILED", 
			expWpList, 
			testWpList
		);
		
		// Testing IssueDate
		TestCase.assertEquals
		(
			"ProjGetSetTest IssueDate has FAILED", 
			expIssueDate, 
			testIssueDate
		);
		
		// Testing Desc
		TestCase.assertEquals
		(
			"ProjGetSetTest Desc has FAILED", 
			expDesc, 
			testDesc
		);
	}
}
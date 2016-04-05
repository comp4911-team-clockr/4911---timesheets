package comp4911.tests;

import junit.framework.TestCase;

import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;

import org.junit.Test;
//import comp4911.models.Employee;
//import comp4911.models.WorkPackage;
import comp4911.models.Project;

/**
 * Testing Project Model
 */
public class ModelProjTest {
	
	@Test
	public void ProjGetSetTest() {
		
		final int projId = 10;
		final String projName = "Project Alpha";
		final int manDaysProj1 = 301;
		final int manDaysProj2 = 302;
		final int manDaysProj3 = 303;
		final int manDaysProj4 = 304;
		final int manDaysProj5 = 305;
		final int manDaysProjDS = 306;
		final int manDaysProjSS = 307;
		final int supervisor = 2;
		final int projAss = 3;
		final double proposal = 4.0;
		final double initBudget = 5.0;
		final double ro1Budget = 6.0;
		final double ro2Budget = 7.0;
		final double finalBudget = 8.0;
		
		//final List<Employee> empList = new ArrayList<Employee>();
		//final List<WorkPackage> wpList = new ArrayList<WorkPackage>(); 
		
		final Date issueDate = Date.valueOf("2016-02-01");
		final String desc = "";
		
		final Project proj = new Project();
		proj.setProjectId(projId);
		proj.setProjName(projName);
		proj.setSupervisor(supervisor);
		//proj.setEmpProjList(empList); // List of Employee
		//proj.setWpList(wpList); // List of WorkPackage
		proj.setProjAssistant(projAss);
		proj.setIssueDate(issueDate);
		proj.setDesc(desc);
		proj.setProposal(proposal);
		proj.setInitBudget(initBudget);
		proj.setRO1Budget(ro1Budget);
		proj.setRO2Budget(ro2Budget);
		proj.setFinalBudget(finalBudget);
		proj.setManDaysP1(manDaysProj1);
		proj.setManDaysP2(manDaysProj2);
		proj.setManDaysP3(manDaysProj3);
		proj.setManDaysP4(manDaysProj4);
		proj.setManDaysP5(manDaysProj5);
		proj.setManDaysDS(manDaysProjDS);
		proj.setManDaysSS(manDaysProjSS);
		
		// Test Result
		final int testProjId = proj.getProjectId();
		final String testProjName = proj.getProjName();
		final int testSupervisor = proj.getSupervisor();
		final int testProjAss = proj.getProjAssistant();
		final Date testIssueDate = (Date) proj.getIssueDate();
		final String testDesc = proj.getDesc();
		final double testProposal = proj.getProposal();
		final double testInitBudget = proj.getInitBudget();
		final double testRo1Budget = proj.getRO1Budget();
		final double testRo2Budget = proj.getRO2Budget();
		final double testFinalBudget = proj.getFinalBudget();
		final int testManDaysP1 = proj.getManDaysP1();
		final int testManDaysP2 = proj.getManDaysP2();
		final int testManDaysP3 = proj.getManDaysP3();
		final int testManDaysP4 = proj.getManDaysP4();
		final int testManDaysP5 = proj.getManDaysP5();
		final int testManDaysDS = proj.getManDaysDS();
		final int testManDaysSS = proj.getManDaysSS();
		
		//List<Employee> testEmpList = new ArrayList<Employee>();
		//testEmpList = proj.getEmpProjList();
		//List<WorkPackage> testWpList = new ArrayList<WorkPackage>();
		//testWpList = proj.getWpList();
		
		// Expected Result
		final int expProjId = projId;
		final String expProjName = projName;
		final int expSupervisor = supervisor;
		final int expProjAss = proj.getProjAssistant();
		final Date expIssueDate = (Date) proj.getIssueDate();
		final String expDesc = proj.getDesc();
		final double expProposal = proj.getProposal();
		final double expInitBudget = proj.getInitBudget();
		final double expRo1Budget = proj.getRO1Budget();
		final double expRo2Budget = proj.getRO2Budget();
		final double expFinalBudget = proj.getFinalBudget();
		final int expManDaysP1 = proj.getManDaysP1();
		final int expManDaysP2 = proj.getManDaysP2();
		final int expManDaysP3 = proj.getManDaysP3();
		final int expManDaysP4 = proj.getManDaysP4();
		final int expManDaysP5 = proj.getManDaysP5();
		final int expManDaysDS = proj.getManDaysDS();
		final int expManDaysSS = proj.getManDaysSS();
		
		//List<Employee> expEmpList = new ArrayList<Employee>();
		//expEmpList = empList;
		//List<WorkPackage> expWpList = new ArrayList<WorkPackage>();
		//expWpList = wpList;
		
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
		
		// Testing Supervisor
		TestCase.assertEquals
		(
			"ProjGetSetTest Supervisor has FAILED", 
			expSupervisor, 
			testSupervisor
		);
		
		// Testing projAssistant
		TestCase.assertEquals
		(
			"ProjGetSetTest projAssistant has FAILED", 
			expProjAss, 
			testProjAss
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

		// Testing Proposal
		TestCase.assertEquals
		(
			"ProjGetSetTest Proposal has FAILED", 
			expProposal, 
			testProposal
		);
		
		// Testing InitBudget
		TestCase.assertEquals
		(
			"ProjGetSetTest InitBudget has FAILED", 
			expInitBudget, 
			testInitBudget
		);
		
		// Testing RO1Budget
		TestCase.assertEquals
		(
			"ProjGetSetTest RO1Budget has FAILED", 
			expRo1Budget, 
			testRo1Budget
		);
		
		// Testing RO2Budget
		TestCase.assertEquals
		(
			"ProjGetSetTest RO2Budget has FAILED", 
			expRo2Budget, 
			testRo2Budget
		);
		
		// Testing FinalBudget
		TestCase.assertEquals
		(
			"ProjGetSetTest FinalBudget has FAILED", 
			expFinalBudget, 
			testFinalBudget
		);
		
		// Testing ManDaysP1
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysP1 has FAILED", 
			expManDaysP1, 
			testManDaysP1
		);
		
		// Testing ManDaysP2
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysP2 has FAILED", 
			expManDaysP2, 
			testManDaysP2
		);
		
		// Testing ManDaysP3
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysP3 has FAILED", 
			expManDaysP3, 
			testManDaysP3
		);
		
		// Testing ManDaysP4
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysP4 has FAILED", 
			expManDaysP4, 
			testManDaysP4
		);
		
		// Testing ManDaysP5
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysP5 has FAILED", 
			expManDaysP5, 
			testManDaysP5
		);
		
		// Testing ManDaysDS
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysDS has FAILED", 
			expManDaysDS, 
			testManDaysDS
		);
		
		// Testing ManDaysSS
		TestCase.assertEquals
		(
			"ProjGetSetTest ManDaysSS has FAILED", 
			expManDaysSS, 
			testManDaysSS
		);
		
		// Testing ManDays
//		TestCase.assertEquals
//		(
//			"ProjGetSetTest ManDays has FAILED", 
//			expManDays, 
//			testManDays
//		);
//		
//		// Testing EmpList
//		TestCase.assertEquals
//		(
//			"ProjGetSetTest EmpList has FAILED", 
//			expEmpList, 
//			testEmpList
//		);
//		
//		// Testing WPList
//		TestCase.assertEquals
//		(
//			"ProjGetSetTest WPList has FAILED", 
//			expWpList, 
//			testWpList
//		);
	}
}
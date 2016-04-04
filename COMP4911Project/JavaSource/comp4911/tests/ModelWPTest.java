package comp4911.tests;

import junit.framework.TestCase;

import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;

import org.junit.Test;
//import comp4911.models.Employee;
import comp4911.models.WorkPackage;
import comp4911.models.Project;

/**
 * Testing WorkPackage Model
 */
public class ModelWPTest {

	@Test
	public void WPGetSetTest(){
		
		// Dummy Data for the Project Object
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
		
		//final List<WorkPackage> wpList = new ArrayList<WorkPackage>();

		final Date issueDate = Date.valueOf("2016-02-01");
		
		final String desc = "";
		
		final Project head = new Project();
		head.setProjectId(projId);
		head.setProjName(projName);
		head.setSupervisor(supervisor);
		//head.setEmpProjList(empList); // List of Employee
		//head.setWpList(wpList); // List of WorkPackage
		head.setProjAssistant(projAss);
		head.setIssueDate(issueDate);
		head.setDesc(desc);
		head.setProposal(proposal);
		head.setInitBudget(initBudget);
		head.setRO1Budget(ro1Budget);
		head.setRO2Budget(ro2Budget);
		head.setFinalBudget(finalBudget);
		head.setManDaysP1(manDaysProj1);
		head.setManDaysP2(manDaysProj2);
		head.setManDaysP3(manDaysProj3);
		head.setManDaysP4(manDaysProj4);
		head.setManDaysP5(manDaysProj5);
		head.setManDaysDS(manDaysProjDS);
		head.setManDaysSS(manDaysProjSS);
		
		//Dummy Data for work package
		final String wpId = "1";
		final String wpNum = "1";
		final String wpTitle = "Project Set-Up";
		final String customer = "ImaCustomer";
		final String respId = "ImaRespId";
		final int mdp1 = 1;
		final int mdp2 = 2;
		final int mdp3 = 3;
		final int mdp4 = 4;
		final int mdp5 = 5;
		final int mdds = 6;
		final int mdss = 7;
		final int projectId = 8;
		final boolean isActive = true;
		//final String contractor = "TEK Solutions";
		//final int userId = 000004;
		//final String payRateId = "P1";
		//final int manDays = 13;

		//final List<Employee> empList = new ArrayList<Employee>();
		
		final WorkPackage wp = new WorkPackage();
		wp.setWpId(wpId);
		wp.setWpNum(wpNum);
		wp.setWpTitle(wpTitle);
		wp.setCustomer(customer);
		wp.setRespId(respId);
		wp.setMdp1(mdp1);
		wp.setMdp2(mdp2);
		wp.setMdp3(mdp3);
		wp.setMdp4(mdp4);
		wp.setMdp5(mdp5);
		wp.setMdds(mdds);
		wp.setMdss(mdss);
		wp.setProjectId(projectId);
		wp.setActive(isActive);
		//wp.setContractor(contractor);
		//wp.setUserId(userId);
		//wp.setPayRateId(payRateId);
		//wp.setManDays(manDays);
		//wp.setEmpWPList(empList); // List of Employee
		//wp.setHead(head);

		// Test Result
		final String testWpId = wp.getWpId();
		final String testWpNum = wp.getWpNum();
		final String testWpTitle = wp.getWpTitle();
		final String testCustomer = wp.getCustomer();
		final String testRespId = wp.getRespId();
		final int testMdp1 = wp.getMdp1();
		final int testMdp2 = wp.getMdp2();
		final int testMdp3 = wp.getMdp3();
		final int testMdp4 = wp.getMdp4();
		final int testMdp5 = wp.getMdp5();
		final int testMdds = wp.getMdds();
		final int testMdss = wp.getMdss();
		final int testProjectId = wp.getProjectId();
		final boolean testIsActive = wp.isActive();
		//final String testContractor = wp.getContractor();
		//final int testUserId = wp.getSupervisor();
		//final String testPayRateId = wp.getPayRateId();
		//final int testManDays = wp.getManDays();

		//List<Employee> testEmpList = new ArrayList<Employee>();
		//testEmpList = wp.getEmpWpList();

		//Project testHead = new Project();
		//testHead = wp.getHead();

		// Expected Result
		final String expWpId = wpId;
		final String expWpNum = wpNum;
		final String expWpTitle = wpTitle;
		final String expCustomer = customer;
		final String expRespId = respId;
		final int expMdp1 = mdp1;
		final int expMdp2 = mdp2;
		final int expMdp3 = mdp3;
		final int expMdp4 = mdp4;
		final int expMdp5 = mdp5;
		final int expMdds = mdds;
		final int expMdss = mdss;
		final int expProjectId = wp.getProjectId();
		final boolean expIsActive = wp.isActive();
		//final String expContractor = contractor;
		//final int expUserId = userId;
		//final String expPayRateId = payRateId;
		//final int expManDays = manDays;

		//final List<Employee> expEmpList = new ArrayList<Employee>();
		//testEmpList = empList;

		//Project expHead = new Project();
		//expHead = head;

		// Testing WpId
		TestCase.assertEquals("WPGetSetTest WpId has FAILED", expWpId, testWpId);

		// Testing WpNum
		TestCase.assertEquals("WPGetSetTest WpNum has FAILED", expWpNum, testWpNum);

		// Testing WpTitle
		TestCase.assertEquals("WPGetSetTest WpTitle has FAILED", expWpTitle, testWpTitle);

		// Testing Customer
		TestCase.assertEquals("WPGetSetTest Customer has FAILED", expCustomer, testCustomer);

		// Testing RespId
		TestCase.assertEquals("WPGetSetTest RespId has FAILED", expRespId, testRespId);
		
		// Testing Mdp1
		TestCase.assertEquals("WPGetSetTest Mdp1 has FAILED", expMdp1, testMdp1);

		// Testing Mdp2
		TestCase.assertEquals("WPGetSetTest Mdp2 has FAILED", expMdp2, testMdp2);
		
		// Testing Mdp3
		TestCase.assertEquals(
			"WPGetSetTest Mdp3 has FAILED", 
			expMdp3, 
			testMdp3
		);
		
		// Testing Mdp4
		TestCase.assertEquals(
			"WPGetSetTest Mdp4 has FAILED", 
			expMdp4, 
			testMdp4
		);
		
		// Testing Mdp5
		TestCase.assertEquals(
			"WPGetSetTest Mdp5 has FAILED", 
			expMdp5, 
			testMdp5
		);
		
		// Testing Mdds
		TestCase.assertEquals(
			"WPGetSetTest Mdds has FAILED", 
			expMdds, 
			testMdds
		);
		
		// Testing Mdss
		TestCase.assertEquals(
			"WPGetSetTest Mdss has FAILED", 
			expMdss, 
			testMdss
		);
		
		// Testing ProjectId
		TestCase.assertEquals(
			"WPGetSetTest ProjectId has FAILED", 
			expProjectId, 
			testProjectId
		);
		
		// Testing isActive
		TestCase.assertEquals(
			"WPGetSetTest isActive has FAILED", 
			expIsActive, 
			testIsActive
		);
		
		// Testing Contractor
		//TestCase.assertEquals("WPGetSetTest Contractor has FAILED", expContractor, testContractor);

		// Testing UserId
		//TestCase.assertEquals("WPGetSetTest UserId has FAILED", expUserId, testUserId);

		// Testing PayRateId
		//TestCase.assertEquals("WPGetSetTest PayRateId has FAILED", expPayRateId, testPayRateId);

		// Testing ManDays
		//TestCase.assertEquals("WPGetSetTest ManDays has FAILED", expManDays, testManDays);

		// Testing EmpList
		//TestCase.assertEquals("WPGetSetTest EmpList has FAILED", expEmpList, testEmpList);

		// Testing Head
		//TestCase.assertEquals("WPGetSetTest Head has FAILED", expHead, testHead);
	}
}
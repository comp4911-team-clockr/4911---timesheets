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
 * Testing WorkPackage Model
 */
public class ModelWPTest {

	@Test
	public void WPGetSetTest(){

		final int wpId = 1;
		final int wpNum = 1;
		final String wpTitle = "Project Set-Up";
		final String contractor = "TEK Solutions";
		final int userId = 000004;
		final String payRateId = "P1";
		final int manDays = 13;

		final List<Employee> empList = new ArrayList<Employee>();

		// Dummy Data for the Project Object
		final int projId = 10;
		final String projName = "Project Alpha";
		final int manDaysProj = 300;
		final int supervisor = 2;

		final List<WorkPackage> wpList = new ArrayList<WorkPackage>();

		final Date issueDate = Date.valueOf("2016-02-01");
		
		final String desc = "";
		
		final Project head = new Project();
		head.setProjectId(projId);
		head.setProjName(projName);
		head.setManDays(manDaysProj);
		head.setSupervisor(supervisor);
		head.setEmpProjList(empList); // List of Employee
		head.setWpList(wpList); // List of WorkPackage
		head.setIssueDate(issueDate);
		head.setDesc(desc);

		final WorkPackage wp = new WorkPackage();
		wp.setWpId(wpId);
		wp.setWpNum(wpNum);
		wp.setWpTitle(wpTitle);
		wp.setContractor(contractor);
		wp.setUserId(userId);
		wp.setPayRateId(payRateId);
		wp.setManDays(manDays);
		wp.setEmpWPList(empList); // List of Employee
		wp.setHead(head);

		// Test Result
		final int testWpId = wp.getWpId();
		final int testWpNum = wp.getWpNum();
		final String testWpTitle = wp.getWpTitle();
		final String testContractor = wp.getContractor();
		final int testUserId = wp.getSupervisor();
		final String testPayRateId = wp.getPayRateId();
		final int testManDays = wp.getManDays();

		List<Employee> testEmpList = new ArrayList<Employee>();
		testEmpList = wp.getEmpWpList();

		Project testHead = new Project();
		testHead = wp.getHead();

		// Expected Result
		final int expWpId = wpId;
		final int expWpNum = wpNum;
		final String expWpTitle = wpTitle;
		final String expContractor = contractor;
		final int expUserId = userId;
		final String expPayRateId = payRateId;
		final int expManDays = manDays;

		final List<Employee> expEmpList = new ArrayList<Employee>();
		testEmpList = empList;

		Project expHead = new Project();
		expHead = head;

		// Testing WpId
		TestCase.assertEquals("WPGetSetTest WpId has FAILED", expWpId, testWpId);

		// Testing WpNum
		TestCase.assertEquals("WPGetSetTest WpNum has FAILED", expWpNum, testWpNum);

		// Testing WpTitle
		TestCase.assertEquals("WPGetSetTest WpTitle has FAILED", expWpTitle, testWpTitle);

		// Testing Contractor
		TestCase.assertEquals("WPGetSetTest Contractor has FAILED", expContractor, testContractor);

		// Testing UserId
		TestCase.assertEquals("WPGetSetTest UserId has FAILED", expUserId, testUserId);

		// Testing PayRateId
		TestCase.assertEquals("WPGetSetTest PayRateId has FAILED", expPayRateId, testPayRateId);

		// Testing ManDays
		TestCase.assertEquals("WPGetSetTest ManDays has FAILED", expManDays, testManDays);

		// Testing EmpList
		TestCase.assertEquals("WPGetSetTest EmpList has FAILED", expEmpList, testEmpList);

		// Testing Head
		TestCase.assertEquals("WPGetSetTest Head has FAILED", expHead, testHead);
	}
}
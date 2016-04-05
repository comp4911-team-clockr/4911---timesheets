package comp4911.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import comp4911.models.TimeSheet;
import comp4911.models.TimeSheetRow;
import junit.framework.TestCase;

/**
 * Testing TimeSheetRow model
 * 
 * @author Roscef
 *
 */
public class ModelTimeSheetRowTest {

	@Test
	public void TimeSheetRowGetSetTest() {

		// Dummy Data
		List<TimeSheetRow> timeSheetRowList = new ArrayList<TimeSheetRow>();
		
		final String timeSID = "1";
		final int empNum = 2;
		final int weekNum = 3;
		final double satTotalHrs = 4.0;
		final double sunTotalHrs = 4.0;
		final double monTotalHrs = 8.0;
		final double tueTotalHrs = 8.0;
		final double wedTotalHrs = 8.0;
		final double thrTotalHrs = 8.0;
		final double friTotalHrs = 8.0;
		final double overAllHrs = 48.0;
		//final double overTimeHrs = 10.0;
		final double flexTimeHrs = 11.0;
		final String signature = "Signature";
		final String isApproval = "approved";
		final boolean isActive = true;
		
		TimeSheet ts = new TimeSheet();
		ts.setTimeSheetRows(timeSheetRowList);
		ts.setTimeSID(timeSID);
		ts.setEmpNumber(empNum);
		ts.setWeekNumber(weekNum);
		ts.setSatTotalHrs(satTotalHrs);
		ts.setSunTotalHrs(sunTotalHrs);
		ts.setMonTotalHrs(monTotalHrs);
		ts.setTuesTotalHrs(tueTotalHrs);
		ts.setWedTotalHrs(wedTotalHrs);
		ts.setThursTotalHrs(thrTotalHrs);
		ts.setFriTotalHrs(friTotalHrs);
		ts.setOverallTotalHrs(overAllHrs);
		//ts.setOvertimeHrs(overTimeHrs);
		ts.setflextimeHrs(flexTimeHrs);
		ts.setSignature(signature);
		ts.setApproval(isApproval);
		ts.setIsActive(isActive);
		
		final String timeSheetRowId = "1";
		final int projId = 2;
		final int wpId = 4;
		final double satHrs = 4.0;
		final double sunHrs = 4.0;
		final double monHrs = 8.0;
		final double tueHrs = 8.0;
		final double wedHrs = 8.0;
		final double thrHrs = 8.0;
		final double friHrs = 8.0;
		final double totalWkHrs = 48.0;
		final String notes = "";
		final int vacationDays = 1;

		TimeSheetRow tsr = new TimeSheetRow();
		tsr.setTimeSheet(ts);
		tsr.setTimeSheetRowId(timeSheetRowId);
		tsr.setProjectId(projId);
		tsr.setWorkpackId(wpId);
		tsr.setSatHrs(satHrs);
		tsr.setSunHrs(sunHrs);
		tsr.setMonHrs(monHrs);
		tsr.setTuesHrs(tueHrs);
		tsr.setWedHrs(wedHrs);
		tsr.setThursHrs(thrHrs);
		tsr.setFriHrs(friHrs);
		tsr.setWeekTotalHrs(totalWkHrs);
		tsr.setNotes(notes);
		tsr.setVacationDays(vacationDays);
		tsr.setFlexTimeHrs(flexTimeHrs);
		tsr.setTimesheet(ts);
		

		// Test Result
		TimeSheet testTimeSheet = new TimeSheet();
		testTimeSheet = tsr.getTimeSheet();

		final String testTimeSheetRowId = tsr.getTimeSheetRowId();
		final int testProjId = tsr.getProjectId();
		final int testWpId = tsr.getWorkpackId();
		final double testSatHrs = tsr.getSatHrs();
		final double testSunHrs = tsr.getSunHrs();
		final double testMonHrs = tsr.getMonHrs();
		final double testTueHrs = tsr.getTuesHrs();
		final double testWedHrs = tsr.getWedHrs();
		final double testThrHrs = tsr.getThursHrs();
		final double testFriHrs = tsr.getFriHrs();
		final double testTotalWkHrs = tsr.getWeekTotalHrs();
		final String testNotes = tsr.getNotes();
		final int testVacDays = tsr.getVacationDays();
		final double testFlexTime = tsr.getFlexTimeHrs();
		
		TimeSheet testTS = new TimeSheet();
		testTS = tsr.getTimesheet();

		// Expected Result
		TimeSheet expTimeSheet = new TimeSheet();
		expTimeSheet = ts;

		final String expTimeSheetRowId = timeSheetRowId;
		final int expProjId = projId;
		final int expWpId = wpId;
		final double expSatHrs = satHrs;
		final double expSunHrs = sunHrs;
		final double expMonHrs = monHrs;
		final double expTueHrs = tueHrs;
		final double expWedHrs = wedHrs;
		final double expThrHrs = thrHrs;
		final double expFriHrs = friHrs;
		final double expTotalWkHrs = totalWkHrs;
		final String expNotes = notes;
		final int expVacDays = tsr.getVacationDays();
		final double expFlexTime = tsr.getFlexTimeHrs();
		
		TimeSheet expTS = new TimeSheet();
		expTS = tsr.getTimesheet();

		// Testing timesheet
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest timeSheet has FAILED", 
			expTimeSheet, 
			testTimeSheet
		);
		
		// Testing timeSheetRowId
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest EmpNumber has FAILED", 
			expTimeSheetRowId, 
			testTimeSheetRowId
		);
		
		// Testing projId
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest projId has FAILED", 
			expProjId, 
			testProjId
		);
		
		// Testing wpId
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest wpId has FAILED", 
			expWpId, 
			testWpId
		);
		
		// Testing satHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest satHrs has FAILED", 
			expSatHrs, 
			testSatHrs
		);
		
		// Testing sunHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest sunHrs has FAILED", 
			expSunHrs, 
			testSunHrs
		);
		
		// Testing monHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest monHrs has FAILED", 
			expMonHrs, 
			testMonHrs
		);
		
		// Testing tueHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest tueHrs has FAILED", 
			expTueHrs, 
			testTueHrs
		);
		
		// Testing wedHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest wedHrs has FAILED", 
			expWedHrs, 
			testWedHrs
		);
		
		// Testing thrHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest thrHrs has FAILED", 
			expThrHrs, 
			testThrHrs
		);
		
		// Testing friHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest friHrs has FAILED", 
			expFriHrs, 
			testFriHrs
		);
		
		// Testing totalWkHrs
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest totalWkHrs has FAILED", 
			expTotalWkHrs, 
			testTotalWkHrs
		);
		
		// Testing notes
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest notes has FAILED", 
			expNotes, 
			testNotes
		);
		
		// Testing VacDays
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest VacDays has FAILED", 
			expVacDays, 
			testVacDays
		);
		
		// Testing FlexTime
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest FlexTime has FAILED", 
			expFlexTime, 
			testFlexTime
		);
		
		// Testing Timesheet
		TestCase.assertEquals
		(
			"TimeSheetRowGetSetTest Timesheet has FAILED", 
			expTS, 
			testTS
		);
	}
}

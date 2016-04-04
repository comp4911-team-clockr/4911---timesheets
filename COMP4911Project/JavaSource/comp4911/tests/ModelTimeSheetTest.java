package comp4911.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import comp4911.models.TimeSheet;
import comp4911.models.TimeSheetRow;
import junit.framework.TestCase;

/**
 * Testing TimeSheet Model
 * 
 * @author Roscef
 *
 */
public class ModelTimeSheetTest {
	
	@Test
	public void TimeSheetGetSetTest(){
		
		//Dummy Data
		List<TimeSheetRow> timeSheetRowList = new ArrayList<TimeSheetRow>();
		
		final String timeSID = "1";
		final int empNum = 2;
		final int weekNum = 3;
		final double satHrs = 4.0;
		final double sunHrs = 4.0;
		final double monHrs = 8.0;
		final double tueHrs = 8.0;
		final double wedHrs = 8.0;
		final double thrHrs = 8.0;
		final double friHrs = 8.0;
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
		ts.setSatTotalHrs(satHrs);
		ts.setSunTotalHrs(sunHrs);
		ts.setMonTotalHrs(monHrs);
		ts.setTuesTotalHrs(tueHrs);
		ts.setWedTotalHrs(wedHrs);
		ts.setThursTotalHrs(thrHrs);
		ts.setFriTotalHrs(friHrs);
		ts.setOverallTotalHrs(overAllHrs);
		//ts.setOvertimeHrs(overTimeHrs);
		ts.setflextimeHrs(flexTimeHrs);
		ts.setSignature(signature);
		ts.setApproval(isApproval);
		ts.setIsActive(isActive);
		
		// Test Result
		List<TimeSheetRow> testTimeSheetRowList = new ArrayList<TimeSheetRow>();
		testTimeSheetRowList = ts.getTimeSheetRows();
		
		final String testTimeSID = ts.getTimeSID();
		final int testEmpNum = ts.getEmpNumber();
		final int testWeekNum = ts.getWeekNumber();
		final double testSatHrs = ts.getSatTotalHrs();
		final double testSunHrs = ts.getSunTotalHrs();
		final double testMonHrs = ts.getMonTotalHrs();
		final double testTueHrs = ts.getTuesTotalHrs();
		final double testWedHrs = ts.getWedTotalHrs();
		final double testThrHrs = ts.getThursTotalHrs();
		final double testFriHrs = ts.getFriTotalHrs();
		final double testOverAllHrs = ts.getOverallTotalHrs();
		//final double testOverTimeHrs = ts.getOvertimeHrs();
		final double testFlexTimeHrs = ts.getFlextimeHrs();
		final String testSignature = ts.getSignature();
		final String testIsApproval = ts.isApproval();
		final boolean testIsActive = ts.getIsActive();
		
		// Expected Result
		List<TimeSheetRow> expTimeSheetRowList = new ArrayList<TimeSheetRow>();
		expTimeSheetRowList = timeSheetRowList;
		
		final String expTimeSID = timeSID;
		final int expEmpNum = empNum;
		final int expWeekNum = weekNum;
		final double expSatHrs = satHrs;
		final double expSunHrs = sunHrs;
		final double expMonHrs = monHrs;
		final double expTueHrs = tueHrs;
		final double expWedHrs = wedHrs;
		final double expThrHrs = thrHrs;
		final double expFriHrs = friHrs;
		final double expOverAllHrs = overAllHrs;
		//final double expOverTimeHrs = overTimeHrs;
		final double expFlexTimeHrs = flexTimeHrs;
		final String expSignature = signature;
		final String expIsApproval = isApproval;
		final boolean expIsActive = isActive;
		
		// Testing timeSheetRowList
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest timeSheetRowList has FAILED", 
			expTimeSheetRowList, 
			testTimeSheetRowList
		);
		
		// Testing timeSID
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest timeSID has FAILED", 
			expTimeSID, 
			testTimeSID
		);
		
		// Testing empNum
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest empNum has FAILED", 
			expEmpNum, 
			testEmpNum
		);
		
		// Testing weekNum
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest weekNum has FAILED", 
			expWeekNum, 
			testWeekNum
		);
		
		// Testing satHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest satHrs has FAILED", 
			expSatHrs, 
			testSatHrs
		);
		
		// Testing sunHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest sunHrs has FAILED", 
			expSunHrs, 
			testSunHrs
		);
		
		// Testing monHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest monHrs has FAILED", 
			expMonHrs, 
			testMonHrs
		);
		
		// Testing tueHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest tueHrs has FAILED", 
			expTueHrs, 
			testTueHrs
		);
		
		// Testing wedHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest wedHrs has FAILED", 
			expWedHrs, 
			testWedHrs
		);
		
		// Testing thrHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest thrHrs has FAILED", 
			expThrHrs, 
			testThrHrs
		);
		
		// Testing friHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest friHrs has FAILED", 
			expFriHrs, 
			testFriHrs
		);
		
		// Testing overAllHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest overAllHrs has FAILED", 
			expOverAllHrs, 
			testOverAllHrs
		);
		
		// Testing overTimeHrs
//		TestCase.assertEquals
//		(
//			"TimeSheetGetSetTest overTimeHrs has FAILED", 
//			expOverTimeHrs, 
//			testOverTimeHrs
//		);
		
		// Testing flexTimeHrs
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest flexTimeHrs has FAILED", 
			expFlexTimeHrs, 
			testFlexTimeHrs
		);
		
		// Testing signature
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest signature has FAILED", 
			expSignature, 
			testSignature
		);
		
		// Testing isApproval
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest isApproval has FAILED", 
			expIsApproval, 
			testIsApproval
		);
		
		// Testing isActive
		TestCase.assertEquals
		(
			"TimeSheetGetSetTest isActive has FAILED", 
			expIsActive, 
			testIsActive
		);
	}

}

package comp4911.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all test classes for the models
 * 
 * @author Roscef
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ModelCredTest.class,
	ModelEmpTest.class,
	ModelProjTest.class,
	ModelTimeSheetTest.class,
	ModelTimeSheetRowTest.class,
	ModelWPTest.class
})

public class ModelTestSuite {

}

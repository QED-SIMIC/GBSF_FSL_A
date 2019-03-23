package harness;


import java.util.Date;

import util.WebDriverUtil;

public class ResultClerk {

	private static final String RESULT_LOG_DEFAULT_FILE = "C:\\Data\\Results\\QS_TestResultLog";

	private Date testBeginTimestamp;

	private static ResultClerk resultClerkOnDuty = new ResultClerk(RESULT_LOG_DEFAULT_FILE);
	private static Boolean lastTestPassed = false;
	// --------------------------------------------------------------------------------------

	// --------------------------------------------------------------------
	// Private Constructor to Make Singleton
	private ResultClerk(String resultLogFilePath) {
		// The commented lines below are left to illustrate how program
		// execution time can be recorded
		// this.logFileLineWriter = new FileLineWriter(resultLogFilePath,
		// FileLineWriter.APPEND_MODE_TRUE);
		/// this.executionTimeLog = new FileLineWriter(EXEC_TIME_LOG,
		// FileLineWriter.APPEND_MODE_TRUE);

	}

	// --------------------------------------------------------------------
	public static ResultClerk getInstance() {
		return resultClerkOnDuty;
	}

	// ----------------------------------------------------------------
	public void recordBeginTest(String methodName) {
		this.log("recordBeginTest => " + methodName);
	}

	// -----------------------------------------------------------------
	public Boolean hasTestPassed() {
		return ResultClerk.lastTestPassed;
	}

	// -----------------------------------------------------------------
	public Boolean hasTestFailed() {
		return !ResultClerk.lastTestPassed;
	}

	// -----------------------------------------------------------------
	public void recordTestPass(String testMethod) {
		
		StringBuffer sb = new StringBuffer();
		ResultClerk.lastTestPassed = true;

		sb.append("+++ recordTestPass ");
		sb.append(testMethod);
		this.log(sb.toString());
		// Here you can record result into the test repository tool

	}

	// -----------------------------------------------------------------
	private void logExecutionTime(String testMethod, String testCaseName) {
		StringBuffer sb = new StringBuffer();
		Date testEndTimestamp = null;
		WebDriverUtil wdu;

		sb.append(", ");
		// sb.append(TimeUtil.getTimeStampImage(testEndTimestamp));
		sb.append(", ");
		sb.append(testEndTimestamp);
		sb.append(testMethod);
		sb.append(("\n"));

		this.log(sb.toString());

		// Here you can record result into the test repository tool
		wdu = new WebDriverUtil();
		this.saveScreenshot(testMethod);
	}

	// -----------------------------------------------------------------
	public void recordTestFail(String testMethod, String exceptionTrace) {

		StringBuffer sb = new StringBuffer();
		ResultClerk.lastTestPassed = false;
		WebDriverUtil wdu;

		sb.append("!!! GB Automagic => FAIL");
		sb.append("Test Method => ");
		sb.append(testMethod);
		sb.append("\n !!! Exception =>");
		sb.append(exceptionTrace);
		this.log(sb.toString());

		// Here you can record result into the test repository tool
		wdu = new WebDriverUtil();
		this.saveScreenshot(testMethod);
		// wdu.close();

	}

	// ---------------------------------------------------------------------
	private void log(String message) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		// sb.append(timeStamp);
		sb.append("--");
		sb.append(message);
	}

	// ----------------------------------------------------------
	private void saveScreenshot(String methodName) {

		StringBuffer sb = new StringBuffer();
		WebDriverUtil wdu = new WebDriverUtil();

		sb.append("C:\\DATA\\Screenshots\\");
		sb.append(".");
		sb.append(methodName);
		sb.append(".png");

		wdu.takeScreenshot(sb.toString());
	}

}
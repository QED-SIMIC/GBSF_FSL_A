package sandboxa;


import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;

import env.Environment;
import harness.ResultClerk;
import harness.ResultMonitor;
import vm.Program;
import vm.VM;

public class DemoA_Test {

	
	private static ResultClerk resultClerk = ResultClerk.getInstance();
	private Environment env;
	private VM vm;
	private DemoA_Programs demoA_Programs;

	// --------------------------------------------------------
	@Rule
	public TestName currentTestName = new TestName();

	// --------------------------------------------------------
	@Rule
	public TestWatcher resultMonitor = ResultMonitor.watcher;

	// --------------------------------------------------------
	@BeforeClass
	public static void beforeClass() {
	}

	// --------------------------------------------------------
	@AfterClass
	public static void afterClass() {
	}

	// ----------------------------------------------------------
	@Before
	public void beforeTest() {

		BasicConfigurator.configure();
		this.resultClerk.recordBeginTest(this.currentTestName.getMethodName());
		this.vm = new VM();
		this.demoA_Programs = new DemoA_Programs();
	}

	// ----------------------------------------------------------
	@After
	public void afterTest() {
	}

	// ----------------------------------------------------------------------
	@Test
	// --------------------------------------------------------------------
	public void signInOnly() {
		Program p = this.demoA_Programs.getSignInOnly();
		this.vm.execute(p);
	}

}

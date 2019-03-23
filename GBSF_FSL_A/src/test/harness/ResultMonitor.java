package harness;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import env.RuntimeError;

public class ResultMonitor {

	// ------------------------------------------------------------------------
	public static TestWatcher watcher = new TestWatcher() {

		private ResultClerk resultClerk = ResultClerk.getInstance();

		// --------------------------------------------------------------------
		@Override
		protected void failed(Throwable ex, Description description) {

			String testMethod = description.getMethodName();
			resultClerk.recordTestFail(testMethod, RuntimeError.throwableToString(ex));

		}

		// --------------------------------------------------------------------
		@Override
		protected void succeeded(Description description) {

			String testMethod = description.getMethodName();
			resultClerk.recordTestPass(testMethod);

		}
	};
}

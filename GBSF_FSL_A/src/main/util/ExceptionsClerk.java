package util;


public class ExceptionsClerk {

	/**
	 * Provides a mechanism to unconditionally and intentionally throw and exception.
	 * Additional function is to format Throwable to String.
	 */

	/**
	 * Runtime Exception indicates that during the program execution,
	 * the program has encountered a problem that can occur at run time
	 * and that the program cannot recover from this problem.
	 * @param errorMessage
	 */
	// ------------------------------------------------------------------------
	public static void raiseRuntimeException(String errorMessage) {

		throw new RuntimeException(errorMessage);
	}

	/**
	 * The meaning of an AssertionError is that something happened that 
	 * the developer thought was impossible to happen.
	 * So if an AssertionError is ever thrown, it is a clear sign 
	 * of a programming error.
	 * param errorMessage
	 **/
	// ------------------------------------------------------------------------
	public static void raiseAssertionError(String errorMessage) {

		throw new AssertionError(errorMessage);
	}

	// ------------------------------------------------------------------------
	public static String throwableToString(Throwable ex) {

		StringBuffer sb = new StringBuffer();
		String exceptionImage = null;

		sb.append("\n +++");
		sb.append(ex.getMessage());
		sb.append("\n");
		sb.append("\n ---\n");

		for (StackTraceElement frame : ex.getStackTrace()) {
			sb.append(frame.toString());
			sb.append("\n");
		}
		exceptionImage = sb.toString();

		return exceptionImage;
	}

}

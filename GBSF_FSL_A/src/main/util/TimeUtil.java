package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	// -------------------------------------------------------------------
	public static String getCurrentTimeStamp() {
		SimpleDateFormat simpleDate;
		Date currentTime;
		String currentTimeString;

		simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentTime = new Date();
		currentTimeString = simpleDate.format(currentTime);
		return currentTimeString;
	}

	// ------------------------------------------------------------------
	public static boolean waitMilliseconds(int numMilliseconds) {

		try {
			Thread.sleep(numMilliseconds);
		} catch (Exception ex) {
			ExceptionsClerk.raiseRuntimeException(ExceptionsClerk.throwableToString(ex));
		}
		return true;
	}

	// -----------------------------------------------------------
	public static boolean waitSeconds(int numberOfSec) {

		return TimeUtil.waitMilliseconds(numberOfSec * 1000);
	}

}

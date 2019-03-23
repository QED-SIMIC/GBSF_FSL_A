package env;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import util.WebDriverUtil;

public class Environment {

	private final static String CHROME_WEB_DRIVER_FILE_PATH = "C:\\Selenium\\Driver\\chromedriver.exe";
	private final static String WORKDAY_SIGNIN_URL = "http://www.login.saleforce.com";

	private static Environment env = new Environment();

	
	private static final Point BROWSER_INITIAL_POSITION = new Point(0, 50);
	private final static Dimension DESKTOP_VIEWPORT = new Dimension(1300, 700);

	private WebDriver sessionDriver = null;
	private Map<String, WebDriver> sessions = null;

	private Point initialtBrowserPosition;
	private Dimension initialViewport;

	// ------------------------------------------------------------------------
	// Singleton
	// --------------------------------------------------------------------------
	private  Environment() {
		this.initialtBrowserPosition = BROWSER_INITIAL_POSITION;
		this.initialViewport = DESKTOP_VIEWPORT;
		this.sessionDriver = null;
		this.sessions = new HashMap<String, WebDriver>();
	}

	// ------------------------------------------------------------------------
	public static Environment getInstance() {
		return env;
	}

	// --------------------------------------------------------------------
	public WebDriver initiateSession(String sessionName) {

		if (this.initialtBrowserPosition == null) {
			this.initialtBrowserPosition = BROWSER_INITIAL_POSITION;
		} else {
			this.initialtBrowserPosition = new Point(this.initialtBrowserPosition.x + 700,
					this.initialtBrowserPosition.y + 300);
		}
		this.sessionDriver =
				WebDriverUtil.createAndSetWebDriver(CHROME_WEB_DRIVER_FILE_PATH,
				this.initialtBrowserPosition,
				DESKTOP_VIEWPORT);     
		this.sessions.put(sessionName, this.sessionDriver);

		return this.sessionDriver;
	}

	// ----------------------------------------------------------------------
	public String getSignInUrl() {
		return WORKDAY_SIGNIN_URL;
	}

	// --------------------------------------------------------------------
	public WebDriver getCurrentSessionDriver() {
		return this.sessionDriver;
	}

}
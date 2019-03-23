package util;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import env.Environment;

public class WebDriverUtil {

	private final static String CHROME_WB_DRIVER_PROPERTY = "webdriver.chrome.driver";

	private Environment env;
	private WebDriver driver;
	private String urlAtInstantiation;
	private Set<String> winHandlesAtInstantiation;

	public WebDriverUtil() {
		this.env = Environment.getInstance();
		this.driver = this.env.getCurrentSessionDriver();
		this.urlAtInstantiation = this.driver.getCurrentUrl();
		this.winHandlesAtInstantiation = this.driver.getWindowHandles();
	}

	// --------------------------------------------------------------------
	public static WebDriver createAndSetWebDriver(String chromeDriverFilePath, Point initialPosition,
			Dimension viewportDimension) {

		File chromeWebDriverExeFile;
		WebDriver driver;

		chromeWebDriverExeFile = new File(chromeDriverFilePath);
		System.setProperty(CHROME_WB_DRIVER_PROPERTY, chromeWebDriverExeFile.getAbsolutePath());

		driver = new ChromeDriver();

		driver.manage().window().setPosition(initialPosition);
		driver.manage().window().setSize(viewportDimension);

		return driver;
	}

	// --------------------------------------------------------------
	public void loadUrl(String url) {
		this.driver.get(url);
		this.waitForUrlChange(25);

	}

	// ------------------------------------------------------------------------
	public void loadAUrlNoWait(String url) {
		this.driver.get(url);
	}

	// --------------------------------------------------------------
	public void waitForUrlChange(int numOfSeconds) {
		String currentUrl = null;
		boolean urlChanged = false;

		for (int i = 1; i <= numOfSeconds; i++) {
			TimeUtil.waitSeconds(1);
			currentUrl = this.driver.getCurrentUrl();
			if (!this.urlAtInstantiation.equals(currentUrl)) {
				urlChanged = true;
				break;
			}
		}
		if (!urlChanged) {
			ExceptionsClerk.raiseRuntimeException("URL did not change, remained " + currentUrl);
		}
		this.urlAtInstantiation = currentUrl;
	}

	// ----------------------------------------------------------------
	public void waitForUrlChange() {
		this.waitForUrlChange(25);
		this.urlAtInstantiation = this.driver.getCurrentUrl();
	}

	// ----------------------------------------------------------------
	public String switchToNewWindow() {
		Set<String> winHandles = null;
		boolean newWinOpened = false;
		String newWinHandle = null;
		String currentUrl = null;

		for (int i = 0; i <= 10; i++) {
			TimeUtil.waitSeconds(1);
			winHandles = this.driver.getWindowHandles();
			if (winHandles.size() > this.winHandlesAtInstantiation.size()) {
				newWinOpened = true;
				break;
			}
		}
		if (!newWinOpened) {
			ExceptionsClerk.raiseRuntimeException("There is no new window opened");
		}
		for (String winHandle : winHandles) {
			if (!this.winHandlesAtInstantiation.contains(winHandle)) {
				newWinHandle = winHandle;
				break;
			}
		}
		driver.switchTo().window(newWinHandle);
		TimeUtil.waitSeconds(1);
		currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	// ----------------------------------------------------------
	public void setWindowSize(int winWidthPx, int winHeightPx) {
		Dimension windowSize;
		windowSize = new Dimension(winWidthPx, winHeightPx);
		this.driver.manage().window().setSize(windowSize);
	}

	// --------------------------------------------------------
	public void setWindowPosition(int winXPx, int winYPx) {
		Point windowPosition;
		windowPosition = new Point(winXPx, winYPx);
		this.driver.manage().window().setPosition(windowPosition);
	}
	
	// ------------------------------------------------------------
		public void waitElementToBeClickable(WebElement clickableWe) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(clickableWe));
		}

		// --------------------------------------------------------------
		public String getCurrentUtl() {
			return this.driver.getCurrentUrl();
		}

		// ------------------------------------------------------------
		public void scrollToView(WebElement element) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			TimeUtil.waitMilliseconds(500);
		}


}
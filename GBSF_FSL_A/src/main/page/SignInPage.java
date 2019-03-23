package page;


import env.Environment;
import util.TimeUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {

	private Environment env;
	private WebDriver driver;
	
	//-------------------------------------------------------------
	public SignInPage () {
		this.env = Environment.getInstance();
		this.driver = this.env.getCurrentSessionDriver();
	}
	
	// ----------------------------------------------------------
	public boolean isLoginUrl(String url) {
		return url.contains("workday");
	}

	//-----------------------------------------------------------
	public void signInAs(String userName, String password) {
		WebElement userNameWe = null;
		WebElement passwordWe = null;
		WebElement signInButtonWe = null;
		
		TimeUtil.waitSeconds(5);
		userNameWe = this.driver.findElement(By.xpath("//*[@data-automation-id=\"userName\"]/input"));
		userNameWe.clear();
		userNameWe.sendKeys(userName);
		
		passwordWe = this.driver.findElement(By.xpath("//*[@data-automation-id=\"password\"]/input"));
		passwordWe.sendKeys(password);
		
		signInButtonWe = this.driver.findElement(By.xpath("//*[@data-automation-id=\"goButton\"]"));
		signInButtonWe.click();
		
	}
	
}


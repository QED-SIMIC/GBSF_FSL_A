package util;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementUtil {

	/**
	 * Provide value added operations on top of WebElement:
	 * - Entering value in the text field
	 * - Select value from drop-down list by visible text
	 * @param driver
	 */
	public WebElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;

	// -----------------------------------------------------------------------
	public void setTextField(String text, String textFieldXpath) {
		WebElement textFieldWe;
		if (text != null) {
			textFieldWe = this.driver.findElement(By.xpath(textFieldXpath));
			textFieldWe.clear();
			textFieldWe.sendKeys(text);
		}
	}

	// -----------------------------------------------------------------------
	public void selectInDropDownListByVisibleText(String visibleText, String dropDownWeXpath) {
		WebElement dropDownWe;
		Select droplist = null;
		dropDownWe = this.driver.findElement(By.xpath(dropDownWeXpath));
		droplist = new Select(dropDownWe);
		droplist.selectByVisibleText(visibleText);
	}

}

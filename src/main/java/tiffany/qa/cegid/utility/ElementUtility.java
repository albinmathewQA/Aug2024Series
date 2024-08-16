package tiffany.qa.cegid.utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtility {

	private WebDriver driver;

	public ElementUtility(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendKeys(By locator, String value) {

		
	WebElement sendBox=getElement(locator);
	sendBox.clear();
	sendBox.sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doElementGetText(By locator) {
		String text = getElement(locator).getText();
		return text;
	}

	public boolean doElementdisplayed(By locator) {

		return getElement(locator).isDisplayed();

	}

	public List<WebElement> getElementS(By locator) {
		return driver.findElements(locator);

	}

	// For getting href value(Link) from anchor tag
	public void getElementAttribute(By locator, String attributeValue) {

		List<WebElement> eleList = getElementS(locator);
		for (WebElement e : eleList) {

			String attrValue = e.getAttribute(attributeValue);
			System.out.println(attrValue);
		}
	}

	// for getting count in list
	public int getElementCount(By locator) {
		int count = getElementS(locator).size();
		return count;

	}

	// For getting list of webelements
	public List<String> getElementTextList(By locator, String attributeValue) {

		List<WebElement> eleList = getElementS(locator);
		List<String> textlist = new ArrayList<String>();
		for (WebElement e : eleList) {

			String attrValue = e.getAttribute(attributeValue);
			textlist.add(attributeValue);
		}
		return textlist;
	}

	public WebElement waitforVisibilityofElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitforVisibilityofElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

}

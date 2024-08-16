package tiffany.qa.cegid.naveen.pages;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import tiffany.qa.cegid.appconstant.AppConstant;
import tiffany.qa.cegid.utility.ElementUtility;

public class LoginPage {

	private WebDriver driver;
	private ElementUtility elementUtil;

//private locators
	private By userId = By.id("input-email");
	private By password = By.id("input-password");
	private By Loginbuton = By.xpath("(//input[@value='Login'])");

//Page constructor -Driver
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		elementUtil = new ElementUtility(driver);

	}

//Page actions //Method

	@Step("Getting the page title step")
	public String getTitle() {
		String title = driver.getTitle();
		System.out.println("Login page title is" + title);
		return title;

	}

	public String getCurrentURL() {
		String currentURL = driver.getCurrentUrl();

		return currentURL;
	}
	@Step("Login with below username : {0} and password is {1}")
	public AccountPage dologin(String user, String pwd) {
		
		elementUtil.waitforVisibilityofElement(userId, AppConstant.DEFAULT_LONG_TIMEOUT).sendKeys(user);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(Loginbuton);
		//driver.findElement(userId).sendKeys(user);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(Loginbuton).click();
		return new AccountPage(driver);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement doSendKeys(By locator, String vale) {

		return null;

	}
}

package tiffany.qa.cegid.naveen.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import tiffany.qa.cegid.appconstant.AppConstant;
import tiffany.qa.cegid.base.BaseTest;

//Allure functionlaity optional
@Epic("Epic 1:  Login for Naveen Automation")
@Story("US login -Test1")

public class LoginTest extends BaseTest {

	//Allure functionlaity optional
	@Severity(SeverityLevel.CRITICAL)
	@Description("Getting the title of the Page")
	
	@Test(priority = 1)
	public void loginPageTest() {
		String title = loginpage.getTitle();
		Assert.assertEquals(title, AppConstant.LOGIN_PAGE_TITLE);

	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Getting the current URL of the Page")
	@Test(priority = 2)
	public void getCurrentURL() {
		String currentURL = loginpage.getCurrentURL();
		Assert.assertTrue(currentURL.contains("naveenautomationlabs"));
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("Login Functionality")
	@Test(priority = 3)
	public void doLogin() {

		accountpage = loginpage.dologin(prop.getProperty("username").trim().toLowerCase(),prop.getProperty("password").trim());
		Assert.assertTrue(accountpage.islogoutButtonExist());
//"tiffany.cegid@gmail.com", "Test@123"
	}

}

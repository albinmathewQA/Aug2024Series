package tiffany.qa.cegid.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import tiffany.qa.cegid.factory.DriverFactory;
import tiffany.qa.cegid.naveen.pages.AccountPage;
import tiffany.qa.cegid.naveen.pages.LoginPage;
import tiffany.qa.cegid.naveen.pages.ProductInfoPage;
import tiffany.qa.cegid.naveen.pages.SearchResult;


public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginpage;
	protected AccountPage accountpage;
	protected  SearchResult searchresult;
	protected ProductInfoPage productinfopage;
	protected Properties prop;
	
	protected SoftAssert softassert;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop=df.initProp();
		driver = df.iniDriver(prop);
		loginpage = new LoginPage(driver);
		softassert=new SoftAssert();

	}

	@AfterTest

	public void teardown() {
		driver.quit();
	}
}

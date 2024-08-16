package tiffany.qa.cegid.naveen.test;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tiffany.qa.cegid.appconstant.AppConstant;
import tiffany.qa.cegid.base.BaseTest;

public class AccountTest extends BaseTest {

	@BeforeClass
	public void accountSetUp() {
		accountpage = loginpage.dologin(prop.getProperty("username").trim().toLowerCase(),
				prop.getProperty("password").trim());
	}

	@Test
	public void accpageTitleTest() {
		String accntTitle = accountpage.accntPageTitle();
		Assert.assertEquals(accntTitle, AppConstant.ACCOUNT_PAGE_TITLE);

	}

	@Test
	public void currentURLTest() {
		String currentURL = accountpage.accntCurrentURL();
		Assert.assertTrue(currentURL.contains("account"));
	}

	@Test
	public void accPageHeaderListCount() {
		List<String> actualAccountHeader = accountpage.getAccountHeaderList();
		System.out.println("Actual header list is   " + actualAccountHeader);
		Assert.assertEquals(actualAccountHeader.size(), AppConstant.ACCOUNT_HEADER_COUNT);
	}

	@Test
	public void accPageHeaderValueTest() {
		List<String> actualAccountHeader = accountpage.getAccountHeaderList();
		System.out.println("Actual header list is   " + actualAccountHeader);
		System.out.println("Expected  header list is   " + AppConstant.EXPECTED_ACCOUNT_HEADER_LIST);
		Assert.assertEquals(actualAccountHeader, AppConstant.EXPECTED_ACCOUNT_HEADER_LIST);

	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Apple" }, { "Samsung" }

		};
	}

	@Test(dataProvider = "getProductTestData")
	public void searchTest(String searchKey) {
		searchresult = accountpage.performSearch(searchKey);
		Assert.assertTrue(searchresult.getSearchProductCount() >= 1);

	}
	
	@DataProvider
	
	public Object[][] getProductDetail()
	{
	
		return new Object[][] {
			{ "Macbook" ,"MacBook Air"},
			{ "iMac" ,"iMac"}, 
			{ "Apple","Apple Cinema 30\"" },
			{ "Samsung" ,"Samsung Galaxy Tab 10.1"}
		};
		
	}
	
	
	
@Test(dataProvider = "getProductDetail")
	public void searchProductTest(String searchKey,String productDetail) {
		searchresult = accountpage.performSearch(searchKey);
		if ((searchresult.getSearchProductCount() >= 1)) {
			productinfopage = searchresult.selectProduct(productDetail);
			assertEquals(productinfopage.getProductName(), productDetail);
		}

	}

}

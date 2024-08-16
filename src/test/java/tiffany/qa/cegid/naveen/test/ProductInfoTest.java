package tiffany.qa.cegid.naveen.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tiffany.qa.cegid.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetUp() {

		accountpage = loginpage.dologin(prop.getProperty("username").trim().toLowerCase(),
				prop.getProperty("password").trim());
	}

	@DataProvider

	public Object[][] getProductImage() {

		return new Object[][] { { "Macbook", "MacBook Air", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung Galaxy Tab 10.1", 1 } };

	}

	@Test(dataProvider = "getProductImage")
	public void productImageCountTest(String searckkey, String productname, int actualimage) {
		searchresult = accountpage.performSearch("Macbook");
		productinfopage = searchresult.selectProduct("MacBook Air");
		int imagecount = productinfopage.getImageCount();

		Assert.assertEquals(imagecount, 4);

	}

	@Test

	public void productTabvalidatin() {
		Assert.assertEquals(productinfopage.tabPresentTest(), true);
	}

@Test
	public void productInfoTest() {
		searchresult = accountpage.performSearch("Macbook");
		productinfopage = searchresult.selectProduct("MacBook Air");
		Map<String, String> productinfoMap = productinfopage.getProducInfo();
		System.out.println(productinfoMap);
		
		softassert.assertEquals(productinfoMap.get("Brand"), "Apple");
		softassert.assertEquals(productinfoMap.get("Availability"), "Out Of Stock");
		softassert.assertEquals(productinfoMap.get("Product Name"), "MacBook Air");
		softassert.assertEquals(productinfoMap.get("Product Code"), "Product 17");
		softassert.assertEquals(productinfoMap.get("Reward Points"), "700");
		softassert.assertEquals(productinfoMap.get("taxexcl"), "Ex Tax: $1,000.00");
		softassert.assertEquals(productinfoMap.get("productPrice"), "$1,202.00");
		
		softassert.assertAll();

	}

@Test
public void addtoCart() throws InterruptedException
{
	searchresult = accountpage.performSearch("Macbook");
	productinfopage = searchresult.selectProduct("MacBook Air");
	Thread.sleep(200);
	productinfopage.enterQuantity(2);
	String successMsg=productinfopage.addProducttoCart();
	
	softassert.assertTrue(successMsg.contains("Success"));
	softassert.assertEquals("Success: You have added MacBook Air to your shopping cart!", 	successMsg);
	softassert.assertAll();
}

}

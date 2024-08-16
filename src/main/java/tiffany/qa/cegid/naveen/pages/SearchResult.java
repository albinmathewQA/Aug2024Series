package tiffany.qa.cegid.naveen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tiffany.qa.cegid.appconstant.AppConstant;
import tiffany.qa.cegid.utility.ElementUtility;

public class SearchResult {

	private WebDriver driver;
	private ElementUtility eleUtil;
	

	private By searchProductCount = By.cssSelector("div.row div.product-layout");

	public SearchResult(WebDriver driver) {

		this.driver = driver;
		eleUtil=new ElementUtility(driver);
		
	}

	public int getSearchProductCount()
	{
		int count=eleUtil.waitforVisibilityofElements(searchProductCount, AppConstant.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product count is "+count);
		return count;
	}
	
	
	public ProductInfoPage selectProduct(String Productname)
	{
		By productLocator=By.linkText(Productname);
		eleUtil.waitforVisibilityofElements(productLocator, AppConstant.DEFAULT_LONG_TIMEOUT);
		eleUtil.waitforVisibilityofElement(productLocator, AppConstant.DEFAULT_LONG_TIMEOUT).click();
		
		return new ProductInfoPage (driver);
		
	}
	
}

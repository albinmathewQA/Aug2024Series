package tiffany.qa.cegid.naveen.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tiffany.qa.cegid.appconstant.AppConstant;
import tiffany.qa.cegid.utility.ElementUtility;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtility eleUtil;
	
	private By productHeader=By.tagName("h1");
	private By productimages=By.cssSelector("ul.thumbnails img");
	private By producttab=By.cssSelector("li.active a");
	
	private By productMetadata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricedata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity=By.id("input-quantity");
	private By addtoCartButton=By.id("button-cart");
	private By alertMessage=By.cssSelector("div.alert.alert-success");
	
	
	

	private Map<String, String> productinfoMap;
	

	public ProductInfoPage(WebDriver driver) {
		
	this.driver=driver;	
	this.eleUtil=new ElementUtility(driver);
	
	}
	
	
	public String getProductName() {
	
		String ProductheaderValue=eleUtil.doElementGetText(productHeader);
		return ProductheaderValue;
	}
	
	public int getImageCount()
	{
		eleUtil.waitforVisibilityofElement(productimages, AppConstant.DEFAULT_LONG_TIMEOUT);
		int imagecount=eleUtil.getElementS(productimages).size();
		
		
		return imagecount;
	}
	
	public boolean tabPresentTest()
	{
	return eleUtil.doElementdisplayed(producttab);
	
	}
	
	
	public Map<String, String> getProducInfo()
	{
		
		
		 productinfoMap=new HashMap<String, String>();
		 productinfoMap.put("Product Name", getProductName());
		 System.out.println("Product name is ## "+getProductName());

		getProductMetaInfo();
		getPrdouctPrice();
		return productinfoMap;
		
		}
	
		
	
	
	private void getProductMetaInfo()
	{
	List<WebElement> metaList=eleUtil.getElementS(productMetadata); 
		
		for (WebElement e: metaList)
		{
		String meta=e.getText();	
		String metainfo[]=meta.split(":");
		String key=metainfo[0].trim();
		String value=metainfo[1].trim();
		
		productinfoMap.put(key, value);
		
		}
	}
	
	
	
		private void getPrdouctPrice()
		{
		
		
		List<WebElement> priceList=eleUtil.getElementS(productPricedata);
		String price=priceList.get(0).getText();
		String excprice=priceList.get(1).getText();
		productinfoMap.put( "productPrice",price);
		productinfoMap.put( "taxexcl",excprice);

		}
		

		public void enterQuantity(int qty)
		{
			eleUtil.waitforVisibilityofElement(quantity, AppConstant.DEFAULT_LONG_TIMEOUT);
			eleUtil.doSendKeys(quantity, String.valueOf(qty));	
		}

	
		public String addProducttoCart() 
		{
			eleUtil.doClick(addtoCartButton);
			String successMsg= eleUtil.waitforVisibilityofElement(alertMessage, AppConstant.DEFAULT_LONG_TIMEOUT).getText();
			StringBuilder sb=new StringBuilder(successMsg);
			String msg=sb.substring(0, successMsg.length()-1).replace("\n", "");
			System.out.println("Cart successmsg "+msg);
			return msg;
			
		}
		
	

}

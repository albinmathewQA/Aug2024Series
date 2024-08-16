package tiffany.qa.cegid.naveen.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tiffany.qa.cegid.utility.ElementUtility;

public class AccountPage {

	private WebDriver driver;
	private ElementUtility elementUtil;

	private By logoutlink = By.linkText("Logout");
	private By searchBox = By.name("search");
	private By accountHeader = By.cssSelector("div#content h2");
	private By searchButton=By.cssSelector("#search button");
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtility(driver);

	}

	public String accntPageTitle() {
		String accntTitle = driver.getTitle();
		return accntTitle;
	}

	public String accntCurrentURL() {
		String accCurrentURL = driver.getCurrentUrl();
		return accCurrentURL;
	}

	public boolean islogoutButtonExist() {

		return elementUtil.waitforVisibilityofElement(logoutlink, 10).isDisplayed();
	}

	public boolean isSearchexist() {
		return elementUtil.waitforVisibilityofElement(searchBox, 10).isDisplayed();
	}

	public List<String> getAccountHeaderList() {
		List<WebElement> headerList =elementUtil.waitforVisibilityofElements(accountHeader, 10);
		List<String> accountHeaderList = new ArrayList<String>();

		for (WebElement e : headerList) {
			String text = e.getText();
			accountHeaderList.add(text);

		}

		return accountHeaderList;

	}
	
	public SearchResult performSearch(String SearchKey)
	{
		if (isSearchexist() )
		{
			elementUtil.doSendKeys(searchBox, SearchKey);
			elementUtil.doClick(searchButton);
			
			return new SearchResult(driver);
		}
		else
		{
			System.out.println("Search is not present in the page");
			return null;

		}
		
		
	}
}

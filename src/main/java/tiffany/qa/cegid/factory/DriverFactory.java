package tiffany.qa.cegid.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionManager OptionManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is initializing driver on the basis of given browser
	 * 
	 * @param browserName
	 * @return driver
	 */
	public WebDriver iniDriver(Properties prop) {

		OptionManager = new OptionManager(prop);
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("browser name is" + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("I am in Browser method");
			// driver = new ChromeDriver(OptionManager.getChromeoptions());
			tlDriver.set(new ChromeDriver(OptionManager.getChromeoptions()));

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());

		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());

		}

		else {
			System.out.println("Pass the correct browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method reading the property from property file
	 * 
	 * @return
	 */
	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip=null;
		String env = System.getProperty("env");
		System.out.println("Running the test cases on the env" + env);

		try {
		
		if (env == null) {
			System.out.println("No env is not passed ..Running on QA env");
			ip = new FileInputStream("./src/main/resources/config/config.properties");

		}

		else {
			switch (env.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/main/resources/config/qa.properties");
				break;

			case "stage":
				ip = new FileInputStream("./src/main/resources/config/stage.properties");

				break;

			case "prod":
				ip = new FileInputStream("./src/main/resources/config/prod.properties");

				break;

			default:
				System.out.println("Wrong env is passed ,please pass correct env");
				break;
			}
		}
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return prop;
		
	}

	/*
	 * To take screenshot
	 */
	public static String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

		File dest = new File(path);

		try {
			FileUtil.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}

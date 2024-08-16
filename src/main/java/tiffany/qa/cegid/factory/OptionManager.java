package tiffany.qa.cegid.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeoptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
			System.out.println(" I am in incogninto mode");

		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			co.addArguments("--incongito");
		return co;
	}

	public FirefoxOptions getFirefoxoptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println(" I am in incogninto mode");

			fo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			co.addArguments("--incongito");
		return fo;

	}

	public EdgeOptions getEdgeoxoptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			fo.addArguments("--incongito");
		return eo;

	}
}

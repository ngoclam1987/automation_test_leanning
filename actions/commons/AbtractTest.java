package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbtractTest {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	public enum Browser {
		FIREFOX, CHROME, HFIREFOX, HCHROME,
	}

	public WebDriver getBrowerDriver(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());

		switch (browser) {
		case FIREFOX: {	
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case HFIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-headless");
			firefoxOptions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case HCHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("headless");
			chromeOptions.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeOptions);
			break;
		default:
			throw new RuntimeException("Name browser not found");
		}
		driver.get("http://demo.guru99.com/v4/");
		return driver;
	}

}

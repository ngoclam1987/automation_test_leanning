package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_Check_Environment {
	WebDriver driver;
	
  @Test
  public void TC_01_Verify_Title() {
	  driver.get("https://testng.org/doc/");
	  String mavenTitle = driver.getTitle();
	  Assert.assertEquals(mavenTitle, "TestNG - Welcome");
  }
  @Test
  public void TC_02_Verify_Url() {
	  String mavenUrl = driver.getCurrentUrl();
	  Assert.assertEquals(mavenUrl, "https://testng.org/doc/");
  }
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver",".\\browseDriver\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

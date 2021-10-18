package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbtractPage;
import pageUI.MainPageUI;

public class MainPageObject  extends AbtractPage{

	WebDriver driver;
	
	public MainPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getWelcomeMassageText() {
		awaitToElementVisible(driver, MainPageUI.WECOME_TEXT);
		return getElementText(driver, MainPageUI.WECOME_TEXT);
	}

	public void openNewCustomerPage() {
		awaitToElementClickable(driver,MainPageUI.NEWCUSTOMER_LINK);
		clickToElement(driver, MainPageUI.NEWCUSTOMER_LINK);
	}

}

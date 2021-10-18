package pageObjects;

import javax.xml.xpath.XPathEvaluationResult.XPathResultType;

import org.openqa.selenium.WebDriver;

import commons.AbtractPage;
import pageUI.NewCustomerPageUI;

public class NewCustomerPageObject  extends AbtractPage{

	WebDriver driver;
	
	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToNameTextbox(String name) {
		awaitToElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, name);
	}

	public void inputToBrithOfDateTextbox(String date) {
		awaitToElementVisible(driver, NewCustomerPageUI.DAY_OF_BIRTH_TEXTBOX);
		removeAttributeInDOM(driver, NewCustomerPageUI.DAY_OF_BIRTH_TEXTBOX, "type");
		sendkeyToElement(driver, NewCustomerPageUI.DAY_OF_BIRTH_TEXTBOX, date);
	}

	public void inputToAddressAreabox(String address) {
		awaitToElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTAREA);
		sendkeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTAREA, address);
	}

	public void inputToCityTextbox(String city) {
		awaitToElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, city);
	}

	public void inputToStateTextbox(String state) {
		awaitToElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, state);
	}

	public void inputToPinTextbox(String pin) {
		awaitToElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, pin);
	}

	public void inputToMobleNumberTextbox(String mobileNumber) {
		awaitToElementVisible(driver, NewCustomerPageUI.MOBILE_NUMBER_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.MOBILE_NUMBER_TEXTBOX, mobileNumber);
	}

	public void inputToEmailTextbox(String email) {
		awaitToElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		awaitToElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickToSubmitButton() {
		awaitToElementClickable(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
	}

	public String getSuccessMessage() {
		awaitToElementVisible(driver, NewCustomerPageUI.SUCCESS_TEXT);
		sleepInSecond(1);
		String result = getElementText(driver, NewCustomerPageUI.SUCCESS_TEXT);
		System.out.println("get text add custommer success--->" +result);
		return result;
	}

	public void clickToLogoutLink() {
		awaitToElementClickable(driver, NewCustomerPageUI.LOGOUT_LINK);
		clickToElement(driver, NewCustomerPageUI.LOGOUT_LINK);
		
		waitToAlertPresence(driver);
		acceptAlert(driver);
	}

}

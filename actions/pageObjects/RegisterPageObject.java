package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbtractPage;
import pageUI.RegisterPageUI;

public class RegisterPageObject  extends AbtractPage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTexbox(String email) {
		awaitToElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickToSubmitButton() {
		awaitToElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDText() {
		awaitToElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getElementText(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordText() {		
		awaitToElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getElementText(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	public void openLoginPage(String loginUrl) {
		openUrl(driver, loginUrl);
		
	}

}

package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbtractPage;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbtractPage{
	
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToHereLink() {
		awaitToElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
	}

	public String getLoginPageUrl() {
		return getCurrentPageUrl(driver);
	}
	public void inputToUserIDTextbox(String userID) {
		awaitToElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userID);
	}

	public void inputToPasswordTextbox(String password) {
		awaitToElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickToLoginButton() {
		awaitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
	}
	
	public boolean isLoginFormDisplayed() {
		awaitToElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}

}

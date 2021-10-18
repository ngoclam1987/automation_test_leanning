package com.bankgaru.user;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbtractTest;
import pageObjects.LoginPageObject;
import pageObjects.MainPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

@Test
public class Level_03_Page_Object_Pattern extends AbtractTest {
	WebDriver driver;
	String loginUrl, userID, password;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MainPageObject mainPage;
	NewCustomerPageObject newCustomerPage;
	
	String name, date, brithOfDay, address, city, state, pin, mobile, email, passwordCustomer;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowerDriver(browserName);
		loginPage = new LoginPageObject(driver);
		name = "minh";
		date = "05-06-2011";
		address = "Hoai Duc";
		city = "Ha Noi";
		state = "Viet Nam";
		pin = "123456";
		mobile = "0979578686";
		email = "evotek"+ randomInterger() + "@gmail.com" ;
		passwordCustomer = "12345678";
	}

	public void TC_01_Register_To_System() {
		System.out.println("Register - Step 01: Get Login Page Url");
		loginUrl = loginPage.getLoginPageUrl();
		System.out.println("Register - Step 02: Click to here link");
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver);
		System.out.println("Register - Step 03: Input to email texbox");
		registerPage.inputToEmailTexbox("mygmailinfo"+ randomInterger() + "@gmail.com");
		System.out.println("Register - step 04: click submit button");
		registerPage.clickToSubmitButton();
		System.out.println("Register - step 05: get UserID/Passowrd information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
	}
	
	public void TC_02_Login_To_System() {
		System.out.println("Login - Step 01: Open Login Page");
		registerPage.openLoginPage(loginUrl);
		loginPage = new LoginPageObject(driver);
		System.out.println("Login - Step 02: Input to UserID/ Password texbox");
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		System.out.println("Login - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();
		mainPage = new MainPageObject(driver);
		System.out.println("Login - Step 04: Navigate to Home Page");
		Assert.assertEquals(mainPage.getWelcomeMassageText(),"Welcome To Manager's Page of Guru99 Bank");
	}
	
	public void TC_03_New_Customer() {
		System.out.println("NewCustomer -Step 01: Open new customer page");
		mainPage.openNewCustomerPage();
		newCustomerPage = new NewCustomerPageObject(driver);
		
		System.out.println("NewCustomer - Step 02: Input to name textbox");
		newCustomerPage.inputToNameTextbox(name);
		
		System.out.println("NewCustomer - Step 03: Input to BrithOfDate textbox");
		newCustomerPage.inputToBrithOfDateTextbox(date);
		
		System.out.println("NewCustomer - Step 04: Input to Address textbox");
		newCustomerPage.inputToAddressAreabox(address);
		
		System.out.println("NewCustomer - Step 05: Input to City textbox");
		newCustomerPage.inputToCityTextbox(city);
		
		System.out.println("NewCustomer - Step 06: Input to State textbox");
		newCustomerPage.inputToStateTextbox(state);
		
		System.out.println("NewCustomer - Step 07: Input to PIN textbox");
		newCustomerPage.inputToPinTextbox(pin);
		
		System.out.println("NewCustomer - Step 08: Input to MobleNumber textbox");
		newCustomerPage.inputToMobleNumberTextbox("0979797979");
		
		System.out.println("NewCustomer - Step 09: Input to Email textbox");
		newCustomerPage.inputToEmailTextbox(email);
		
		System.out.println("NewCustomer - Step 10: Input to Password textbox");
		newCustomerPage.inputToPasswordTextbox(passwordCustomer);
		
		System.out.println("NewCustomer - Step 11: Input to Submit Button");
		newCustomerPage.clickToSubmitButton();
		
		System.out.println("NewCustomer - Step 12: Verify Success massage displayed");
		Assert.assertEquals(newCustomerPage.getSuccessMessage(), "Customer Registered Successfully!!!");
	}
	
	public void TC_04_Logout() {
		System.out.println("Logout - Step 01: Click to logout link");
		newCustomerPage.clickToLogoutLink();
		loginPage = new LoginPageObject(driver);
		System.out.println("Logout - Step 02: Verify Login form displayed");
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String randomInterger() {
		Random ran = new Random(999);
		int rusult = ran.nextInt();
		return Integer.toString(rusult);
	}
}

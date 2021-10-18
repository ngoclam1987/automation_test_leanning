package commons;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbtractPage {

	private Alert alert;
	private Select select;
	private Actions action;
	private WebElement element;
	private long longTimeOut = 30;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;

	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refeshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void waitToAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String key) {
		alert = driver.switchTo().alert();
		alert.sendKeys(key);
	}

	public String getTextToAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void switchToWindowsToID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowsToTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement find(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		find(driver, locator).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		find(driver, locator).clear();
		find(driver, locator).sendKeys(value);
	}

	public void selectItemDropdown(WebDriver driver, String locator, String itemValue) {
		select = new Select(find(driver, locator));
		select.selectByVisibleText(itemValue);
	}

	public String getFistSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropDownMultiple(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		find(driver, parentLocator).click();
		sleepInSecond(1);
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childLocator)));
		List<WebElement> allItems = finds(driver, childLocator);
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return find(driver, locator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		String result = find(driver, locator).getText();
		System.out.println("new custommer success------> "+result);
		return result;
	}

	public int countElementSize(WebDriver driver, String locator) {
		return finds(driver, locator).size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		if (!find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String locator) {
		if (find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		return find(driver, locator).isDisplayed();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		return find(driver, locator).isEnabled();
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		return find(driver, locator).isSelected();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(find(driver, locator));
	}

	public void switchToDefaultPage(WebDriver driver, String locator) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(find(driver, locator)).perform();
	}

	public void hoverClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(find(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, sourceLocator), find(driver, targetLocator)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.math('" + textExpected + "'");
		return textActual.equals(textExpected);
	}

	public void scrollBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("argument[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "');", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('"+ attributeRemove +"');", element);
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
	}

	public void awaitToElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}

	public void awaitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void awaitToElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void awaitToElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}
}

package headspin.testApp.utils;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Actions {

	AndroidDriver driver;
	WebDriverWait wait;

	public Actions(AndroidDriver driver) {
		this.driver = driver;
	}

	public void enterText(WebElement element, String text) {
		try {
			element.clear();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		} catch (StaleElementReferenceException e) {
			hardWait();
			element.clear();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		}
	}

	public void clickOnElement(WebElement element) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element)).click();
		} catch (StaleElementReferenceException e) {
			hardWait();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element)).click();
		}
	}

	public String getText(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public void setActivity(String appPackage, String appActivity) {
		driver.startActivity(new Activity(appPackage, appActivity));
	}

	public void waitForElementToDisappear(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));	
	}
	
	public void hardWait() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

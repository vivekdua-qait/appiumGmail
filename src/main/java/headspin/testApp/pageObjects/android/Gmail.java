package headspin.testApp.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import headspin.testApp.utils.Actions;
import io.appium.java_client.android.AndroidDriver;

public class Gmail extends Actions {

	AndroidDriver driver;

	public Gmail(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "com.google.android.gm:id/welcome_tour_got_it")
	private WebElement gotItButton;

	@FindBy(id = "com.google.android.gm:id/setup_addresses_add_another")
	private WebElement email;

	@FindBy(xpath = "//android.widget.TextView[@text='Yahoo']/..")
	private WebElement emailAccount;

	@FindBy(id = "login-username")
	private WebElement userName;

	@FindBy(id = "login-signin")
	private WebElement nextButton;

	@FindBy(id = "recaptcha-anchor")
	private WebElement captcha;

	@FindBy(id = "recaptcha-submit")
	private WebElement captchaSubmit;

	@FindBy(id = "login-passwd")
	private WebElement password;

	@FindBy(id = "oauth2-agree")
	private WebElement agreeButton;

	@FindBy(id = "com.google.android.gm:id/compose_button")
	private WebElement composeButton;

	@FindBy(xpath = "//android.view.ViewGroup//android.widget.EditText")
	private WebElement recepientAddress;

	@FindBy(id = "com.google.android.gm:id/subject")
	private WebElement subject;

	@FindBy(id = "com.google.android.gm:id/add_attachment")
	private WebElement attachIcon;

	@FindBy(id = "com.google.android.gm:id/title")
	private WebElement attachFile;

	@FindBy(id = "com.google.android.gm:id/send")
	private WebElement send;

	@FindBy(id = "com.google.android.gm:id/peoplekit_listview_flattened_row")
	private WebElement tapRecepient;

	@FindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView")
	private WebElement outBoxMsg;

	public void clickOnGotItButton() {
		clickOnElement(gotItButton);
	}

	public void enterEmailAddress(String emailAddress) {
		enterText(email, emailAddress);
	}

	public void clickOnEmailAccountForSetup() {
		clickOnElement(emailAccount);
	}

	public void clickOnComposeButton() {
		clickOnElement(composeButton);
	}

	public void enterEmailRecepient(String recepient) {
		enterText(recepientAddress, recepient);
	}

	public void enterSubject(String subjectLine) {
		enterText(subject, subjectLine);
	}

	public void clickOnAttachIcon() {
		clickOnElement(attachIcon);
	}

	public void clickOnAttachFileButton() {
		clickOnElement(attachFile);
	}

	public void attachImage(String imageName) {
		driver.findElement(
				By.xpath("(//android.widget.LinearLayout//android.widget.TextView[@text='" + imageName + "'])[1]"))
				.click();
	}

	public void clickOnSendButton() {
		clickOnElement(send);
	}

	public void clickOnRecepient() {
		clickOnElement(tapRecepient);
	}

	public boolean returnEmailMsg() {
		try {
			return outBoxMsg.isDisplayed();
		} catch (StaleElementReferenceException e) {
			hardWait();
			return outBoxMsg.isDisplayed();
		}
	}

}

package headspin.testApp.pageObjects.android;

import java.io.File;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import headspin.testApp.utils.Actions;
import io.appium.java_client.android.AndroidDriver;

public class GooglePhotos extends Actions {

	protected AndroidDriver driver;

	public GooglePhotos(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "com.google.android.apps.photos:id/touch_outside")
	private WebElement touchOutsideBtn;

	@FindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Photo taken')]")
	private WebElement photo;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	private WebElement moreOptions;

	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.google.android.apps.photos:id/label'])[2]")
	private WebElement fileName;

	public void clickTouchOutsideToDisableSignUp() {
		clickOnElement(touchOutsideBtn);
	}

	public void clickOnPhoto() {
		clickOnElement(photo);
	}

	public void clickOnMoreOptions() {
		clickOnElement(moreOptions);
	}

	public String getFileName() {
		File file = new File(getText(fileName));
		return file.getName();
	}

}

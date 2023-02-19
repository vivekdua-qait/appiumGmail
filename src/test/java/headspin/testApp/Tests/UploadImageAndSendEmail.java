package headspin.testApp.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import headspin.testApp.BaseTest;
import headspin.testApp.pageObjects.android.Gmail;
import headspin.testApp.pageObjects.android.GooglePhotos;

public class UploadImageAndSendEmail extends BaseTest {

	String fileUploaded;
	String fileName = "Download.png";

	@Test
	public void Step01_uploadImageAndverify() throws IOException {
		GooglePhotos photos = new GooglePhotos(driver);
		uploadPhotoAsPreRequisite(fileName);
		photos.clickTouchOutsideToDisableSignUp();
		photos.clickOnPhoto();
		rotateScreenToLandscape();
		photos.clickOnMoreOptions();
		fileUploaded = photos.getFileName();
		// Verify the image name is same as name of uploaded image
		Assert.assertEquals(fileUploaded,fileName, "Assertion Failed: Uploaded image name is incorrect");
		rotateScreenToPOTRAIT();
	}

	@Test
	public void Step02_sendImageOnEmail() {
		Gmail gmail = new Gmail(driver);
		gmail.setActivity("com.google.android.gm", "com.google.android.gm.GmailActivity");
		gmail.clickOnComposeButton();
		gmail.enterEmailRecepient("cs@headspin.io");
		gmail.clickOnRecepient();
		gmail.enterSubject("Appium Assignment");
		gmail.clickOnAttachIcon();
		gmail.clickOnAttachFileButton();
		gmail.attachImage(fileName);
		gmail.clickOnSendButton();
		// Verify email is sent successfully
		Assert.assertTrue(gmail.returnEmailMsg(), "Assertion Failed: Email is not sent successfully");
	}

}

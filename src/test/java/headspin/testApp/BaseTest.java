package headspin.testApp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	protected AndroidDriver driver;
	AppiumServiceBuilder serviceBuilder;
	private AppiumDriverLocalService localAppiumServer;

	@BeforeSuite
	public void configureAppiumServer() {
		// Code to start server automatically
		serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.usingPort(4723);
		localAppiumServer = AppiumDriverLocalService.buildService(serviceBuilder);
		localAppiumServer.start();
		System.out.println(String.format("Appium server started on url: '%s'", localAppiumServer.getUrl().toString()));
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("LatestVivekPhone");
		options.setCapability("appPackage", "com.google.android.apps.photos");
		options.setCapability("appActivity", ".home.HomeActivity");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rotateScreenToLandscape() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
	}

	public void rotateScreenToPOTRAIT() {
		driver.rotate(ScreenOrientation.PORTRAIT);
	}

	public String uploadPhotoAsPreRequisite(String fileName) {
		String ANDROID_PHOTO_PATH = "sdcard/Pictures";
		File classpathRoot = new File(System.getProperty("user.dir"));
		File assetDir = new File(classpathRoot, "/src/test/java/resources");
		File img;
		try {
			img = new File(assetDir.getCanonicalFile(), fileName);
			driver.pushFile(ANDROID_PHOTO_PATH + "/" + img.getName(), img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	@AfterSuite
	public void afterSuite() {
		if (null != localAppiumServer) {
			System.out.println(String.format("Stopping the local Appium server running on: '%s'",
					localAppiumServer.getUrl().toString()));
			localAppiumServer.stop();
			System.out.println("Is Appium server running? " + localAppiumServer.isRunning());
		}
	}

}

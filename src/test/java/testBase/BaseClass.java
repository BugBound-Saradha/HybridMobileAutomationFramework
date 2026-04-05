package testBase;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

// Log4j imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClass {

	public static AndroidDriver driver;

	// Logger instance
	public static Logger log = LogManager.getLogger(BaseClass.class);

	@BeforeClass
	public void ConfigureAppium() {

		log.info("========== Starting Appium Configuration ==========");

		try {
			UiAutomator2Options options = new UiAutomator2Options();

			log.debug("Setting device name");
			options.setDeviceName("emulator-5554");

			log.debug("Setting app path");
			options.setApp("C://Users//sunda//eclipse-workspace//AppiumV01//src//test//resources//General-Store.apk");

			log.debug("Setting ChromeDriver path");
			options.setChromedriverExecutable(
					"C:\\Users\\sunda\\Downloads\\QAAutomationBootcamp\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

			log.debug("Initializing Android Driver");
			driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

			log.debug("Setting implicit wait");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			log.info("Appium Driver initialized successfully");

		} catch (Exception e) {
			log.error("Error during Appium setup", e);
			throw new RuntimeException("Failed to initialize Appium Driver", e);
		}
	}

	@AfterClass
	public void tearDown() {

		log.info("========== Starting Driver Teardown ==========");

		try {
			if (driver != null) {
				log.debug("Quitting driver");
				driver.quit();
				log.info("Driver closed successfully");
			} else {
				log.warn("Driver was null, nothing to quit");
			}
		} catch (Exception e) {
			log.error("Error during driver teardown", e);
		}
	}

	// ================= SCREENSHOT =================
	public String captureScreen(String tname) {

		log.debug("Capturing screenshot for test: {}", tname);
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp
					+ ".png";

			File targetFile = new File(targetFilePath);
			sourceFile.renameTo(targetFile);

			log.info("Screenshot saved at: {}", targetFilePath);
			return targetFilePath;

		} catch (Exception e) {
			log.error("Failed to capture screenshot for test: {}", tname, e);
			return null;
		}
	}
}
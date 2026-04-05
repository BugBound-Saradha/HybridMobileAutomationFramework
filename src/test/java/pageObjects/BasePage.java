package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

	protected AndroidDriver driver;
	private static final Logger log = LogManager.getLogger(BasePage.class);

	// Constructor
	public BasePage(AndroidDriver driver) {
		this.driver = driver;

		log.info("Initializing BasePage with Appium driver");

		try {
			PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
			log.debug("PageFactory elements initialized successfully");
		} catch (Exception e) {
			log.error("Error initializing PageFactory: {}", e.getMessage(), e);
			throw e;
		}
	}

	// ===========================
	// Generic scroll to text
	// ===========================
	public void scrollToText(String text) {

		log.info("Scrolling to text: {}", text);

		try {
			log.debug("Executing Android UIAutomator scroll command");

			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true))" + ".scrollTextIntoView(\"" + text + "\")"));

			log.debug("Successfully scrolled to text: {}", text);

		} catch (NoSuchElementException e) {
			log.error("Element with text '{}' not found during scroll", text, e);
			throw e; // Important for RetryAnalyzer
		} catch (Exception e) {
			log.error("Unexpected error while scrolling to '{}': {}", text, e.getMessage(), e);
			throw e;
		}
	}

	// ===========================
	// Scroll and click
	// ===========================
	public void scrollToTextAndClick(String text) {

		log.info("Scrolling to and clicking text: {}", text);

		try {
			log.debug("Scrolling to element");

			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true))" + ".scrollTextIntoView(\"" + text + "\")"));

			log.debug("Clicking element with text: {}", text);

			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")")).click();

			log.debug("Successfully clicked on text: {}", text);

		} catch (NoSuchElementException e) {
			log.error("Element '{}' not found for click action", text, e);
			throw e;
		} catch (AssertionError ae) {
			log.error("Assertion failed while clicking '{}': {}", text, ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Unexpected error in scrollToTextAndClick('{}'): {}", text, e.getMessage(), e);
			throw e;
		}
	}

	// ===========================
	// Press Android Back
	// ===========================
	public void pressBack() {

		log.info("Pressing Android BACK button");

		try {
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			log.debug("BACK button pressed successfully");

		} catch (Exception e) {
			log.error("Error while pressing BACK button: {}", e.getMessage(), e);
			throw e;
		}
	}

	// ===========================
	// Switch Context
	// ===========================
	public void switchContext(String context) {

		log.info("Switching context to: {}", context);

		try {
			log.debug("Available contexts: {}", driver.getContextHandles());

			driver.context(context);

			log.debug("Successfully switched to context: {}", context);

		} catch (Exception e) {
			log.error("Failed to switch context to '{}': {}", context, e.getMessage(), e);
			throw e;
		}
	}
}
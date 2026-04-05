package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class FinalPurchasePage extends BasePage {

	private static final Logger log = LogManager.getLogger(FinalPurchasePage.class);

	public FinalPurchasePage(AndroidDriver driver) {
		super(driver);
		log.info("FinalPurchasePage initialized");
	}

	// ===========================
	// Locators
	// ===========================
	@FindBy(name = "q")
	WebElement alerttext;

	// ===========================
	// Actions
	// ===========================
	public void setText() {

		log.info("Entering text into search field");

		try {
			String inputText = "CloudBerry QA Bootcamp";

			log.debug("Sending text: {}", inputText);

			alerttext.sendKeys(inputText);

			log.debug("Submitting search using ENTER key");

			alerttext.sendKeys(Keys.ENTER);

			log.debug("Text entered and submitted successfully");

		} catch (AssertionError ae) {
			log.error("Assertion failed while entering text: {}", ae.getMessage(), ae);
			throw ae; // Needed for RetryAnalyzer
		} catch (Exception e) {
			log.error("Error while setting text in FinalPurchasePage: {}", e.getMessage(), e);
			throw e;
		}
	}
}
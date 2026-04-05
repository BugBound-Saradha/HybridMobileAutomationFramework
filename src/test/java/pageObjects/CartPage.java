package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage {

	private static final Logger log = LogManager.getLogger(CartPage.class);

	public CartPage(AndroidDriver driver) {
		super(driver);
		log.info("CartPage initialized");
	}

	// ===========================
	// Locators
	// ===========================

	@AndroidFindBy(className = "android.widget.CheckBox")
	WebElement checkBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	WebElement alerttext;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	WebElement termsButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	WebElement addedProductName;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	WebElement btnToWeb;

	// ===========================
	// Actions
	// ===========================

	public void longClickTermsButton() {

		log.info("Performing long click on Terms button");

		try {
			log.debug("Executing longClickGesture using JavascriptExecutor");

			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) termsButton).getId()));

			log.debug("Long click action performed successfully");

		} catch (Exception e) {
			log.error("Failed to perform long click on Terms button: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void clickTCCheckBox() {

		log.info("Clicking Terms & Conditions checkbox");

		try {
			checkBox.click();
			log.debug("Checkbox clicked successfully");

		} catch (Exception e) {
			log.error("Failed to click checkbox: {}", e.getMessage(), e);
			throw e;
		}
	}

	public String getAlertTitle() {

		log.info("Fetching alert title text");

		try {
			String text = alerttext.getText();
			log.debug("Alert title retrieved: {}", text);
			return text;

		} catch (Exception e) {
			log.error("Failed to get alert title: {}", e.getMessage(), e);
			throw e;
		}
	}

	public String getAddedproductName() throws InterruptedException {

		log.info("Getting added product name");

		try {
			log.debug("Waiting for product name to load");
			Thread.sleep(1000);

			String productName = addedProductName.getText();

			log.debug("Product name retrieved: {}", productName);
			return productName;

		} catch (AssertionError ae) {
			log.error("Assertion failed while fetching product name: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while getting product name: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void clickVisitToWeb() throws InterruptedException {

		log.info("Clicking 'Visit to Web' button");

		try {
			btnToWeb.click();
			log.debug("Clicked 'Visit to Web' button");

			Thread.sleep(1000);
			log.debug("Waited after clicking web button");

		} catch (AssertionError ae) {
			log.error("Assertion failed while clicking Visit to Web: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while clicking Visit to Web button: {}", e.getMessage(), e);
			throw e;
		}
	}
}
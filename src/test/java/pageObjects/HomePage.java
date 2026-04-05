package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {

	private static final Logger log = LogManager.getLogger(HomePage.class);

	// Constructor
	public HomePage(AndroidDriver driver) {
		super(driver);
		log.info("HomePage initialized");
	}

	// ===========================
	// Locators
	// ===========================

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	WebElement letsShopBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	WebElement countryDropdown;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	WebElement getToastMsg;

	// ===========================
	// Actions
	// ===========================

	public String verifyHomePage() {

		log.info("Verifying Home Page by fetching name field text");

		try {
			String text = nameField.getText();
			log.debug("Name field text: {}", text);
			return text;

		} catch (AssertionError ae) {
			log.error("Assertion failed in verifyHomePage: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while verifying Home Page: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void enterName() {

		log.info("Entering name in name field");

		try {
			String name = "Sid";
			log.debug("Sending name: {}", name);

			nameField.sendKeys(name);

			log.debug("Name entered successfully");

		} catch (AssertionError ae) {
			log.error("Assertion failed while entering name: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while entering name: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void clickLetsShop() {

		log.info("Clicking 'Let's Shop' button");

		try {
			log.debug("Waiting for 'Let's Shop' button to be clickable");

			new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.elementToBeClickable(letsShopBtn));

			letsShopBtn.click();

			log.debug("'Let's Shop' button clicked successfully");

		} catch (AssertionError ae) {
			log.error("Assertion failed while clicking Let's Shop: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while clicking Let's Shop button: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void clickCountryDropdown() {

		log.info("Clicking country dropdown");

		try {
			countryDropdown.click();
			log.debug("Country dropdown clicked successfully");

		} catch (Exception e) {
			log.error("Error while clicking country dropdown: {}", e.getMessage(), e);
			throw e;
		}
	}

	public String getToastMsg() {

		log.info("Fetching toast message");

		try {
			String toastMsg = getToastMsg.getAttribute("name");

			log.debug("Toast message retrieved: {}", toastMsg);

			return toastMsg;

		} catch (AssertionError ae) {
			log.error("Assertion failed while fetching toast message: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while getting toast message: {}", e.getMessage(), e);
			throw e;
		}
	}
}
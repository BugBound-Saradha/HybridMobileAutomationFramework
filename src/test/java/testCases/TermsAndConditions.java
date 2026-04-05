package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TermsAndConditions extends BaseClass {

	private static final Logger log = LogManager.getLogger(TermsAndConditions.class);

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void TermsAndConditionsTest() throws InterruptedException {

		log.info("===== Starting TermsAndConditionsTest =====");

		try {
			// Home Page
			log.info("Initializing HomePage");
			HomePage hp = new HomePage(driver);

			log.debug("Entering user name");
			hp.enterName();

			log.debug("Clicking Let's Shop button");
			hp.clickLetsShop();

			// Product Page
			log.info("Navigating to ProductPage");
			ProductPage pp = new ProductPage(driver);

			log.debug("Scrolling to 'Jordan 6 Rings'");
			pp.scrollToText("Jordan 6 Rings");

			log.debug("Adding product to cart");
			pp.clickAddToCart();

			log.debug("Opening cart");
			pp.clickViewCart();

			// Cart Page
			log.info("Navigating to CartPage");
			CartPage cp = new CartPage(driver);

			log.debug("Clicking Terms & Conditions checkbox");
			cp.clickTCCheckBox();

			log.debug("Long clicking Terms & Conditions button to view alert");
			cp.longClickTermsButton();

			log.debug("Fetching alert title");
			String title = cp.getAlertTitle();
			log.info("Alert title retrieved: {}", title);

			// Assertion with try-catch
			try {
				log.debug("Validating alert title");
				Assert.assertEquals(title, "Terms Of Conditions");
				log.info("Assertion PASSED: Alert title is correct");

			} catch (AssertionError ae) {
				log.error("Assertion FAILED: Expected 'Terms Of Conditions' but found '{}'", title);
				throw ae; // Required for RetryAnalyzer
			}

		} catch (Exception e) {
			log.error("Test execution failed due to exception: {}", e.getMessage(), e);
			throw e; // Required for RetryAnalyzer
		}

		log.info("===== Ending TermsAndConditionsTest =====");
	}
}
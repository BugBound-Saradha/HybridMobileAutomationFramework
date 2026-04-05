package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class ValidateToastMessage extends BaseClass {

	private static final Logger log = LogManager.getLogger(ValidateToastMessage.class);

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void ValidateToastMessageTest() {

		log.info("===== Starting ValidateToastMessageTest =====");

		try {
			// Home Page
			log.info("Initializing HomePage");
			HomePage hp = new HomePage(driver);

			log.debug("Clicking Let's Shop button without entering name");
			hp.clickLetsShop();

			log.debug("Fetching Toast message");
			String msg = hp.getToastMsg();
			log.info("Toast message retrieved: {}", msg);

			// Assertion with try-catch
			try {
				log.debug("Validating Toast message");
				Assert.assertEquals(msg, "Please enter your name");
				log.info("Assertion PASSED: Toast message is correct");

			} catch (AssertionError ae) {
				log.error("Assertion FAILED: Expected 'Please enter your name' but found '{}'", msg);
				throw ae; // Required for RetryAnalyzer
			}

		} catch (Exception e) {
			log.error("Test execution failed due to exception: {}", e.getMessage(), e);
			throw e; // Required for RetryAnalyzer
		}

		log.info("===== Ending ValidateToastMessageTest =====");
	}
}
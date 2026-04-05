package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class LaunchApp extends BaseClass {

	private static final Logger log = LogManager.getLogger(LaunchApp.class);

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void LaunchAppTest() {

		log.info("===== Starting LaunchAppTest =====");

		try {
			log.info("Initializing HomePage");
			HomePage hp = new HomePage(driver);

			log.debug("Verifying Home Page text");
			String homePage = hp.verifyHomePage();
			log.info("Home Page text retrieved: {}", homePage);

			// Assertion with try-catch
			try {
				log.debug("Validating Home Page text");
				Assert.assertEquals(homePage, "Enter name here");
				log.info("Assertion PASSED: Home Page text is correct");

			} catch (AssertionError ae) {
				log.error("Assertion FAILED: Expected 'Enter name here' but found '{}'", homePage);
				throw ae; // Important for RetryAnalyzer
			}

		} catch (Exception e) {
			log.error("Test execution failed due to exception: {}", e.getMessage(), e);
			throw e; // Important for RetryAnalyzer
		}

		log.info("===== Ending LaunchAppTest =====");
	}
}
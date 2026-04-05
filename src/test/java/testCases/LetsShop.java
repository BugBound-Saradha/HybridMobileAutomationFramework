package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class LetsShop extends BaseClass {

	private static final Logger log = LogManager.getLogger(LetsShop.class);

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void LetsShopTest() {

		log.info("===== Starting LetsShopTest =====");

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

			log.debug("Fetching Product Page title");
			String products = pp.getProductPageTitle();
			log.info("Product Page title retrieved: {}", products);

			// Assertion with try-catch
			try {
				log.debug("Validating Product Page title");
				Assert.assertEquals(products, "Products");
				log.info("Assertion PASSED: Product Page title is correct");

			} catch (AssertionError ae) {
				log.error("Assertion FAILED: Expected 'Products' but found '{}'", products);
				throw ae; // Required for RetryAnalyzer
			}

		} catch (Exception e) {
			log.error("Test execution failed due to exception: {}", e.getMessage(), e);
			throw e; // Required for RetryAnalyzer
		}

		log.info("===== Ending LetsShopTest =====");
	}
}
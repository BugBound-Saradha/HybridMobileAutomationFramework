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

public class AddToCart extends BaseClass {

	private static final Logger log = LogManager.getLogger(AddToCart.class);

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void AddToCartTest() throws InterruptedException {

		log.info("===== Starting AddToCartTest =====");

		try {
			// Home Page actions
			log.info("Initializing HomePage");
			HomePage hp = new HomePage(driver);

			log.debug("Entering user name");
			hp.enterName();

			log.debug("Clicking Let's Shop button");
			hp.clickLetsShop();

			// Product Page actions
			log.info("Navigating to ProductPage");
			ProductPage pp = new ProductPage(driver);

			log.debug("Scrolling to product: Jordan 6 Rings");
			pp.scrollToText("Jordan 6 Rings");

			log.debug("Adding product to cart");
			pp.clickAddToCart();

			log.debug("Clicking View Cart");
			pp.clickViewCart();

			// Cart Page actions
			log.info("Navigating to CartPage");
			CartPage cp = new CartPage(driver);

			log.debug("Fetching added product name from cart");
			String lastPageProduct = cp.getAddedproductName();

			log.info("Product in cart: {}", lastPageProduct);

			// Assertion with try-catch
			try {
				log.debug("Validating product name in cart");

				Assert.assertEquals(lastPageProduct, "Jordan 6 Rings");

				log.info("Assertion PASSED: Correct product added to cart");

			} catch (AssertionError ae) {

				log.error("Assertion FAILED: Expected 'Jordan 6 Rings' but found '{}'", lastPageProduct);

				throw ae; // Important for RetryAnalyzer
			}

		} catch (Exception e) {
			log.error("Test execution failed due to exception: {}", e.getMessage(), e);
			throw e; // Important for RetryAnalyzer
		}

		log.info("===== Ending AddToCartTest =====");
	}
}
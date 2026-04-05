package testCases;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.FinalPurchasePage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class CompletePurchase extends BaseClass {

	private static final Logger log = LogManager.getLogger(CompletePurchase.class);

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void CompletePurchaseTest() throws InterruptedException {

		log.info("===== Starting CompletePurchaseTest =====");

		try {
			// Home Page
			log.info("Initializing HomePage");
			HomePage hp = new HomePage(driver);

			log.debug("Entering user name");
			hp.enterName();

			log.debug("Clicking Let's Shop");
			hp.clickLetsShop();

			// Product Page
			log.info("Navigating to ProductPage");
			ProductPage pp = new ProductPage(driver);

			log.debug("Scrolling to product: Jordan 6 Rings");
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

			log.debug("Clicking Visit to Web button");
			cp.clickVisitToWeb();

			log.info("Waiting for WebView to load (30 seconds)");
			Thread.sleep(30000);

			// Final Purchase Page
			log.info("Initializing FinalPurchasePage");
			FinalPurchasePage fpg = new FinalPurchasePage(driver);

			// Context Handling
			log.info("Fetching available contexts");

			Set<String> contexts = driver.getContextHandles();

			for (String contextName : contexts) {
				log.debug("Available context: {}", contextName);
			}

			// Switch to WebView
			try {
				log.info("Switching to WEBVIEW");

				fpg.switchContext("WEBVIEW_com.androidsample.generalstore");

				log.debug("Successfully switched to WEBVIEW");

			} catch (Exception e) {
				log.error("Failed to switch to WEBVIEW: {}", e.getMessage(), e);
				throw e;
			}

			// Perform search
			log.debug("Performing search in WebView");
			fpg.setText();

			// Navigate back
			log.debug("Pressing back button to return to native app");
			fpg.pressBack();

			// Switch back to Native
			try {
				log.info("Switching back to NATIVE_APP");

				fpg.switchContext("NATIVE_APP");

				log.debug("Successfully switched back to NATIVE_APP");

			} catch (Exception e) {
				log.error("Failed to switch back to NATIVE_APP: {}", e.getMessage(), e);
				throw e;
			}

		} catch (AssertionError ae) {
			log.error("Assertion failed in CompletePurchaseTest: {}", ae.getMessage(), ae);
			throw ae; // Required for RetryAnalyzer

		} catch (Exception e) {
			log.error("Test execution failed due to exception: {}", e.getMessage(), e);
			throw e; // Required for RetryAnalyzer
		}

		log.info("===== Ending CompletePurchaseTest =====");
	}
}
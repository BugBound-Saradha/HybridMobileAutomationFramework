package pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductPage extends BasePage {

	private static final Logger log = LogManager.getLogger(ProductPage.class);

	public ProductPage(AndroidDriver driver) {
		super(driver);
		log.info("ProductPage initialized");
	}

	// ===========================
	// Locators
	// ===========================

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/toolbar_title\")")
	WebElement productPageTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	List<WebElement> items;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	List<WebElement> addToCartButtons;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	WebElement viewCart;

	// ===========================
	// Actions
	// ===========================

	public String getProductPageTitle() {

		log.info("Fetching Product Page title");

		try {
			String title = productPageTitle.getText();
			log.debug("Product page title: {}", title);
			return title;

		} catch (AssertionError ae) {
			log.error("Assertion failed while getting product page title: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while fetching product page title: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void clickAddToCart() {

		log.info("Attempting to add product 'Jordan 6 Rings' to cart");

		try {
			boolean productFound = false;

			for (int i = 0; i < items.size(); i++) {

				String productName = items.get(i).getText();
				log.debug("Checking product: {}", productName);

				if (productName.equalsIgnoreCase("Jordan 6 Rings")) {

					log.debug("Match found. Clicking Add to Cart for: {}", productName);

					addToCartButtons.get(i).click();

					productFound = true;

					log.info("Product '{}' added to cart successfully", productName);

					break;
				}
			}

			if (!productFound) {
				throw new RuntimeException("Product 'Jordan 6 Rings' not found");
			}

		} catch (AssertionError ae) {
			log.error("Assertion failed while adding product to cart: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error in clickAddToCart(): {}", e.getMessage(), e);
			throw e;
		}
	}

	public void clickViewCart() {

		log.info("Clicking on View Cart button");

		try {
			viewCart.click();
			log.debug("View Cart button clicked successfully");

		} catch (AssertionError ae) {
			log.error("Assertion failed while clicking View Cart: {}", ae.getMessage(), ae);
			throw ae;
		} catch (Exception e) {
			log.error("Error while clicking View Cart button: {}", e.getMessage(), e);
			throw e;
		}
	}
}
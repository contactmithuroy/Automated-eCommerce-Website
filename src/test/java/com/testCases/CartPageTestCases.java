package com.testCases;
import com.pageObject.CartPage;
import com.pageObject.LoginPage;
import com.testBase.BaseClass;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class CartPageTestCases extends BaseClass {
	CartPage cartPage;
	LoginPage loginPage;

	@BeforeClass
	public void setupPageObject() {
		cartPage = new CartPage(driver);
		loginPage = new LoginPage(driver);
	}

	@Parameters("url")
	@Test(groups = {"functional", "regression"}, description = "Login to the application")
	public void testLogin(String url) throws IOException {
		// Fetch email and password from the second row
		String[] credentials = getSecondRowCredentials();
		String email = credentials[0];
		String password = credentials[1];
		driver.get(url+"login");
		boolean loginSuccess = loginPage.loginForPreRequisites(email, password); // Login with data Provider first value email and password
		Assert.assertTrue(loginSuccess, "Login failed!");
		System.out.println("1. Login successful.");
	}

	@Test(groups = {"functional", "regression"}, dependsOnMethods = {"testLogin"},priority = 1, description = "Add new products using 'Add to Cart' button")
	public void testAddProductsToCart() throws InterruptedException {
		cartPage.addProductsToCart();
		cartPage.navigateToCart();
		int cartItemCount = cartPage.getCartItemsCount();
		if(cartItemCount !=0) {
			System.out.println("2. Products added to cart successfully.");
		}else {
			Assert.assertTrue(false, "Cart item count mismatch!");
		}
	}

	@Test(groups = {"functional", "regression"}, dependsOnMethods = {"testLogin"}, priority = 2, description = "Verify the 'Update Quantity' feature")
	public void testUpdateQuantity() {
		cartPage.updateProductQuantity("3");
		int cartItemCount = cartPage.getCartItemsCount();
		//Assert.assertTrue(cartItemCount > 0, "Cart items not found after quantity update.");
		System.out.println("3. Quantity updated successfully.");
	}

	@Test(groups = {"functional", "regression"}, dependsOnMethods = {"testLogin"}, priority = 3, description = "Test 'Proceed to Checkout' button functionality")
	public void testProceedToCheckout() {
		cartPage.proceedToCheckout();
		System.out.println("4. Proceeded to checkout successfully.");
	}

	@Test(groups = {"functional", "regression"}, dependsOnMethods = {"testLogin","testProceedToCheckout"},priority = 4, description = "Validate total price calculation and place order")
	public void testPlaceOrder() {
		cartPage.addComment("Please deliver between 9 AM and 5 PM.");
		cartPage.placeOrder();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("payment_done"), "Place Order failed.");
		System.out.println("5. Order placed successfully.");
	}

	@Test(groups = {"functional", "regression"}, dependsOnMethods = {"testLogin","testProceedToCheckout"}, priority = 5, description = "Download the invoice of payment")
	public void testDownloadInvoice() {
		cartPage.downloadInvoice();
		System.out.println("6. Invoice downloaded successfully.");
	}
}

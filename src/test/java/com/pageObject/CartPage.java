package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
	WebDriver driver;

	// Predefined card details
	private String cardName = "John Doe";
	private String cardNumber = "4111111111111111";
	private String cvc = "123";
	private String expiryMonth = "12";
	private String expiryYear = "2025";

	// Constructor
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath ="(//div[@class='product-overlay'])[1]")
	private WebElement product; //hover on product box

	@FindBy(xpath ="//button[normalize-space()='Continue Shopping']")
	private WebElement continueShopping;

	@FindBy(xpath = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[1]//div[2]//div[1]//a[1]")
	private WebElement addToCartButtons;

	@FindBy(xpath = "//a[normalize-space()='Cart']")
	private WebElement cartLink;

	@FindBy(xpath = "//tbody/tr")
	private List<WebElement> cartItems;

	@FindBy(xpath = "//button[contains(@class, 'update-quantity')]")
	private WebElement updateQuantityButton;

	@FindBy(xpath = "//input[@name='quantity']")
	private WebElement quantityInput;

	@FindBy(xpath = "//a[contains(text(),'Proceed To Checkout')]")
	private WebElement proceedToCheckoutButton;

	@FindBy(xpath = "//textarea[@name='message']")
	private WebElement commentBox;

	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	private WebElement placeOrderButton;

	@FindBy(xpath = "//input[@name='name_on_card']")
	private WebElement nameOnCardInput;

	@FindBy(xpath = "//input[@name='card_number']")
	private WebElement cardNumberInput;

	@FindBy(xpath = "//input[@name='cvc']")
	private WebElement cvcInput;

	@FindBy(xpath = "//input[@name='expiry_month']")
	private WebElement expiryMonthInput;

	@FindBy(xpath = "//input[@name='expiry_year']")
	private WebElement expiryYearInput;

	@FindBy(xpath = "(//button[normalize-space()='Pay and Confirm Order'])")
	private WebElement confirmOrderButton;

	@FindBy(xpath = "//a[normalize-space()='Download Invoice']")
	private WebElement downloadInvoiceButton;

	// Actions
	public void addProductsToCart() throws InterruptedException {

		Actions action = new Actions(driver);
		action.moveToElement(product).perform(); // Action to hover over the product
		addToCartButtons.click();
		Thread.sleep(2000);
		continueShopping.click();

	}

	public void navigateToCart() {
		cartLink.click();
	}

	public int getCartItemsCount() {
		return cartItems.size();
	}

	public void updateProductQuantity(String quantity) {
		quantityInput.clear();
		quantityInput.sendKeys(quantity);
		updateQuantityButton.click();
	}

	public void proceedToCheckout() {
		proceedToCheckoutButton.click();
	}

	public void addComment(String comment) {
		commentBox.sendKeys(comment);
	}

	public void placeOrder() {
		placeOrderButton.click();
		nameOnCardInput.sendKeys(cardName);
		cardNumberInput.sendKeys(cardNumber);
		cvcInput.sendKeys(cvc);
		expiryMonthInput.sendKeys(expiryMonth);
		expiryYearInput.sendKeys(expiryYear);
		confirmOrderButton.click();
	}

	public void downloadInvoice() {
		downloadInvoiceButton.click();
	}
}

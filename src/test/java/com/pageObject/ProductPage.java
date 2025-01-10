package com.pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	// Locators
    @FindBy(xpath ="//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[1]//div[2]//div[1]//a[1]")
	private WebElement addToCart;
    
    @FindBy(xpath ="(//div[@class='product-overlay'])[1]")
   	private WebElement product; //hover on product box
    
    @FindBy(xpath ="//button[normalize-space()='Continue Shopping']")
	private WebElement continueShopping;
    
	//Action
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
    public void clickAddToProduct(WebDriver driver) throws InterruptedException {
    	Actions action = new Actions(driver);
        action.moveToElement(product).perform(); // Action to hover over the product
        
    	addToCart.click();
    	Thread.sleep(2000);
    	continueShopping.click();
    	
    }
}

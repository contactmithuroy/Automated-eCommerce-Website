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
    
    @FindBy(xpath = "//input[@id='search_product']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@id='submit_search']")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[@class='title text-center']")
    private WebElement searchResultsTitle;
    
    @FindBy(xpath = "//a[normalize-space()='Women']")
    private WebElement womenCategory;
    
    @FindBy(xpath="(//a[contains(text(),'Dress')])")
    private WebElement womenCategoryChildList;

    @FindBy(xpath = "//a[normalize-space()='Men']")
    private WebElement menCategory;
    
    @FindBy(xpath="(//a[contains(text(),'Jeans')])")
    private WebElement menCategoryChildList;

    @FindBy(xpath = "//a[normalize-space()='Kids']")
    private WebElement kidsCategory;
    
    @FindBy(xpath="(//a[contains(text(),'Tops & Shirts')])")
    private WebElement kidsCategoryChildList;
    
    @FindBy(xpath="//a[@href='/brand_products/Polo']")
    private WebElement poloBrand;
    
    @FindBy(xpath="//a[@href='/brand_products/H&M']")
    private WebElement handmBrand;
    
    @FindBy(xpath="//a[@href='/brand_products/Biba']")
    private WebElement bibaBrand;
    
    @FindBy(xpath="(//a[contains(text(),'View Product')])[1]")
    private WebElement viewProduct;
    
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
    
	public void searchProduct(String searchVal) {
		 searchInput.sendKeys(searchVal);
	        searchButton.click();
	}
	
	public void selectCategory(String category) {
        if (category.equalsIgnoreCase("Women")) {
            womenCategory.click();
            womenCategoryChildList.click();
            } else if (category.equalsIgnoreCase("Men")) {
            menCategory.click();
            menCategoryChildList.click();
        } else if (category.equalsIgnoreCase("Kids")) {
            kidsCategory.click();
            kidsCategoryChildList.click();
        }
    }
	
	public void brandOption(String brand) {
		if(brand.equalsIgnoreCase("Polo")) {
			poloBrand.click();
		}else if(brand.equalsIgnoreCase("H&M")) {
			handmBrand.click();
			
		}else if(brand.equalsIgnoreCase("Biba")) {
			bibaBrand.click();
		}
	}
	
	public void viewProduct() {
		viewProduct.click();
	}
	
}

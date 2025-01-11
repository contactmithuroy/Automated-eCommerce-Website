package com.testCases;

import com.pageObject.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.testBase.BaseClass;

public class ProductPageTestCases extends BaseClass {
    ProductPage productPage;

    @BeforeClass
    public void setupPageObject() {
        productPage = new ProductPage(driver);
    }

    @Parameters("url")
    @Test(priority = 1, description = "Verify that the product page loads successfully")
    public void testHomePageLoadSuccessfully(String url) {
        driver.get(url);
        String title = productPage.getPageTitle(driver);
        Assert.assertEquals(title, "Automation Exercise - All Products", "Product page title mismatch.");
        System.out.println("Product page loaded successfully with title: " + title);
    }

    @Test(priority = 2, description = "Validate that navigation links redirect to the correct pages")
    public void testAddToCartButton() throws InterruptedException {
        productPage.clickAddToProduct(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products", "Products link navigation failed.");
        System.out.println("Verified Add to Cart functionality.");
    }
    
    @Test(priority = 3, description = "Test the search functionality on the product page")
    public void testSearchFunctionality() {
    	String searchVal = "Shirt";
        productPage.searchProduct(searchVal); // Example product
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://automationexercise.com/products?search="+searchVal;
        Assert.assertEquals(currentUrl,expectedUrl, "Search results did not match.");
        System.out.println("Search functionality works as expected.");
    }
    
    @Test(priority = 4, description = "Validate that category option (WOMEN, MEN, KIDS) works correctly")
    public void testCategorySelection() {
        productPage.selectCategory("Women");
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"), "Women category navigation failed.");

        driver.navigate().back();

        productPage.selectCategory("Men");
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"), "Men category navigation failed.");

        driver.navigate().back();

        productPage.selectCategory("Kids");
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"), "Kids category navigation failed.");
        
        driver.navigate().back();
        System.out.println("Category Selection functionality works as expected.");
    }
    
    @Test(priority = 5, description = "Validate that brand option (Polo, H&M, Biba) works correctly")
    public void testBranSelection() {
        productPage.brandOption("Polo");
        Assert.assertTrue(driver.getTitle().contains("Polo"), "Polo brand navigation failed.");

        productPage.brandOption("H&M");
        Assert.assertTrue(driver.getTitle().contains("H&M"), "H&M brand navigation failed.");
        
        productPage.brandOption("Biba");
        Assert.assertTrue(driver.getTitle().contains("Biba"), "Biba brand navigation failed.");
        
        System.out.println("Brand Selection functionality works as expected.");
    }
    
    @Test(priority = 6, description = "Validate that product details page works correctly")
    public void productDetailsButton() {
        productPage.viewProduct();
        Assert.assertTrue(driver.getTitle().contains("Product Details"), "Product details navigation failed.");
        driver.navigate().back();
        System.out.println("Product Details page Selection functionality works as expected.");
    }
    
}

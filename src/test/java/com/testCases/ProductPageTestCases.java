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
}

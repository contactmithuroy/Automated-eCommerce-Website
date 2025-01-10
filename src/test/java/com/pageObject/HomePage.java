package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//a[contains(text(),'Products')]")
    private WebElement productsLink;
    
    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    private WebElement cartLink;
    
    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(text(),'Contact us')]")
    private WebElement contactUsLink;

    @FindBy(tagName = "title")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='carousel-inner']//div[contains(@class, 'active')]")
    private WebElement activeCarouselItem;

    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement logo;

    @FindBy(xpath = "//footer//a")
    private List<WebElement> footerLinks;

    // Actions
    public boolean isLogoVisible(WebDriver driver) {
        return logo.isDisplayed();
    }
    
    public void clickProducts(WebDriver driver) {
        productsLink.click();
    }
    
    public void clickCart(WebDriver driver) {
        cartLink.click();
    }

    public void clickSignupLogin(WebDriver driver) {
        signupLoginLink.click();
    }

    public void clickContactUs(WebDriver driver) {
        contactUsLink.click();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public boolean isCarouselContentChanging(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String initialContent = activeCarouselItem.getText();
        wait.until(ExpectedConditions.stalenessOf(activeCarouselItem));
        String newContent = activeCarouselItem.getText();
        return !initialContent.equals(newContent);
    }

    public boolean areFooterLinksVisible(WebDriver driver) {
        for (WebElement link : footerLinks) {
            if (!link.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
}

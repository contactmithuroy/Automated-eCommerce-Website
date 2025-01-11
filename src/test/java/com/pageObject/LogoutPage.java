package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locator for Logout button
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    // Action for Logout
    public boolean logoutSuccessfully() {
        if (logoutButton.isDisplayed()) {
            logoutButton.click();
            return true;
        }
        return false;
    }
}

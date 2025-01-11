package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    // Predefined input values
    private String email = "admin@mr.com";
    private String password = "1234";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//input[@name='email']")
    private WebElement loginEmail;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement loginPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    // Actions
    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean loginWithValidCredentials() {
        loginEmail.clear();
        loginEmail.sendKeys(email);
        loginPassword.clear();
        loginPassword.sendKeys(password);
        loginButton.click();

        // Verifies if logout button is displayed after login
        return logoutButton.isDisplayed();
    }
}

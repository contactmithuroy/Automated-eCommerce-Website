package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

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

    public boolean loginWithCredentials(String email, String password) {
    	loginEmail.clear();
        loginEmail.sendKeys(email);
        loginPassword.clear();
        loginPassword.sendKeys(password);
        loginButton.click();
        boolean result = logoutButton.isDisplayed();
        logoutButton.click();
        // Verifies if logout button is displayed after login
        return result;
    }

    public boolean login(String email, String password) {
    	loginEmail.clear();
        loginEmail.sendKeys(email);
        loginPassword.clear();
        loginPassword.sendKeys(password);
        loginButton.click();
        boolean result = logoutButton.isDisplayed();
        return result;
    }
    
    public boolean logoutIfLoggedIn() {
        try {
            if (logoutButton.isDisplayed()) {
                logoutButton.click();
                return true; // Logout successful
            }
        } catch (Exception e) {
            // Ignore as the logout button might not be displayed
        }
        return false; // Not logged in
    }

    public boolean loginForPreRequisites(String email, String password) {
        logoutIfLoggedIn(); // Ensure no active session exists
        return login(email, password);
    }
}

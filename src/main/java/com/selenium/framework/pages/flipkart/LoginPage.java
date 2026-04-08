package com.selenium.framework.pages.flipkart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginPage - Flipkart Login/Register Page Object
 * Handles login and registration interactions
 */
public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By emailField = By.xpath("//input[@class='_2IX_2- VfPpkd-fmcmbc']");
    private By phoneField = By.xpath("//input[@placeholder='Enter Email or Mobile number']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private By registerButton = By.xpath("//span[contains(text(), 'New to Flipkart')]");
    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By registerEmailField = By.xpath("//input[@placeholder='Email']");
    private By registerPasswordField = By.xpath("//input[@placeholder='Password']");
    private By createAccountButton = By.xpath("//button[contains(text(), 'CREATE ACCOUNT')]");
    private By errorMessage = By.xpath("//span[@class='_27z7dR']");
    private By loginForm = By.xpath("//div[contains(@class, 'login-form')]");
    private By forgotPasswordLink = By.xpath("//a[contains(text(), 'Forgot')]");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Wait for login form to appear
     */
    public void waitForLoginForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
    }
    
    /**
     * Login with email and password
     */
    public HomePage loginWithEmail(String email, String password) {
        try {
            waitForLoginForm();
            
            // Clear and enter email
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailInput.clear();
            emailInput.sendKeys(email);
            
            Thread.sleep(500);
            
            // Click login or continue
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginBtn.click();
            
            Thread.sleep(1000);
            
            // Enter password
            WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passInput.clear();
            passInput.sendKeys(password);
            
            // Click login
            loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginBtn.click();
            
            // Wait for homepage to load
            wait.until(ExpectedConditions.urlContains("flipkart.com"));
            return new HomePage(driver);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Login with phone number
     */
    public HomePage loginWithPhone(String phoneNumber, String password) {
        try {
            waitForLoginForm();
            
            WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
            phoneInput.clear();
            phoneInput.sendKeys(phoneNumber);
            
            Thread.sleep(500);
            
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginBtn.click();
            
            Thread.sleep(1000);
            
            WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passInput.clear();
            passInput.sendKeys(password);
            
            loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginBtn.click();
            
            wait.until(ExpectedConditions.urlContains("flipkart.com"));
            return new HomePage(driver);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Click on Forgot Password link
     */
    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }
    
    /**
     * Get error message if login fails
     */
    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    
    /**
     * Check if login form is displayed
     */
    public boolean isLoginFormDisplayed() {
        try {
            return driver.findElement(loginForm).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

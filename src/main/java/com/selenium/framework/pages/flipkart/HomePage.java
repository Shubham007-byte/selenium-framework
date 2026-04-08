package com.selenium.framework.pages.flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * HomePage - Flipkart Home Page Object
 * Handles search, navigation, and home page interactions
 */
public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By searchBox = By.xpath("//input[@placeholder='Search for products, brands and more']");
    private By searchButton = By.xpath("//button[@type='submit'][contains(.,'Search')]");
    private By loginButton = By.xpath("//a[contains(text(),'Login')]");
    private By cartIcon = By.xpath("//a[contains(@href, 'cart')]");
    private By categoryMenu = By.xpath("//span[contains(text(),'Electronics')]");
    private By accountDropdown = By.xpath("//div[contains(@class, 'profile')]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Search for a product by name
     */
    public SearchResultsPage searchProduct(String productName) {
        try {
            Thread.sleep(500); // Wait for page to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        WebElement search = driver.findElement(searchBox);
        search.clear();
        search.sendKeys(productName);
        
        // Click search button or press Enter
        search.submit();
        
        wait.until(ExpectedConditions.urlContains("search"));
        return new SearchResultsPage(driver);
    }
    
    /**
     * Navigate to login page
     */
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new LoginPage(driver);
    }
    
    /**
     * Click on cart icon to view cart
     */
    public CartPage clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        return new CartPage(driver);
    }
    
    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Verify home page is loaded
     */
    public boolean isHomePageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Close login popup if present
     */
    public void closeLoginPopup() {
        try {
            By closeButton = By.xpath("//button[contains(@class, 'close')]");
            WebElement close = driver.findElement(closeButton);
            if (close.isDisplayed()) {
                close.click();
            }
        } catch (Exception e) {
            System.out.println("No popup to close");
        }
    }
    
    /**
     * Accept cookies if banner present
     */
    public void acceptCookies() {
        try {
            By acceptBtn = By.xpath("//button[contains(text(), 'Accept')]");
            WebElement accept = driver.findElement(acceptBtn);
            if (accept.isDisplayed()) {
                accept.click();
            }
        } catch (Exception e) {
            System.out.println("No cookie banner");
        }
    }
}

package com.selenium.framework.pages.flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * CartPage - Flipkart Shopping Cart Page Object
 * Handles cart items, quantity, removal, and checkout
 */
public class CartPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By cartItems = By.xpath("//div[contains(@class, 'cartItem')]");
    private By itemName = By.xpath(".//a[@class='_1wcjJr']");
    private By itemPrice = By.xpath(".//div[@class='_1uv9d1']");
    private By itemQuantity = By.xpath(".//input[@class='yFfkVn']");
    private By removeButton = By.xpath(".//button[contains(text(), 'Remove')]");
    private By emptyCartMessage = By.xpath("//div[contains(text(), 'empty')]");
    private By proceedButton = By.xpath("//button[contains(text(), 'Proceed to Checkout')]");
    private By cartTotal = By.xpath("//div[contains(text(), 'Total')]");
    private By saveForLater = By.xpath("//button[contains(text(), 'Save for later')]");
    private By deliverySection = By.xpath("//div[contains(@class, 'delivery')]");
    private By priceDetails = By.xpath("//span[contains(text(), 'Price Details')]");
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Wait for cart page to load
     */
    public void waitForCartToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        } catch (Exception e) {
            // Cart might be empty
        }
    }
    
    /**
     * Get count of items in cart
     */
    public int getCartItemCount() {
        try {
            waitForCartToLoad();
            List<WebElement> items = driver.findElements(cartItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Check if cart is empty
     */
    public boolean isCartEmpty() {
        try {
            return driver.findElement(emptyCartMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get item name by index
     */
    public String getItemName(int index) {
        waitForCartToLoad();
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            return items.get(index).findElement(itemName).getText();
        }
        return "";
    }
    
    /**
     * Get item price by index
     */
    public String getItemPrice(int index) {
        waitForCartToLoad();
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            return items.get(index).findElement(itemPrice).getText();
        }
        return "";
    }
    
    /**
     * Get item quantity by index
     */
    public String getItemQuantity(int index) {
        waitForCartToLoad();
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            return items.get(index).findElement(itemQuantity).getAttribute("value");
        }
        return "";
    }
    
    /**
     * Update item quantity
     */
    public void updateQuantity(int index, int newQuantity) {
        waitForCartToLoad();
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            WebElement quantityInput = items.get(index).findElement(itemQuantity);
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(newQuantity));
            try {
                Thread.sleep(1000); // Wait for update
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Remove item from cart by index
     */
    public void removeItem(int index) {
        waitForCartToLoad();
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            WebElement remove = items.get(index).findElement(removeButton);
            wait.until(ExpectedConditions.elementToBeClickable(remove)).click();
            try {
                Thread.sleep(1000); // Wait for removal
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Remove first item from cart
     */
    public void removeFirstItem() {
        removeItem(0);
    }
    
    /**
     * Get cart total price
     */
    public String getCartTotal() {
        try {
            return driver.findElement(cartTotal).getText();
        } catch (Exception e) {
            return "Total not available";
        }
    }
    
    /**
     * Get price details breakdown
     */
    public String getPriceDetails() {
        try {
            return driver.findElement(priceDetails).getText();
        } catch (Exception e) {
            return "Price details not available";
        }
    }
    
    /**
     * Proceed to checkout
     */
    public CheckoutPage proceedToCheckout() {
        waitForCartToLoad();
        WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
        proceedBtn.click();
        wait.until(ExpectedConditions.urlContains("checkout"));
        return new CheckoutPage(driver);
    }
    
    /**
     * Save item for later
     */
    public void saveForLater(int index) {
        waitForCartToLoad();
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            WebElement saveBtn = items.get(index).findElement(saveForLater);
            wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        }
    }
}

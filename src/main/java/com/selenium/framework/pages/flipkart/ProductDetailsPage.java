package com.selenium.framework.pages.flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * ProductDetailsPage - Flipkart Product Details Page Object
 * Handles product information, reviews, ratings, and add to cart
 */
public class ProductDetailsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By productTitle = By.xpath("//span[@class='B_NuCI']");
    private By productPrice = By.xpath("//div[@class='_30jeq3 _16Jk6d']");
    private By productRating = By.xpath("//div[@class='_3LWZlK']");
    private By reviewCount = By.xpath("//span[contains(text(), 'Ratings')]");
    private By addToCartButton = By.xpath("//button[contains(text(), 'Add to Cart') or contains(text(), 'ADD TO CART')]");
    private By buyNowButton = By.xpath("//button[contains(text(), 'Buy Now')]");
    private By wishlistButton = By.xpath("//button[contains(@class, 'wishlist')]");
    private By pincode = By.xpath("//input[@placeholder='Check delivery']");
    private By checkButton = By.xpath("//button[contains(text(), 'Check')]");
    private By productImage = By.xpath("//img[@class='_0DkuSH _1cN46Z']");
    private By outOfStockMessage = By.xpath("//div[contains(text(), 'Out of stock')]");
    private By addedToCartMsg = By.xpath("//div[contains(text(), 'Added to Bag')]");
    
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Wait for product details to load
     */
    public void waitForProductDetailsToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
    }
    
    /**
     * Get product title/name
     */
    public String getProductTitle() {
        waitForProductDetailsToLoad();
        return driver.findElement(productTitle).getText();
    }
    
    /**
     * Get product price
     */
    public String getProductPrice() {
        try {
            return driver.findElement(productPrice).getText();
        } catch (Exception e) {
            return "Price not available";
        }
    }
    
    /**
     * Get product rating
     */
    public String getProductRating() {
        try {
            return driver.findElement(productRating).getText();
        } catch (Exception e) {
            return "No rating";
        }
    }
    
    /**
     * Get review count
     */
    public String getReviewCount() {
        try {
            return driver.findElement(reviewCount).getText();
        } catch (Exception e) {
            return "No reviews";
        }
    }
    
    /**
     * Check if product is in stock
     */
    public boolean isProductInStock() {
        try {
            driver.findElement(outOfStockMessage);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    
    /**
     * Add product to cart
     */
    public void addToCart() {
        waitForProductDetailsToLoad();
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addBtn.click();
        
        // Wait for success message
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addedToCartMsg));
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Added to cart confirmation not visible");
        }
    }
    
    /**
     * Click Buy Now button
     */
    public void clickBuyNow() {
        waitForProductDetailsToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(buyNowButton)).click();
    }
    
    /**
     * Add to wishlist
     */
    public void addToWishlist() {
        waitForProductDetailsToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(wishlistButton)).click();
    }
    
    /**
     * Check delivery for pincode
     */
    public String checkDelivery(String pincodeValue) {
        try {
            WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(pincode));
            pincodeInput.clear();
            pincodeInput.sendKeys(pincodeValue);
            
            wait.until(ExpectedConditions.elementToBeClickable(checkButton)).click();
            Thread.sleep(1500);
            
            // Get delivery info text
            By deliveryInfo = By.xpath("//div[contains(text(), 'Delivery')]");
            return driver.findElement(deliveryInfo).getText();
        } catch (Exception e) {
            return "Delivery info not available";
        }
    }
    
    /**
     * Scroll to product images
     */
    public void scrollToImages() {
        try {
            WebElement image = driver.findElement(productImage);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);
        } catch (Exception e) {
            System.out.println("Could not scroll to images");
        }
    }
    
    /**
     * Get product URL
     */
    public String getProductURL() {
        return driver.getCurrentUrl();
    }
}

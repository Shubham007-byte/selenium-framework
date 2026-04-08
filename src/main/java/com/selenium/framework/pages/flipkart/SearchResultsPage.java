package com.selenium.framework.pages.flipkart;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * SearchResultsPage - Flipkart Search Results Page Object
 * Handles product search results, filtering, and product selection
 */
public class SearchResultsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By productItems = By.xpath("//div[contains(@data-id, 'product')]");
    private By productName = By.xpath(".//a[@class='_30jeq3 _1_WHN1']");
    private By productPrice = By.xpath(".//div[@class='_4b5DiR']");
    private By productRating = By.xpath(".//div[@class='_3LWZlK']");
    private By priceFilter = By.xpath("//div[contains(text(), 'Price')]");
    private By lowToHighSort = By.xpath("//span[contains(text(), 'Price -- Low to High')]");
    private By highToLowSort = By.xpath("//span[contains(text(), 'Price -- High to Low')]");
    private By ratingFilter = By.xpath("//span[contains(text(), 'Ratings')]");
    private By noResultsMessage = By.xpath("//div[contains(text(), 'No products found')]");
    private By loadingSpinner = By.xpath("//div[contains(@class, 'loader')]");
    
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    /**
     * Wait for search results to load
     */
    public void waitForResultsToLoad() {
        try {
            // Wait for loading to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
            // Wait for at least one product to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(productItems));
        } catch (Exception e) {
            System.out.println("Results loaded or no loader found");
        }
    }
    
    /**
     * Get count of products on page
     */
    public int getProductCount() {
        waitForResultsToLoad();
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }
    
    /**
     * Get product name by index
     */
    public String getProductName(int index) {
        waitForResultsToLoad();
        List<WebElement> products = driver.findElements(productItems);
        if (index < products.size()) {
            return products.get(index).findElement(productName).getText();
        }
        return "";
    }
    
    /**
     * Get product price by index
     */
    public String getProductPrice(int index) {
        waitForResultsToLoad();
        List<WebElement> products = driver.findElements(productItems);
        if (index < products.size()) {
            try {
                return products.get(index).findElement(productPrice).getText();
            } catch (Exception e) {
                return "Price not available";
            }
        }
        return "";
    }
    
    /**
     * Get product rating by index
     */
    public String getProductRating(int index) {
        waitForResultsToLoad();
        List<WebElement> products = driver.findElements(productItems);
        if (index < products.size()) {
            try {
                return products.get(index).findElement(productRating).getText();
            } catch (Exception e) {
                return "No rating";
            }
        }
        return "";
    }
    
    /**
     * Click on first product to view details
     */
    public ProductDetailsPage clickFirstProduct() {
        waitForResultsToLoad();
        List<WebElement> products = driver.findElements(productItems);
        if (!products.isEmpty()) {
            products.get(0).click();
            wait.until(ExpectedConditions.urlContains("p"));
            return new ProductDetailsPage(driver);
        }
        return null;
    }
    
    /**
     * Click on product by index
     */
    public ProductDetailsPage clickProductByIndex(int index) {
        waitForResultsToLoad();
        List<WebElement> products = driver.findElements(productItems);
        if (index < products.size()) {
            products.get(index).click();
            wait.until(ExpectedConditions.urlContains("p"));
            return new ProductDetailsPage(driver);
        }
        return null;
    }
    
    /**
     * Sort by price - Low to High
     */
    public void sortByPriceLowToHigh() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lowToHighSort)).click();
            Thread.sleep(2000); // Wait for sorting
            waitForResultsToLoad();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sort by price - High to Low
     */
    public void sortByPriceHighToLow() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(highToLowSort)).click();
            Thread.sleep(2000); // Wait for sorting
            waitForResultsToLoad();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Filter by price range (from minimum to maximum)
     */
    public void filterByPriceRange(int minPrice, int maxPrice) {
        // Find price filter inputs and set values
        // This is a complex operation, may need adjustment based on actual Flipkart structure
        try {
            By minPriceInput = By.xpath("//input[@placeholder='Min']");
            By maxPriceInput = By.xpath("//input[@placeholder='Max']");
            
            WebElement minInput = wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceInput));
            minInput.clear();
            minInput.sendKeys(String.valueOf(minPrice));
            
            WebElement maxInput = driver.findElement(maxPriceInput);
            maxInput.clear();
            maxInput.sendKeys(String.valueOf(maxPrice));
            
            Thread.sleep(1500);
            waitForResultsToLoad();
        } catch (Exception e) {
            System.out.println("Price filter not available: " + e.getMessage());
        }
    }
    
    /**
     * Check if no results found
     */
    public boolean isNoResultsFound() {
        try {
            return driver.findElement(noResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get search URL
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}

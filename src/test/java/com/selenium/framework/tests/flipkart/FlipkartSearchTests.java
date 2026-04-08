package com.selenium.framework.tests.flipkart;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.framework.pages.flipkart.HomePage;
import com.selenium.framework.pages.flipkart.ProductDetailsPage;
import com.selenium.framework.pages.flipkart.SearchResultsPage;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

/**
 * FlipkartSearchTests - Test cases for Flipkart product search functionality
 */
public class FlipkartSearchTests extends BaseTest {
    
    private HomePage homePage;
    private SearchResultsPage searchResults;
    private ProductDetailsPage productDetails;
    
    @Test(priority = 1)
    @Description("Verify user can search for a product on Flipkart")
    @Severity(SeverityLevel.CRITICAL)
    public void searchProductByName() {
        // Arrange
        String searchTerm = "iPhone 15";
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        searchResults = homePage.searchProduct(searchTerm);
        searchResults.waitForResultsToLoad();
        
        // Assert
        int productCount = searchResults.getProductCount();
        Assert.assertTrue(productCount > 0, "Products should be found for search: " + searchTerm);
        
        System.out.println("✅ Found " + productCount + " products for: " + searchTerm);
    }
    
    @Test(priority = 2)
    @Description("Verify user can view product details after search")
    @Severity(SeverityLevel.CRITICAL)
    public void viewProductDetails() {
        // Arrange
        String searchTerm = "laptop";
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        searchResults = homePage.searchProduct(searchTerm);
        searchResults.waitForResultsToLoad();
        
        String firstProductName = searchResults.getProductName(0);
        productDetails = searchResults.clickFirstProduct();
        productDetails.waitForProductDetailsToLoad();
        
        // Assert
        Assert.assertTrue(productDetails.getProductTitle().contains(firstProductName) 
                         || !productDetails.getProductTitle().isEmpty(), 
                         "Product title should be displayed");
        Assert.assertNotNull(productDetails.getProductPrice(), "Product price should be displayed");
        
        System.out.println("✅ Product Title: " + productDetails.getProductTitle());
        System.out.println("✅ Product Price: " + productDetails.getProductPrice());
    }
    
    @Test(priority = 3)
    @Description("Verify product count is displayed for search results")
    @Severity(SeverityLevel.NORMAL)
    public void verifySearchResultsCount() {
        // Arrange
        String searchTerm = "headphones";
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        searchResults = homePage.searchProduct(searchTerm);
        searchResults.waitForResultsToLoad();
        
        int productCount = searchResults.getProductCount();
        String firstProductName = searchResults.getProductName(0);
        String firstProductPrice = searchResults.getProductPrice(0);
        
        // Assert
        Assert.assertTrue(productCount >= 1, "At least one product should be found");
        Assert.assertNotNull(firstProductName, "First product name should not be null");
        Assert.assertNotNull(firstProductPrice, "First product price should not be null");
        
        System.out.println("✅ Search Results Count: " + productCount);
        System.out.println("✅ First Product: " + firstProductName + " - " + firstProductPrice);
    }
    
    @Test(priority = 4)
    @Description("Verify user can navigate through multiple search results")
    @Severity(SeverityLevel.NORMAL)
    public void navigateThroughSearchResults() {
        // Arrange
        String searchTerm = "keyboard";
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        searchResults = homePage.searchProduct(searchTerm);
        searchResults.waitForResultsToLoad();
        
        // Click on second product
        productDetails = searchResults.clickProductByIndex(1);
        productDetails.waitForProductDetailsToLoad();
        
        // Assert
        Assert.assertNotNull(productDetails.getProductTitle(), "Product title should be displayed");
        Assert.assertTrue(productDetails.isProductInStock(), "Product should be in stock");
        
        System.out.println("✅ Clicked on second product: " + productDetails.getProductTitle());
    }
    
    @Test(priority = 5)
    @Description("Verify product information is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductInformation() {
        // Arrange
        String searchTerm = "mouse";
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        searchResults = homePage.searchProduct(searchTerm);
        searchResults.waitForResultsToLoad();
        
        productDetails = searchResults.clickFirstProduct();
        productDetails.waitForProductDetailsToLoad();
        
        String title = productDetails.getProductTitle();
        String price = productDetails.getProductPrice();
        String rating = productDetails.getProductRating();
        
        // Assert
        Assert.assertNotNull(title, "Product title should not be null");
        Assert.assertNotNull(price, "Product price should not be null");
        Assert.assertTrue(!price.isEmpty(), "Product price should not be empty");
        
        System.out.println("✅ Product Title: " + title);
        System.out.println("✅ Product Price: " + price);
        System.out.println("✅ Product Rating: " + rating);
    }
}

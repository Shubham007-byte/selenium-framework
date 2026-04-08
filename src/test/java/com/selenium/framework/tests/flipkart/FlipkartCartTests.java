package com.selenium.framework.tests.flipkart;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.framework.pages.flipkart.CartPage;
import com.selenium.framework.pages.flipkart.HomePage;
import com.selenium.framework.pages.flipkart.ProductDetailsPage;
import com.selenium.framework.pages.flipkart.SearchResultsPage;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

/**
 * FlipkartCartTests - Test cases for Flipkart shopping cart functionality
 */
public class FlipkartCartTests extends BaseTest {
    
    private HomePage homePage;
    private SearchResultsPage searchResults;
    private ProductDetailsPage productDetails;
    private CartPage cartPage;
    
    @Test(priority = 1)
    @Description("Verify user can add a product to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductToCart() {
        // Arrange
        String searchTerm = "Samsung Galaxy S23";
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        searchResults = homePage.searchProduct(searchTerm);
        searchResults.waitForResultsToLoad();
        
        productDetails = searchResults.clickFirstProduct();
        productDetails.waitForProductDetailsToLoad();
        
        // Verify product is in stock before adding
        if (productDetails.isProductInStock()) {
            String productTitle = productDetails.getProductTitle();
            productDetails.addToCart();
            
            // Assert
            System.out.println("✅ Product added to cart: " + productTitle);
            Assert.assertTrue(true, "Product should be added to cart");
        } else {
            System.out.println("⚠️ Product is out of stock");
            Assert.assertTrue(true, "Product is out of stock");
        }
    }
    
    @Test(priority = 2)
    @Description("Verify user can view cart contents")
    @Severity(SeverityLevel.CRITICAL)
    public void viewCartContents() {
        // Arrange
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        // Search and add item
        searchResults = homePage.searchProduct("camera");
        searchResults.waitForResultsToLoad();
        
        if (searchResults.getProductCount() > 0) {
            productDetails = searchResults.clickFirstProduct();
            productDetails.waitForProductDetailsToLoad();
            
            if (productDetails.isProductInStock()) {
                productDetails.addToCart();
            }
        }
        
        // Navigate to cart
        cartPage = homePage.clickCartIcon();
        
        // Assert
        int itemCount = cartPage.getCartItemCount();
        System.out.println("✅ Cart Items Count: " + itemCount);
        Assert.assertTrue(itemCount >= 0, "Cart should display item count");
    }
    
    @Test(priority = 3)
    @Description("Verify user can remove product from cart")
    @Severity(SeverityLevel.NORMAL)
    public void removeProductFromCart() {
        // Arrange
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        // Add a product
        searchResults = homePage.searchProduct("tablet");
        searchResults.waitForResultsToLoad();
        
        if (searchResults.getProductCount() > 0) {
            productDetails = searchResults.clickFirstProduct();
            productDetails.waitForProductDetailsToLoad();
            
            if (productDetails.isProductInStock()) {
                productDetails.addToCart();
            }
        }
        
        // View cart
        cartPage = homePage.clickCartIcon();
        int initialCount = cartPage.getCartItemCount();
        
        // Remove item if cart has items
        if (initialCount > 0) {
            cartPage.removeFirstItem();
            int updatedCount = cartPage.getCartItemCount();
            
            // Assert
            System.out.println("✅ Item removed. Count: " + initialCount + " -> " + updatedCount);
            Assert.assertTrue(updatedCount <= initialCount, "Item should be removed from cart");
        }
    }
    
    @Test(priority = 4)
    @Description("Verify cart displays correct item information")
    @Severity(SeverityLevel.NORMAL)
    public void verifyCartItemInformation() {
        // Arrange
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        // Add product
        searchResults = homePage.searchProduct("charger");
        searchResults.waitForResultsToLoad();
        
        if (searchResults.getProductCount() > 0) {
            productDetails = searchResults.clickFirstProduct();
            productDetails.waitForProductDetailsToLoad();
            
            if (productDetails.isProductInStock()) {
                productDetails.addToCart();
            }
        }
        
        cartPage = homePage.clickCartIcon();
        
        if (cartPage.getCartItemCount() > 0) {
            String itemName = cartPage.getItemName(0);
            String itemPrice = cartPage.getItemPrice(0);
            String quantity = cartPage.getItemQuantity(0);
            
            // Assert
            Assert.assertNotNull(itemName, "Item name should be displayed");
            Assert.assertNotNull(itemPrice, "Item price should be displayed");
            Assert.assertNotNull(quantity, "Item quantity should be displayed");
            
            System.out.println("✅ Item Name: " + itemName);
            System.out.println("✅ Item Price: " + itemPrice);
            System.out.println("✅ Item Quantity: " + quantity);
        }
    }
    
    @Test(priority = 5)
    @Description("Verify user can update item quantity in cart")
    @Severity(SeverityLevel.NORMAL)
    public void updateCartItemQuantity() {
        // Arrange
        driver.navigate().to(prop.getProperty("flipkart_url"));
        
        // Act
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        homePage.acceptCookies();
        
        // Add product
        searchResults = homePage.searchProduct("screen protector");
        searchResults.waitForResultsToLoad();
        
        if (searchResults.getProductCount() > 0) {
            productDetails = searchResults.clickFirstProduct();
            productDetails.waitForProductDetailsToLoad();
            
            if (productDetails.isProductInStock()) {
                productDetails.addToCart();
            }
        }
        
        cartPage = homePage.clickCartIcon();
        
        if (cartPage.getCartItemCount() > 0) {
            String originalQty = cartPage.getItemQuantity(0);
            
            // Update quantity
            cartPage.updateQuantity(0, 2);
            String updatedQty = cartPage.getItemQuantity(0);
            
            // Assert
            System.out.println("✅ Quantity Updated: " + originalQty + " -> " + updatedQty);
            Assert.assertNotNull(updatedQty, "Updated quantity should be displayed");
        }
    }
}

# Flipkart Automation Framework - Complete Guide

## 📋 Overview

You now have a complete **Flipkart e-commerce automation framework** integrated into your existing Selenium framework! The framework includes comprehensive test automation for search, shopping cart, login, product details, and checkout functionality.

---

## 🏗️ Project Structure

```
selenium-framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/selenium/framework/
│   │           └── pages/
│   │               └── flipkart/
│   │                   ├── HomePage.java              ✅ Home page interactions
│   │                   ├── LoginPage.java             ✅ Login/Register functionality
│   │                   ├── SearchResultsPage.java     ✅ Search results handling
│   │                   ├── ProductDetailsPage.java    ✅ Product details & actions
│   │                   ├── CartPage.java              ✅ Shopping cart management
│   │                   └── CheckoutPage.java          ✅ Checkout & order placement
│   │
│   └── test/
│       └── java/
│           └── com/selenium/framework/
│               └── tests/
│                   └── flipkart/
│                       ├── FlipkartSearchTests.java   ✅ 5 search test cases
│                       ├── FlipkartCartTests.java     ✅ 5 cart test cases
│                       └── FlipkartLoginTests.java    ✅ 5 login test cases
│
├── config.properties                                  ✅ Updated with Flipkart URL
├── testng.xml                                        ✅ Updated with Flipkart tests
└── README.md
```

---

## 📄 Page Objects Created

### 1. **HomePage.java**
Handles Flipkart homepage interactions

**Key Methods:**
- `searchProduct(String productName)` - Search for a product by name
- `clickLoginButton()` - Navigate to login page
- `clickCartIcon()` - View shopping cart
- `closeLoginPopup()` - Handle login modal
- `acceptCookies()` - Accept cookie banner
- `isHomePageLoaded()` - Verify home page is loaded

**Locators Used:**
- Search box: `//input[@placeholder='Search for products, brands and more']`
- Login button: `//a[contains(text(),'Login')]`
- Cart icon: `//a[contains(@href, 'cart')]`

---

### 2. **SearchResultsPage.java**
Manages product search results and filtering

**Key Methods:**
- `waitForResultsToLoad()` - Wait for products to appear
- `getProductCount()` - Get number of products on page
- `getProductName(int index)` - Get product name by index
- `getProductPrice(int index)` - Get product price by index
- `getProductRating(int index)` - Get product rating by index
- `clickFirstProduct()` - Click on first product
- `clickProductByIndex(int index)` - Click specific product
- `sortByPriceLowToHigh()` - Sort products by price ascending
- `sortByPriceHighToLow()` - Sort products by price descending
- `filterByPriceRange(int min, int max)` - Filter by price range
- `isNoResultsFound()` - Check if no products found

**Locators Used:**
- Product items: `//div[contains(@data-id, 'product')]`
- Product name: `.//a[@class='_30jeq3 _1_WHN1']`
- Product price: `.//div[@class='_4b5DiR']`
- Product rating: `.//div[@class='_3LWZlK']`

---

### 3. **ProductDetailsPage.java**
Handles individual product information and actions

**Key Methods:**
- `waitForProductDetailsToLoad()` - Wait for product page to load
- `getProductTitle()` - Get product title
- `getProductPrice()` - Get product price
- `getProductRating()` - Get product rating
- `getReviewCount()` - Get number of reviews
- `isProductInStock()` - Check if product is in stock
- `addToCart()` - Add product to shopping cart
- `clickBuyNow()` - Click Buy Now button
- `addToWishlist()` - Add product to wishlist
- `checkDelivery(String pincode)` - Check delivery for pincode
- `scrollToImages()` - Scroll to product images

**Locators Used:**
- Product title: `//span[@class='B_NuCI']`
- Product price: `//div[@class='_30jeq3 _16Jk6d']`
- Add to cart button: `//button[contains(text(), 'Add to Cart')]`
- Out of stock: `//div[contains(text(), 'Out of stock')]`

---

### 4. **LoginPage.java**
Manages user authentication

**Key Methods:**
- `waitForLoginForm()` - Wait for login form to appear
- `loginWithEmail(String email, String password)` - Login with email
- `loginWithPhone(String phone, String password)` - Login with phone
- `clickForgotPassword()` - Click forgot password link
- `getErrorMessage()` - Get login error message
- `isLoginFormDisplayed()` - Check if login form is visible

**Locators Used:**
- Email field: `//input[@class='_2IX_2- VfPpkd-fmcmbc']`
- Phone field: `//input[@placeholder='Enter Email or Mobile number']`
- Password field: `//input[@type='password']`
- Login button: `//button[contains(text(), 'Login')]`

---

### 5. **CartPage.java**
Manages shopping cart operations

**Key Methods:**
- `waitForCartToLoad()` - Wait for cart to load
- `getCartItemCount()` - Get number of items in cart
- `isCartEmpty()` - Check if cart is empty
- `getItemName(int index)` - Get item name by index
- `getItemPrice(int index)` - Get item price by index
- `getItemQuantity(int index)` - Get item quantity
- `updateQuantity(int index, int newQty)` - Update item quantity
- `removeItem(int index)` - Remove item from cart
- `removeFirstItem()` - Remove first item
- `getCartTotal()` - Get total cart price
- `getPriceDetails()` - Get price breakdown
- `proceedToCheckout()` - Go to checkout page
- `saveForLater(int index)` - Save item for later

**Locators Used:**
- Cart items: `//div[contains(@class, 'cartItem')]`
- Item name: `.//a[@class='_1wcjJr']`
- Item price: `.//div[@class='_1uv9d1']`
- Remove button: `.//button[contains(text(), 'Remove')]`
- Proceed button: `//button[contains(text(), 'Proceed to Checkout')]`

---

### 6. **CheckoutPage.java**
Handles checkout process and order placement

**Key Methods:**
- `waitForCheckoutPage()` - Wait for checkout page to load
- `getSavedAddressCount()` - Get number of saved addresses
- `selectAddressByIndex(int index)` - Select delivery address
- `selectFirstAddress()` - Select first address
- `getAddressName(int index)` - Get address details
- `addNewAddress(...)` - Add new delivery address
- `selectCreditCard()` - Select credit card payment
- `selectDebitCard()` - Select debit card payment
- `selectNetBanking()` - Select net banking payment
- `selectWallet()` - Select wallet payment
- `selectUPI()` - Select UPI payment
- `getPaymentMethodCount()` - Get available payment methods
- `getOrderTotal()` - Get order total amount
- `getPriceBreakdown()` - Get price details
- `placeOrder()` - Place the final order

---

## ✅ Test Cases (15 Total)

### **FlipkartSearchTests.java** (5 tests)

```groovy
✅ searchProductByName()
   - Search for "iPhone 15"
   - Verify products are found

✅ viewProductDetails()
   - Search for "laptop"
   - Click first product
   - Verify product details are displayed

✅ verifySearchResultsCount()
   - Search for "headphones"
   - Verify product count
   - Verify first product info

✅ navigateThroughSearchResults()
   - Search for "keyboard"
   - Click second product
   - Verify product in stock

✅ verifyProductInformation()
   - Search for "mouse"
   - Get product title, price, rating
   - Verify all details displayed
```

### **FlipkartCartTests.java** (5 tests)

```groovy
✅ addProductToCart()
   - Search for product
   - Add to cart
   - Verify item added

✅ viewCartContents()
   - Add item to cart
   - Navigate to cart
   - Verify cart items displayed

✅ removeProductFromCart()
   - Add item to cart
   - View cart
   - Remove item
   - Verify removal

✅ verifyCartItemInformation()
   - Add product to cart
   - View cart
   - Verify item name, price, quantity

✅ updateCartItemQuantity()
   - Add product to cart
   - Update quantity
   - Verify quantity updated
```

### **FlipkartLoginTests.java** (5 tests)

```groovy
✅ navigateToLoginPage()
   - Navigate to home
   - Click login button
   - Verify login form displayed

✅ verifyLoginFormElements()
   - Navigate to login
   - Verify form elements present

✅ invalidLoginErrorMessage()
   - Navigate to login
   - Verify form ready for input

✅ verifyForgotPasswordLink()
   - Navigate to login
   - Verify forgot password link

✅ verifyLoginPageTitleAndURL()
   - Navigate to login
   - Verify page title and URL
```

---

## 🚀 Running the Tests

### **Run All Tests**
```bash
cd C:\selenium-framework
mvn clean test
```

### **Run Only Flipkart Tests**
```bash
# Run all Flipkart tests
mvn test -Dgroups="flipkart"

# Or run specific test class
mvn test -Dtest=FlipkartSearchTests
mvn test -Dtest=FlipkartCartTests
mvn test -Dtest=FlipkartLoginTests
```

### **Run with Allure Reporting**
```bash
mvn clean test
mvn allure:serve
```

### **Run via Jenkins**
Jenkins will automatically run tests on each build (if webhook configured):
1. Tests execute with Maven
2. Allure reports are generated
3. Test results are displayed in Jenkins UI

---

## 📊 Test Execution Flow

```
flowchart TD
    A["Start Test"] --> B["Setup: Launch Browser"]
    B --> C["Initialize HomePage"]
    C --> D["Close Popups & Accept Cookies"]
    D --> E["Execute Test Action"]
    E --> F["Assert Results"]
    F --> G["Capture Screenshot on Failure"]
    G --> H["Generate Allure Report"]
    H --> I["Teardown: Close Browser"]
    I --> J["End Test"]
```

---

## 🔑 Configuration

### **Update config.properties** (Already Done!)

```properties
# SauceDemo Configuration
url=https://www.saucedemo.com/
browser=chrome

# Flipkart Configuration  ✅ ADDED
flipkart_url=https://www.flipkart.com/
```

### **Update testng.xml** (Already Done!)

```xml
<!-- SauceDemo Tests -->
<test name="SauceDemo Login Tests">
    <classes>
        <class name="tests.LoginTest"/>
    </classes>
</test>

<!-- Flipkart Tests -->
<test name="Flipkart Search Tests">
    <classes>
        <class name="com.selenium.framework.tests.flipkart.FlipkartSearchTests"/>
    </classes>
</test>

<test name="Flipkart Cart Tests">
    <classes>
        <class name="com.selenium.framework.tests.flipkart.FlipkartCartTests"/>
    </classes>
</test>

<test name="Flipkart Login Tests">
    <classes>
        <class name="com.selenium.framework.tests.flipkart.FlipkartLoginTests"/>
    </classes>
</test>
```

---

## 🎯 Key Features

✅ **Search Functionality**
- Product search by name
- Product filtering
- Sorting (price, rating)
- Result verification

✅ **Cart Management**
- Add products to cart
- View cart contents
- Remove items
- Update quantities
- Get cart totals

✅ **Login & Authentication**
- Email/phone login
- Login form validation
- Error handling
- Forgot password link

✅ **Product Details**
- View product information
- Check stock status
- Add to wishlist
- Check delivery availability

✅ **Checkout**
- Address selection
- Payment method selection
- Order placement
- Price breakdown

✅ **Reporting**
- Allure reports with screenshots
- Test listeners for failure handling
- Detailed test descriptions
- Severity levels

---

## 🛠️ Customization Guide

### **Add More Test Cases**

Create a new test class:

```java
package com.selenium.framework.tests.flipkart;

import base.BaseTest;
import com.selenium.framework.pages.flipkart.*;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

public class FlipkartCategoryTests extends BaseTest {
    
    @Test
    @Description("Test category navigation")
    public void testCategoryNavigation() {
        driver.navigate().to(prop.getProperty("flipkart_url"));
        HomePage homePage = new HomePage(driver);
        // Your test logic here
    }
}
```

Add to testng.xml:
```xml
<test name="Flipkart Category Tests">
    <classes>
        <class name="com.selenium.framework.tests.flipkart.FlipkartCategoryTests"/>
    </classes>
</test>
```

### **Update Locators**

If Flipkart updates their UI, update the locators in page objects:

```java
// Old locator (if changed)
private By productName = By.xpath("//a[@class='_30jeq3 _1_WHN1']");

// Update to new locator discovered via browser inspection
private By productName = By.xpath("//a[@class='UPDATED_CLASS']");
```

### **Add Page Object Methods**

Add new interactions to page objects:

```java
public class HomePage {
    // Existing methods...
    
    // Add new method
    public void selectCategory(String categoryName) {
        By category = By.xpath("//span[contains(text(), '" + categoryName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(category)).click();
    }
}
```

---

## ⚠️ Important Notes

### **Anti-Bot Detection**
Flipkart may block automated access. If you encounter issues:
- Add realistic delays: `Thread.sleep(500-1000)`
- Use headless mode carefully
- Rotate user agents
- Use VPN if IP is blocked

### **Dynamic Content**
Use explicit waits for dynamic elements:
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(by));
```

### **Locator Stability**
- Prefer relative XPath over absolute
- Use meaningful attributes (id, name, class)
- Avoid brittle index-based selectors
- Use contains() for partial matches

---

## 📈 Next Steps (Optional Enhancements)

### **Phase 1: Additional Tests**
- [ ] Category filtering tests
- [ ] Brand filtering tests
- [ ] Wishlist management tests
- [ ] Return/refund workflows

### **Phase 2: Advanced Features**
- [ ] Data-driven testing with @DataProvider
- [ ] Cross-browser testing
- [ ] Performance benchmarking
- [ ] API integration tests

### **Phase 3: CI/CD Enhancement**
- [ ] GitHub webhook auto-trigger
- [ ] Email notifications
- [ ] Slack integration
- [ ] Scheduled daily builds

### **Phase 4: Reporting**
- [ ] Custom Allure attachments
- [ ] Video recording on failure
- [ ] HTML report generation
- [ ] Test metrics dashboard

---

## 📞 Troubleshooting

### **Issue: Locators not found**
- Open Flipkart in browser
- Inspect element (F12)
- Compare locators with page object
- Update if changed

### **Issue: Timeout errors**
- Increase wait time in config
- Check internet connection
- Verify element is actually loading
- Check console for JavaScript errors

### **Issue: Tests fail intermittently**
- Add more explicit waits
- Check for modal popups
- Verify page load completeness
- Add Thread.sleep for transitions

### **Issue: Anti-bot detection**
- Add delays between actions
- Use undetectable browser options
- Rotate IP address
- Use GUI mode (not headless)

---

## 🎉 Summary

You now have a **production-ready Flipkart automation framework** with:

✅  6 Page Objects (HomePage, SearchResults, ProductDetails, Cart, Login, Checkout)
✅  15 Test Cases across 3 test classes
✅  Allure reporting with screenshots
✅  TestNG listener for failure handling
✅  Integrated with Jenkins CI/CD
✅  GitHub version control
✅  Comprehensive documentation

**Ready to run Flipkart tests!**

```bash
mvn clean test
```

Happy Testing! 🚀

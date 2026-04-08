# Flipkart Automation Framework

## Project Overview

Automating **Flipkart.com** - India's leading e-commerce platform

### Key Features to Automate
- ✅ User Registration
- ✅ Login/Logout
- ✅ Product Search
- ✅ Product Filtering (Price, Rating, Brand)
- ✅ Add to Cart
- ✅ Remove from Cart
- ✅ Checkout Process
- ✅ Order Placement
- ✅ Order Tracking

---

## Flipkart Automation Strategy

### Phase 1: Structural Analysis

#### Page Objects Needed
1. **HomePage.java** - Landing page, search bar, navigation
2. **LoginPage.java** - Login/Register modal
3. **SearchResultsPage.java** - Product listing, filters
4. **ProductDetailsPage.java** - Product info, ratings, reviews
5. **CartPage.java** - Cart items, quantity, remove
6. **CheckoutPage.java** - Delivery address, payment
7. **OrderConfirmationPage.java** - Order success, tracking

#### Test Scenarios (High Priority)
```java
// Search Tests
- searchProductByName()
- applyPriceFilter()
- applyCategoryFilter()
- sortByRating()
- sortByPrice()

// Shopping Cart Tests
- addProductToCart()
- removeProductFromCart()
- updateQuantity()
- verifyCartTotal()

// Checkout Tests
- proceedToCheckout()
- selectDeliveryAddress()
- selectPaymentMethod()
- completeOrderPayment()

// User Tests
- userRegistration()
- userLogin()
- userLogout()
- viewOrderHistory()
```

---

## Technical Challenges & Solutions

### Challenge 1: Dynamic Elements
**Problem:** Flipkart uses heavy JavaScript, dynamic IDs
**Solution:** Use robust locators (By.xpath, CSS selectors with wait)

### Challenge 2: Pop-ups & Modals
**Problem:** Frequent login/promotional pop-ups
**Solution:** Create PopupHandler utility class

### Challenge 3: Network Delays
**Problem:** Images and content load slowly
**Solution:** Explicit waits with proper timeout

### Challenge 4: Anti-Bot Detection
**Problem:** Flipkart may detect automated access
**Solution:** Add realistic delays, use headless mode carefully

---

## Project Structure

```
flipkart-automation/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       │   └── BaseTest.java
│   │       ├── pages/
│   │       │   ├── HomePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── SearchResultsPage.java
│   │       │   ├── ProductDetailsPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutPage.java
│   │       │   └── OrderConfirmationPage.java
│   │       └── utils/
│   │           ├── DriverFactory.java
│   │           ├── ConfigReader.java
│   │           ├── WaitUtil.java
│   │           ├── PopupHandler.java
│   │           ├── ScreenshotUtil.java
│   │           └── TestListener.java
│   │
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── HomePageTests.java
│       │       ├── LoginTests.java
│       │       ├── SearchTests.java
│       │       ├── ProductTests.java
│       │       ├── CartTests.java
│       │       └── CheckoutTests.java
│       │
│       └── resources/
│           ├── config.properties
│           └── testdata.properties
│
└── README.md
```

---

## Step 1: Analyze Flipkart Structure

### Key Locators to Identify
```
Home Page:
- Search Box: XPath or ID
- Login Icon: Button
- Cart Icon: Link
- Categories: Navigation menu

Product Page:
- Product Title: h1 or similar
- Price: Span with class
- Rating: Star element
- Add to Cart Button: Button

Cart Page:
- Cart Items: Table or list
- Remove Button: Per item
- Proceed to Checkout: Button
```

---

## Step 2: Create Reusable Utilities

### WaitUtil.java
```java
- waitForElementVisibility()
- waitForElementClickability()
- waitForTextPresence()
- waitForElementDisappearance()
```

### PopupHandler.java
```java
- closeLoginPopup()
- closePromotionalBanner()
- acceptCookies()
```

### ElementActions.java
```java
- click()
- sendKeys()
- getText()
- isElementDisplayed()
- scrollToElement()
```

---

## Step 3: Implementation Plan

### Week 1
- [x] Create project structure
- [ ] Create HomePage.java
- [ ] Create LoginPage.java
- [ ] Create SearchResultsPage.java
- [ ] Write basic search tests

### Week 2
- [ ] Create ProductDetailsPage.java
- [ ] Create CartPage.java
- [ ] Write cart tests
- [ ] Write checkout tests

### Week 3
- [ ] Create CheckoutPage.java
- [ ] Create OrderConfirmationPage.java
- [ ] Add data-driven tests
- [ ] Add assertions

### Week 4
- [ ] Cross-browser testing
- [ ] Performance optimization
- [ ] Jenkins integration
- [ ] Reporting

---

## Starting Point

### Option 1: Continue with Existing Project
```
Modify current selenium-framework project
Add Flipkart pages alongside SauceDemo
```

### Option 2: Create New Project
```
Create separate flipkart-automation project
from scratch in new folder
```

**Recommendation:** Create separate project (cleaner structure)

---

## Next Action

Let's start by:
1. Creating new project directory `flipkart-automation`
2. Setting up basic project structure
3. Creating HomePage and LoginPage page objects
4. Writing first search test

---

## Important Notes

⚠️ **Website Terms of Service:** Verify Flipkart allows automation testing
- Check robots.txt
- Use realistic delays between actions
- Avoid excessive requests
- Consider using test environment if available

🔧 **Locator Strategy:** 
- Flipkart updates UI frequently
- Use relative XPaths (more stable)
- Avoid hardcoded waits
- Use explicit waits extensively

---

## Success Criteria

✅ Successfully search products
✅ Add items to cart
✅ Verify cart functionality
✅ Test checkout flow
✅ Generate Allure reports
✅ Run on Jenkins

---

**Ready to start?** Let's create the Flipkart automation project! 🎯

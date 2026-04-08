package com.selenium.framework.pages.flipkart;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * CheckoutPage - Flipkart Checkout Page Object
 * Handles address selection, payment method, and order placement
 */
public class CheckoutPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By savedAddresses = By.xpath("//div[contains(@class, 'address-card')]");
    private By addressName = By.xpath(".//span[contains(@class, 'name')]");
    private By selectAddressButton = By.xpath(".//button[contains(text(), 'Deliver here')]");
    private By newAddressButton = By.xpath("//button[contains(text(), 'Add a new address')]");
    private By nameField = By.xpath("//input[@placeholder='Name']");
    private By phoneField = By.xpath("//input[@placeholder='Phone Number']");
    private By pincodeField = By.xpath("//input[@placeholder='Pincode']");
    private By addressField = By.xpath("//textarea[@placeholder='Address']");
    private By cityField = By.xpath("//input[@placeholder='City']");
    private By stateField = By.xpath("//select[@name='state']");
    private By addAddressButton = By.xpath("//button[contains(text(), 'Save address')]");
    private By paymentMethods = By.xpath("//div[contains(@class, 'payment-option')]");
    private By creditCardOption = By.xpath("//span[contains(text(), 'Credit Card')]");
    private By debitCardOption = By.xpath("//span[contains(text(), 'Debit Card')]");
    private By netBankingOption = By.xpath("//span[contains(text(), 'Net Banking')]");
    private By walletOption = By.xpath("//span[contains(text(), 'Wallet')]");
    private By upiOption = By.xpath("//span[contains(text(), 'UPI')]");
    private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order') or contains(text(), 'Continue')]");
    private By orderTotal = By.xpath("//span[contains(text(), 'Total')]");
    private By priceBreakdown = By.xpath("//div[contains(@class, 'price-details')]");
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Wait for checkout page to load
     */
    public void waitForCheckoutPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(savedAddresses));
    }
    
    /**
     * Get count of saved addresses
     */
    public int getSavedAddressCount() {
        waitForCheckoutPage();
        List<WebElement> addresses = driver.findElements(savedAddresses);
        return addresses.size();
    }
    
    /**
     * Select address by index
     */
    public void selectAddressByIndex(int index) {
        waitForCheckoutPage();
        List<WebElement> addresses = driver.findElements(savedAddresses);
        if (index < addresses.size()) {
            WebElement selectBtn = addresses.get(index).findElement(selectAddressButton);
            wait.until(ExpectedConditions.elementToBeClickable(selectBtn)).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Select first address
     */
    public void selectFirstAddress() {
        selectAddressByIndex(0);
    }
    
    /**
     * Get address name by index
     */
    public String getAddressName(int index) {
        waitForCheckoutPage();
        List<WebElement> addresses = driver.findElements(savedAddresses);
        if (index < addresses.size()) {
            return addresses.get(index).findElement(addressName).getText();
        }
        return "";
    }
    
    /**
     * Add new address
     */
    public void addNewAddress(String name, String phone, String pincode, 
                              String address, String city, String state) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(newAddressButton)).click();
            Thread.sleep(1000);
            
            // Fill address form
            wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
            driver.findElement(phoneField).sendKeys(phone);
            driver.findElement(pincodeField).sendKeys(pincode);
            driver.findElement(addressField).sendKeys(address);
            driver.findElement(cityField).sendKeys(city);
            
            // Handle state dropdown
            wait.until(ExpectedConditions.elementToBeClickable(stateField)).click();
            
            Thread.sleep(1500);
            
            // Click add address button
            wait.until(ExpectedConditions.elementToBeClickable(addAddressButton)).click();
            Thread.sleep(1500);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Select Credit Card payment
     */
    public void selectCreditCard() {
        wait.until(ExpectedConditions.elementToBeClickable(creditCardOption)).click();
    }
    
    /**
     * Select Debit Card payment
     */
    public void selectDebitCard() {
        wait.until(ExpectedConditions.elementToBeClickable(debitCardOption)).click();
    }
    
    /**
     * Select Net Banking payment
     */
    public void selectNetBanking() {
        wait.until(ExpectedConditions.elementToBeClickable(netBankingOption)).click();
    }
    
    /**
     * Select Wallet payment
     */
    public void selectWallet() {
        wait.until(ExpectedConditions.elementToBeClickable(walletOption)).click();
    }
    
    /**
     * Select UPI payment
     */
    public void selectUPI() {
        wait.until(ExpectedConditions.elementToBeClickable(upiOption)).click();
    }
    
    /**
     * Get payment method count
     */
    public int getPaymentMethodCount() {
        try {
            List<WebElement> methods = driver.findElements(paymentMethods);
            return methods.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Get order total
     */
    public String getOrderTotal() {
        try {
            return driver.findElement(orderTotal).getText();
        } catch (Exception e) {
            return "Total not available";
        }
    }
    
    /**
     * Get price breakdown
     */
    public String getPriceBreakdown() {
        try {
            return driver.findElement(priceBreakdown).getText();
        } catch (Exception e) {
            return "Price breakdown not available";
        }
    }
    
    /**
     * Place order
     */
    public void placeOrder() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
            Thread.sleep(2000); // Wait for order processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

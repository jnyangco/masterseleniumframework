package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
    private final By firstName = By.id("billing_first_name");
    private final By lastName = By.id("billing_last_name");
    private final By addressLineOne = By.id("billing_address_1");
    private final By city = By.id("billing_city");
    private final By postCode = By.id("billing_postcode");
    private final By email = By.cssSelector("#billing_email");

    private final By placeOrderBtn = By.xpath("//button[@id='place_order']");
    private final By successMessage = By.cssSelector(".woocommerce-notice");
    private final By showLoginBtn = By.cssSelector(".showlogin");
    private final By usernameTxt = By.cssSelector("#username");
    private final By passwordTxt = By.cssSelector("#password");
    private final By loginBtn = By.xpath("//button[@name='login']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String text) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(text);
    }

    public void enterLastName(String text) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(text);
    }

    public void enterAddressLineOne(String text) {
        driver.findElement(addressLineOne).clear();
        driver.findElement(addressLineOne).sendKeys(text);
    }

    public void enterCity(String text) {
        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(text);
    }

    public void enterPostCode(String text) {
        driver.findElement(postCode).clear();
        driver.findElement(postCode).sendKeys(text);
    }

    public void enterEmail(String text) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(text);
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName());
        enterLastName(billingAddress.getLastName());
        enterAddressLineOne(billingAddress.getAddressLineOne());
        enterCity(billingAddress.getCity());
        enterPostCode(billingAddress.getPostalCode());
        enterEmail(billingAddress.getEmail());
        return this;
    }

    public void placeOrder() {
        driver.findElement(placeOrderBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void clickHereToLoginLink() {
        driver.findElement(showLoginBtn).click();
    }

    public void enterUsernameTxt(String text) {
        driver.findElement(usernameTxt).sendKeys(text);
    }

    public void enterPasswordTxt(String text) {
        driver.findElement(passwordTxt).sendKeys(text);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public void login(User user) {
        enterUsernameTxt(user.getUsername());
        enterPasswordTxt(user.getPassword());
        clickLoginBtn();
    }

}

package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    private final By productName = By.xpath("//td[@class='product-name']/a");
    private final By checkoutBtn = By.xpath("//a[contains(@class,'checkout-button')]");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public void clickCheckoutBtn() {
        driver.findElement(checkoutBtn).click();
    }
}

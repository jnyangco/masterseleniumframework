package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {

    private final By searchFld = By.xpath("//input[@class='search-field']");
    private final By searchBtn = By.xpath("//button[@value='Search']");
    private final By title = By.xpath("//header[@class='woocommerce-products-header']/h1");
    private final By viewCartLink = By.xpath("//a[@title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    private void enterTextInSearchFld(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(text);
    }

    //Builder Pattern Format - apply if action is in the same page -> we can chain the methods in the test case
    //i.e: storePage.enterTextInSearchFld("Blue").clickSearchBtn();
//    public StorePage enterTextInSearchFld(String text) {
//        driver.findElement(searchFld).sendKeys(text);
//        return this;
//    }

    private void clickSearchBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public void search(String text) {
        enterTextInSearchFld(text);
        clickSearchBtn();
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private By getAddToCartBtnElement(String productName) {
        return By.xpath("//a[@aria-label='Add “" +productName+ "” to your cart']");
    }

    public void clickAddToCartBtn(String productName) {
        By addToCartBtn = getAddToCartBtnElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public void clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    }


}

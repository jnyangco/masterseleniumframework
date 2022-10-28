package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {

    private final By searchFld = By.xpath("//input[@class='search-field']");
    private final By searchBtn = By.xpath("//button[@value='Search']");
    private final By title = By.xpath("//header[@class='woocommerce-products-header']/h1");
    private ProductThumbnail productThumbnail;

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
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

    public void search(String text) {
        enterTextInSearchFld(text);
        clickSearchBtn();
    }

    private void clickSearchBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public void waitSearchPageLoaded() {
        wait.until(ExpectedConditions.urlContains("&post_type=product"));
    }
}

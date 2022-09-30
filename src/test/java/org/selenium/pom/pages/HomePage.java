package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {

    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public HomePage(WebDriver driver) {
        super(driver); //passing this driver to the constructor of BasePage class
    }

    public void load() {
        load("/"); //this is just a basepage
    }

    //note: when navigating to other page -> use Fluent Interface -> return object of the Page
    public StorePage navigateToStoreUsingMenu() {
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }
}

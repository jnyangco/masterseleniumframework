package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {
    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;

    public HomePage(WebDriver driver) {
        super(driver); //passing this driver to the constructor of BasePage class
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public void load() {
        load("/"); //this is just a basepage
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }


}

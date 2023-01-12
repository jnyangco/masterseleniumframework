package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Epic Test 1")
@Feature("Feature Test 1")
public class AddToCartTest extends BaseTest {

    @Story("Story Test 1")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @TmsLink("test-1")
    @Test(description = "Test Case 1")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);

        StorePage storePage = new StorePage(getDriver()).load();
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        storePage.getProductThumbnail().clickViewCart();

        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName() + "TestFailedASDF");
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class, description = "Test Case 2")
    public void addToCartFeaturedProducts(Product product) {
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        homePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        homePage.getProductThumbnail().clickViewCart();

        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}

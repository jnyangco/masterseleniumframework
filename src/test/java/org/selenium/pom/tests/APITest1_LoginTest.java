package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class APITest1_LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User();
        user.setUsername(username);
        user.setPassword("Password@1234");
        user.setEmail(username + "@yopmail.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);


        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();//need to load the website first before inject the cookies
                            //note: endpoint is /checkout -> but the page loaded is Cart since no product added yet
        Thread.sleep(5000);

        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();//after injecting the cookies, load the page again -> it will show the checkout with added product

        checkoutPage.clickHereToLoginLink();
        checkoutPage.login(user);

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}

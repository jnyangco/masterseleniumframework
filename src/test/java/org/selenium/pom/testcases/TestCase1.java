package org.selenium.pom.testcases;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase1 extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        /* BillingAddress billingAddress = new BillingAddress();
        billingAddress.
                setFirstName("demo").
                setLastName("user").
                setAddressLineOne("San Francisco").
                setCity("San Francisco").
                setPostalCode("94188").
                setEmail("askcomdch1@yopmail.com");*/
        /*BillingAddress billingAddress = new BillingAddress("demo", "user", "San Francisco", "San Francisco", "94188", "askcomdch1@yopmail.com");*/
        /*Get filePath of the resource using name only
        InputStream is = getClass().getClassLoader().getResourceAsStream("myBillingAddress.json");*/
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        System.out.println("Start >> Homepage");
        HomePage homePage = new HomePage(driver);
        homePage.load();
        //click storeMenuLink + return StorePage object
        //note: since clickStoreMenuLink return the object of the StorePage
        //we do not need to create an instance of the StorePage (i.e: StorePage storePage = new StorePage(driver);)

        System.out.println("Start >> StorePage");
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search(searchFor);
        Thread.sleep(2000);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        storePage.clickAddToCartBtn(product.getName());
        storePage.clickViewCart();

        System.out.println("Start >> CartPage");
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        cartPage.clickCheckoutBtn();

        System.out.println("Start >> CheckoutPage");
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setBillingAddress(billingAddress);
        /*checkoutPage.enterFirstName("demo");
        checkoutPage.enterLastName("user");
        checkoutPage.enterAddressLineOne("San Francisco");
        checkoutPage.enterCity("San Francisco");
        checkoutPage.enterPostCode("94188");
        checkoutPage.enterEmail("askcomdch1@yopmail.com");*/
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User("askcomdch1@yopmail.com", "Password@1234");

        System.out.println("Start >> Homepage");
        HomePage homePage = new HomePage(driver);
        homePage.load();

        System.out.println("Start >> StorePage");
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search(searchFor);
        Thread.sleep(2000);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        storePage.clickAddToCartBtn(product.getName());
        storePage.clickViewCart();

        System.out.println("Start >> CartPage");
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        cartPage.clickCheckoutBtn();

        System.out.println("Start >> CheckoutPage");
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickHereToLoginLink();
        checkoutPage.login(user);
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
        
        checkoutPage.placeOrder();
        
    }

}

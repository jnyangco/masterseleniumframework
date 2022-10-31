package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest_backup_abstract {
    //protected WebDriver driver;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //abstract
    private ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();

    //Getter and Setter
    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
    //abstract
    private void setDriverManager(DriverManagerAbstract driverManager) {
        this.driverManager.set(driverManager);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }
    //abstract
    protected DriverManagerAbstract getDriverManager() {
        return this.driverManager.get();
    }

    //note: synchronized will run the method in sequence (in 1 test - java class)
    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        //Use this line if running testng.xml with browser parameter
//        browser = System.getProperty("browser", browser);

        //Use this line if running pom.xml, or direct testng run in class
        if(browser == null) {
            browser = "CHROME";
        }

        //1-maven, 2-testng (if maven=null) then get from testng.xml, 3-from config file (so you can run by right-clicking on method name)
        //Usage: 1-maven only -> CICD, 2-testng.xml only, 3-maven with testng.xml

        //driver = new DriverManager().initializeDriver(browser);
//        setDriver(new DriverManagerOriginal().initializeDriver(browser)); //backup
        //factory design

        //INTERFACE
        //we don't need how to initialize drive, we just passed the browser - and it is being taken are of the implementing classes
        //also, someone can work on ChromeDriverManager (solo class) which will minimize the code conflict

        //User this when using DriverManagerFactory (implements)
        //setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());

        //User this when using DriverManagerFactoryAbstract (extends abstract class)
        //abstract
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());

        //
        //System.out.println("CURRENT THREAD: " +Thread.currentThread().getId() +", " +"DRIVER = " +getDriver());
        //abstract
        System.out.println("CURRENT THREAD: " +Thread.currentThread().getId() +", " +"DRIVER = " +getDriver());
    }

    @AfterMethod
    public synchronized void quitDriver() throws InterruptedException {
        Thread.sleep(300);
        //driver.quit();
        System.out.println("CURRENT THREAD: " +Thread.currentThread().getId() +", " +"DRIVER = " +getDriver());

//        Thread.sleep(3000);
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }


}

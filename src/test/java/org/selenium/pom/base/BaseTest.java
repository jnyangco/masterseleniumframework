package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    //protected WebDriver driver;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //Getter and Setter
    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    //note: synchronized will run the method in sequence (in 1 test - java class)
    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        //Use this line if running testng.xml with browser parameter
        browser = System.getProperty("browser", browser);
        //Use this line if running pom.xml, or direct testng run in class
//        if(browser == null) {
//            browser = "CHROME";
//        }



        //1-maven, 2-testng (if maven=null) then get from testng.xml, 3-from config file (so you can run by right-clicking on method name)
        //Usage: 1-maven only -> CICD, 2-testng.xml only, 3-maven with testng.xml

        //driver = new DriverManager().initializeDriver(browser);
//        setDriver(new DriverManagerOriginal().initializeDriver(browser)); //backup
        //factory design

        //INTERFACE
        //we don't need how to initialize drive, we just passed the browser - and it is being taken are of the implementing classes
        //also, someone can work on ChromeDriverManager (solo class) which will minimize the code conflict

        //User this when using DriverManagerFactory (implements)
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());

        System.out.println("CURRENT THREAD: " +Thread.currentThread().getId() +", " +"DRIVER = " +getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws InterruptedException, IOException {
        Thread.sleep(300);
        //driver.quit();
        System.out.println("CURRENT THREAD: " +Thread.currentThread().getId() +", " +"DRIVER = " +getDriver());


        //if if test case failed (status=1 passed, status=2 failed)
        if(result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("screenshots" + File.separator + browser + File.separator
                    + result.getTestClass().getRealClass().getSimpleName()
                    + "_" + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destFile);
            //takeScreenshotUsingAShot(destFile);
        }


//        Thread.sleep(3000);
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }


    //for screenshot
    //private - since we will only use this within the class itself
    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }


    //screenshot full pages
    //note: it will only work if the machine resolution is set to 100%
    private void takeScreenshotUsingAShot(File destfile) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());
        try {
            ImageIO.write(screenshot.getImage(), "PNG", destfile);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}

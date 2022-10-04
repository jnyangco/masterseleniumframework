package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.BrowserType;

import java.time.Duration;

public class DriverManager {

    public WebDriver initializeDriver(String browser) {
        //System.setProperty("webdriver.chrome.driver", "C:\\Selenium Configuration\\browser_jars\\chromedriver.exe");
        //This line of code will set up the chromedriver automatically
//        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        //String browser = System.getProperty("browser", "CHROME");//if null - set default to CHROME

//        String localBrowser;
//        localBrowser = System.getProperty("browser");//maven
//        localBrowser = browser;//testng.xml

        //1-maven, 2-testng (maven=null) then get from testng.xml, 3-from config file (so you can run by right-clicking on method name)
        //Usage: 1-maven only -> CICD, 2-testng.xml only, 3-maven with testng.xml
        browser = System.getProperty("browser", browser);

        //switch (browser.toLowerCase()) { }
        switch (BrowserType.valueOf(browser)) { //converting browser string to enum constants
            case CHROME:
                WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir")+"\\src\\test\\java\\org\\selenium\\pom\\drivers").setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath(System.getProperty("user.dir")+"\\src\\test\\java\\org\\selenium\\pom\\drivers").setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: " +browser);
        }

        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}

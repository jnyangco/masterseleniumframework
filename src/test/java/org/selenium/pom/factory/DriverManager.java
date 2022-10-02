package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {

    public WebDriver initializeDriver() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Selenium Configuration\\browser_jars\\chromedriver.exe");

        //This line of code will set up the chromedriver automatically
//        WebDriverManager.chromedriver().setup();
        //If you want to save the downloaded driver to custom folder (not default folder by selenium)
//        WebDriverManager.chromedriver().cachePath(System.getProperty("User.dir")+"/src/java/org.selenium.pom/driver").setup();
        WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir")+"\\src\\test\\java\\org\\selenium\\pom\\drivers").setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}

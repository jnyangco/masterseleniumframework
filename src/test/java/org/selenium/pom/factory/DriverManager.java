package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    public WebDriver initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Configuration\\browser_jars\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}

package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements DriverManager {
    private WebDriver driver;

    @Override
    //before design factory
    //public void createDriver() {
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir")+"\\src\\test\\java\\org\\selenium\\pom\\drivers").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /*
    @Override
    public void quitDriver() {
        driver.quit();
    }
     */
}

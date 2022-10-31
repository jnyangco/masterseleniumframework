package org.selenium.pom.factory;

public class DriverManagerOriginal {

    /*
    public WebDriver initializeDriver(String browser) {
        //System.setProperty("webdriver.chrome.driver", "C:\\Selenium Configuration\\browser_jars\\chromedriver.exe");
        //This line of code will set up the chromedriver automatically
//        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        //String browser = System.getProperty("browser", "CHROME");//if null - set default to CHROME

        //switch (browser.toLowerCase()) { }
        switch (DriverType.valueOf(browser)) { //converting browser string to enum constants
            case CHROME:

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

     */
}

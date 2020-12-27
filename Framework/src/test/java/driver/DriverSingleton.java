package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.CommonConditions;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton(){}

    public static WebDriver getDriver(){
        if (null == driver){
            String k = "";
            switch(k){// (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "opera": {
                    WebDriverManager.operadriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-gpu");
                    driver  = new ChromeDriver(options);
                }
            }

            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}

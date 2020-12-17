package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import page.AliexpressProductPage;
import org.testng.annotations.Test;

public class TestPageObjectWebDriver {

    private WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();
    @BeforeTest (alwaysRun = true)
    public void setBrowserOptions()
    {
        if(CommonConditions.isWindows()){
            System.setProperty("webdriver.chrome.driver","D:/webdriver/chromedriver.exe" );
        }
        else if(CommonConditions.isMac()){
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu");
        driver  = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testPriceOfProductAndDeliveringInCart() {

        AliexpressProductPage aliexpressProductPage = new AliexpressProductPage(driver).openPage();
        double costOfAddedProduct = aliexpressProductPage.getPriceOfProduct();
        double priceOfDeliveringProduct = aliexpressProductPage.getPriceOfDeliveringProduct();
        aliexpressProductPage.goToCart().selectPurchasedItem();
        double cartDeliveryPrice = aliexpressProductPage.checkValuesInCart().getDeliveringPriceOfProductInCart();
        double cartProductPrice = aliexpressProductPage.checkValuesInCart().getPriceOfProductInCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(costOfAddedProduct, cartProductPrice);
        softAssert.assertEquals(priceOfDeliveringProduct, cartDeliveryPrice,0.01);
        softAssert.assertAll();
    }

    @AfterTest (alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
}

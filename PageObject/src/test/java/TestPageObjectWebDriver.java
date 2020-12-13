import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;
import pageobject_model.page.AliexpressProductPage;

public class TestPageObjectWebDriver {

    private WebDriver driver;
    @Before
    public void setBrowserOptions()
    {

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");//D:/webdriver/chromedriver.exe" );
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver  = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Test
    public void TestPriceOfProductAndDeliveringInCart() {

        AliexpressProductPage aliexpressProductPage = new AliexpressProductPage(driver);
        aliexpressProductPage.openPage();
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
    @After
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }

}

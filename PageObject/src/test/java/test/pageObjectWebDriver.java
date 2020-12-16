package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;
import page.AliexpressProductPage;

public class pageObjectWebDriver {

    private WebDriver driver;
    @Before
    public void setBrowserOptions()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver  = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Test
    public void testCheckPriceOfProductAndDeleveringInCart() {
        AliexpressProductPage aliexpressProductPage = new AliexpressProductPage(driver);
        aliexpressProductPage.openPage();
        double costOfAddedProduct = aliexpressProductPage.getPriceOfProduct();
        double priceOfDeliveringProduct = aliexpressProductPage.getPriceOfDeliveringProduct();
        aliexpressProductPage.goToCart().selectPurchasedItem();
        double cartDeliveryPrice = aliexpressProductPage.checkValuesInCart().getDeliveringPriceOfProductInCart();
        double cartProductPrice = aliexpressProductPage.checkValuesInCart().getPriceOfProductInCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(costOfAddedProduct, cartProductPrice);
        softAssert.assertEquals(priceOfDeliveringProduct, cartDeliveryPrice);
        softAssert.assertAll();
    }
    @After
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }

}
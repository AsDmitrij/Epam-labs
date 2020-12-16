import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WebDriverTest {

    private org.openqa.selenium.WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();
    @BeforeTest(alwaysRun=true)
    public void setBrowserOptions()
    {
        if(Conditions.isWindows()){
            System.setProperty("webdriver.chrome.driver","D:/webdriver/chromedriver.exe" );
        }
        else if(Conditions.isMac()){
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver  = new ChromeDriver(options);
    }
    @Test
    public void TestAddProductToCart() {
        driver.get("https://ali.onl/1JrL");
        Conditions.AddCookies(driver);
        driver.get("https://ali.onl/1JrL");
        List<WebElement> selectProduct = driver.findElements(By.xpath("//img[@title='V3 Nodemcu-CH340']"));
        selectProduct.get(0).click();
        String priceOfAddedProductInString  = driver.findElements(By.xpath("//div[@class='product-price-current']/span[@class='product-price-value' and 1]")).get(0).getText();
        double priceOfAddedProduct = Conditions.convertPriceToDouble(priceOfAddedProductInString);
        String priceOfDeliveringProductInString = driver.findElements(By.xpath("//div[@class='product-shipping-price']")).get(0).getText();
        double priceOfDeliveringProduct = Conditions.convertPriceToDouble(priceOfDeliveringProductInString);
        List<WebElement> addToCart = driver.findElements(By.xpath("//button[@class='next-btn next-large next-btn-primary addcart']"));
        addToCart.get(0).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='next-btn next-small next-btn-primary view-shopcart']")));
        List<WebElement> viewCart = driver.findElements(By.xpath("//button[@class='next-btn next-small next-btn-primary view-shopcart']"));
        viewCart.get(0).click();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='next-checkbox-label']")));
        List<WebElement> selectProductForBuy = driver.findElements(By.xpath("//span[@class='next-checkbox-label']"));
        selectProductForBuy.get(0).click();
        String cartPriceInSting = driver.findElement(By.xpath("//div[@class='total-price']/dl[1]/dd[1]")).getText();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='total-price']/dl[1]/dd[1]"),cartPriceInSting)));
        cartPriceInSting = driver.findElement(By.xpath("//div[1]/dl[@class='charges-totle ' and 1]/dd[1]")).getText();
        String deliveryPriceInSting = driver.findElement(By.xpath("//div[2]/dl[@class='charges-totle ' and 1]/dd[1]")).getText();
        double cartProductPrice = Conditions.convertPriceToDouble(cartPriceInSting);
        double cartDeliveryPrice = Conditions.convertPriceToDouble(deliveryPriceInSting);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(priceOfAddedProduct, cartProductPrice);
        softAssert.assertEquals(priceOfDeliveringProduct, cartDeliveryPrice,0.01);
        softAssert.assertAll();
    }
    @AfterTest(alwaysRun=true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }

}
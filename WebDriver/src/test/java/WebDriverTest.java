import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WebDriverTest {

    private WebDriver driver;
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
        options.addArguments("--disable-gpu");
        driver  = new ChromeDriver(options);
    }
    @Test
    public void testAddProductToCart() {
        driver.get("https://ali.onl/1JrL");
        Conditions.AddCookies(driver);
        driver.get("https://ali.onl/1JrL");

        WebElement selectProduct = driver.findElement(By.xpath("//li[1]/div[@class='sku-property-image' and 1]"));
        selectProduct.click();

        String priceOfAddedProductInString  = driver.findElement(By.xpath("//div[@class='product-price-current']/span[@class='product-price-value' and 1]")).getText();
        double priceOfAddedProduct = Conditions.convertPriceToDouble(priceOfAddedProductInString);
        String priceOfDeliveringProductInString = driver.findElement(By.xpath("//div[@class='product-shipping-price']")).getText();
        double priceOfDeliveringProduct = Conditions.convertPriceToDouble(priceOfDeliveringProductInString);

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='next-btn next-large next-btn-primary addcart']"));
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='next-btn next-large next-btn-primary addcart']")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", addToCart);

        new WebDriverWait(driver,2)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='next-btn next-small next-btn-primary view-shopcart']")));
        List<WebElement> viewCart = driver.findElements(By.xpath("//button[@class='next-btn next-small next-btn-primary view-shopcart']"));
        viewCart.get(0).click();
        new WebDriverWait(driver,2)
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

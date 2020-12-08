import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.regex.*;

import java.util.List;

public class WebDriver {

    private org.openqa.selenium.WebDriver driver;
    @Before //Method(alwaysRun=true)
    public void setBrowserOptions()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver  = new ChromeDriver(options);
    }
    @Test
    public void testAddProductToCart() {
        driver.get("https://ali.onl/1JrL");
        AddCookies();
        driver.get("https://ali.onl/1JrL");
        List<WebElement> selectProduct = driver.findElements(By.xpath("//img[@title='V3 Nodemcu-CH340']"));
        selectProduct.get(0).click();
        String priceOfAddedProductInString  = driver.findElements(By.xpath("//div[@class='product-price-current']/span[@class='product-price-value' and 1]")).get(0).getText();
        double priceOfAddedProduct = convertPriceToDouble(priceOfAddedProductInString);
        String priceOfDeliveringProductInString = driver.findElements(By.xpath("//div[@class='product-shipping-price']")).get(0).getText();
        double priceOfDeliveringProduct = convertPriceToDouble(priceOfDeliveringProductInString);
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
        double cartProductPrice = convertPriceToDouble(cartPriceInSting);
        double cartDeliveryPrice = convertPriceToDouble(deliveryPriceInSting);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(priceOfAddedProduct, cartProductPrice);
        softAssert.assertEquals(priceOfDeliveringProduct, cartDeliveryPrice);
        softAssert.assertAll();
    }
    @After  //Method(alwaysRun=true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
    void AddCookies()
    {
        Cookie cookie_bl_uid = new Cookie.Builder("_bl_uid", "zqk1eh8ewp7aL7uFmtjF4n7ewhF7").build();
        Cookie cookie_ga = new Cookie.Builder("_ga", "GA1.2.505662652.1606241702").build();
        Cookie cookie_ym_d = new Cookie.Builder("_ym_d", "1606241703").build();
        Cookie cookie_af_ss_b = new Cookie.Builder("af_ss_b", "1").build();
        Cookie cookie_ali_apache_id = new Cookie.Builder("ali_apache_id", "33.19.92.58.16062417082.143044.2").build();
        Cookie cookie_cna = new Cookie.Builder("cna", "pTtEGGTYJ0MCAYYRmNzrEG1k").build();
        Cookie cookie_e_id = new Cookie.Builder("e_id", "pt30").build();
        Cookie cookie_intl_common_forever = new Cookie.Builder("intl_common_forever", "5vqa8HMiFUi3uijfhrvAG2+LoUwyd32wwvYOafjhsDhjQ5/YeHdYFg==").build();
        Cookie cookie_isg = new Cookie.Builder("isg", "BGJi25igSXaTRVVckcZYnc8zo-jEs2bNMl0uCqz7jlWAfwL5lEO23ej-qqOD3d5l").build();
        Cookie cookie_I = new Cookie.Builder("I", "eBxU6S6HOlyyISPOBOfwourza77OSKRxnuPzaNbMiOCP_zfB5N0dBZRiHHY6C3HNh6qDR3ylR_DpBeYBqQd-nxvtsJkyaQkmn").build();
        Cookie cookie_tfstk = new Cookie.Builder("tfstk", "c68lBstTG3S760sDf4_Ws1WCD-gOZ_zFIe8XuE6D_cV9QCTVi-Z4bZzmiTbPik1..").build();
        Cookie cookie_xman_f = new Cookie.Builder("xman_f", "wQNuqQ/bcnLtijos/em7czfkD+us5Ij5TxAPRpf/GOV2qt7kO8Fqar/iUooszYOkEfSIpVBrfkM4yDLTKdNfN9z1OgqR3eRQtqrR4xCmeSJBKdDRpQGCosk7iN2HZi2m/28738O3OnMULzered8uJWebneHMmWEPQ8WptRxY/D1R78tCmWE8Uo6v7Cp2fye1A2MjcUKS0mdfX/IW9ne1ndIbAH0w5N9jwzAEk/ov1f/uhbqMWNfdcd5+1PRkPgUvvYL1Y8eWr/kb6qxM8ApiKhwhpNjtVXl9L4K19Tao/pHtLcDzAhUo9e9+HxA8Jk45n5AKx95V9eLiU1mnitUjEphyD3sG0mrLKQjWcZEXOVlGE+DzDqp3/3Io1M7QoWtC").build();
        Cookie cookie_xman_t = new Cookie.Builder("xman_t", "jNxIfWo25SzQPi/HrdpJh+SObit0k7XxWMO6mJRNS6Lwpf9eoTbHqFVesveFFYzt").build();
        Cookie cookie_xman_us_f = new Cookie.Builder("xman_us_f", "x_locale=ru_RU&x_l=0&x_user=BY|Dmitrij|Asonov|ifm|1620821004&x_lid=by19461004ormae&x_c_chg=0&x_as_i=%7B%22aeuCID%22%3A%228ac202316d3f4f88ac31ddacb6f6f817-1607194831292-05427-MDzNljuU%22%2C%22af%22%3A%222048384766%22%2C%22channel%22%3A%22AFFILIATE%22%2C%22cookieCacheEffectTime%22%3A1607195785315%2C%22isCookieCache%22%3A%22Y%22%2C%22ms%22%3A%220%22%2C%22pid%22%3A%222048384766%22%2C%22tagtime%22%3A1607194831292%7D&acs_rt=a4d05c5c92f94ba6936a16a0e10a4f65").build();

        driver.manage().addCookie(cookie_bl_uid);
        driver.manage().addCookie(cookie_ga);
        driver.manage().addCookie(cookie_ym_d);
        driver.manage().addCookie(cookie_af_ss_b);
        driver.manage().addCookie(cookie_ali_apache_id);
        driver.manage().addCookie(cookie_cna);
        driver.manage().addCookie(cookie_e_id);
        driver.manage().addCookie(cookie_intl_common_forever);
        driver.manage().addCookie(cookie_isg);
        driver.manage().addCookie(cookie_I);
        driver.manage().addCookie(cookie_tfstk);
        driver.manage().addCookie(cookie_xman_f);
        driver.manage().addCookie(cookie_xman_t);
        driver.manage().addCookie(cookie_xman_us_f);
    }
    double convertPriceToDouble(String price)
    {
        Pattern pattern=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pattern.matcher(price);
        int start = 0;
        double result =0;
        while (matcher.find(start)) {
            String value = price.substring(matcher.start(), matcher.end());
            value =value.replace(',', '.');
            result = Double.parseDouble(value);
            start = matcher.end();
        }
        return result;
    }

}
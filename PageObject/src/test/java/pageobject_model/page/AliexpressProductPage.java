package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AliexpressProductPage {

    private static final String PRODUCT_PAGE_URL = "https://ali.onl/1JrL";
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//img[@title='V3 Nodemcu-CH340']")
    private WebElement selectProduct;

    @FindBy(xpath = "//div[@class='product-price-current']/span[@class='product-price-value' and 1]")
    private WebElement priceOfChosenProduct;

    @FindBy(xpath = "//div[@class='product-shipping-price']")
    private WebElement priceOfDeliveringChosenProduct;

    @FindBy(xpath = "//button[@class='next-btn next-large next-btn-primary addcart']")
    private WebElement addToCart;

    @FindBy(xpath = "//button[@class='next-btn next-small next-btn-primary view-shopcart']")
    private WebElement viewCart;

    public AliexpressProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AliexpressProductPage openPage(){
        driver.get(PRODUCT_PAGE_URL);
        AddCookies();
        driver.get(PRODUCT_PAGE_URL);
        return  this;
    }

    public double getPriceOfProduct(){
        selectProduct.click();
        String priceOfAddedProductInString = priceOfChosenProduct.getText();
        double priceOfAddedProduct = convertPriceToDouble(priceOfAddedProductInString);
        return priceOfAddedProduct;
    }
    public double getPriceOfDeliveringProduct(){
        String priceOfDeliveringProductInString =priceOfDeliveringChosenProduct.getText();
        double priceOfDeliveringProduct = convertPriceToDouble(priceOfDeliveringProductInString);
        return priceOfDeliveringProduct;
    }

    public AliexpressCartPage goToCart(){
        addToCart.click();
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='next-btn next-small next-btn-primary view-shopcart']")));
        viewCart.click();
        return new AliexpressCartPage(driver);
    }
    public AliexpressCartPage checkValuesInCart(){
        return new AliexpressCartPage(driver);
    }
    private void AddCookies()
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
    private double convertPriceToDouble(String price)
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

package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.CommonConditions;

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

    private static By viewCartLocator = By.xpath("//button[@class='next-btn next-small next-btn-primary view-shopcart']");

    public AliexpressProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AliexpressProductPage openPage(){
        driver.get(PRODUCT_PAGE_URL);
        CommonConditions.AddCookies(driver);
        driver.get(PRODUCT_PAGE_URL);
        return  this;
    }

    public double getPriceOfProduct(){
        selectProduct.click();
        String priceOfAddedProductInString = priceOfChosenProduct.getText();
        double priceOfAddedProduct = CommonConditions.convertPriceToDouble(priceOfAddedProductInString);
        return priceOfAddedProduct;
    }
    public double getPriceOfDeliveringProduct(){
        String priceOfDeliveringProductInString =priceOfDeliveringChosenProduct.getText();
        double priceOfDeliveringProduct = CommonConditions.convertPriceToDouble(priceOfDeliveringProductInString);
        return priceOfDeliveringProduct;
    }

    public AliexpressCartPage goToCart(){
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.elementToBeClickable(addToCart));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", addToCart);
        addToCart.click();
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.presenceOfElementLocated((viewCartLocator)));
        viewCart.click();
        return new AliexpressCartPage(driver);
    }
    public AliexpressCartPage checkValuesInCart(){
        return new AliexpressCartPage(driver);
    }



}

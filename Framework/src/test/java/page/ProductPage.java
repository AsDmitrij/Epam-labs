package page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

public class ProductPage extends AbstractPage{

    private static final String PRODUCT_PAGE_URL = "https://www.dns-shop.ru/product/5fbc44bb7b2a3332/64-smartfon-samsung-galaxy-m21-64-gb-cernyj/";
    private static final String SPRODUCT_PAGE_URL = "https://www.dns-shop.ru/product/01806bf519863332/63-smartfon-xiaomi-redmi-note-8-128-gb-belyj/";

    @FindBy(xpath = "//span[@class='product-card-price__current product-card-price__current_active']")
    private WebElement priceOfChosenProduct;

    @FindBy(xpath = "//button[@class='button-ui button-ui_brand buy-btn btn-cart button-ui_buy-card button-ui_passive']")
    private WebElement addToCartPassive;

    @FindBy(xpath = "//button[@class='button-ui button-ui_brand buy-btn btn-cart button-ui_buy-card']")
    private WebElement addToCartActive;

    @FindBy(xpath = "//a[@class='cart-modal__button button-ui button-ui_brand']")
    private WebElement viewCart;

    @FindBy(xpath = "//span[@class='ui-checkbox__box']")
    private WebElement compareCheckBox;

    private static By viewCartLocator = By.xpath("//button[@class='button-ui button-ui_brand buy-btn btn-cart button-ui_buy-card button-ui_passive']");
    private static By viewPriceLocator= By.xpath("//span[@class='product-card-price__current product-card-price__current_active']");
    private static By goToCartLocator= By.xpath("//a[@class='cart-modal__button button-ui button-ui_brand']");
    private static By compareCheckBoxLocator= By.xpath("//span[@class='ui-checkbox__box']");


    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductPage openPage(){
        driver.navigate().to(PRODUCT_PAGE_URL);
        return this;
    }
    public  ProductPage addProductToCompare(){
        CustomWaits.checkClickable(compareCheckBoxLocator,driver);
        compareCheckBox.click();
        return this;
    }
    public double getPriceOfProduct(){
        CustomWaits.checkPresence(viewPriceLocator,driver);
        String priceOfAddedProductInString = priceOfChosenProduct.getText();
        double priceOfAddedProduct = Double.parseDouble(priceOfAddedProductInString.replaceAll("[^0-9]", ""));
        return priceOfAddedProduct;
    }
    public ProductPage putProductToCart(){
        CustomWaits.checkPresence(viewCartLocator,driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(addToCartPassive).build().perform();
        addToCartActive.click();
        return this;
    }
    public CartPage goToCart() {
        CustomWaits.checkPresence(goToCartLocator,driver);
        viewCart.click();
        return new CartPage(driver);
    }

    public CartPage checkValuesInCart(){
        return new CartPage(driver);
    }



}

package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.util.List;

public class CartPage extends AbstractPage{

    private final String CART_URL = "https://www.dns-shop.ru/order/begin/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//span[@class='price__current']")
    private List<WebElement> productPriceInCart;

    @FindBy(xpath = "//button[@class='menu-control-button']")
    private WebElement refreshCart;

    @FindBy(xpath = "//a[@class='accessories__item-title' and 1]")
    private List<WebElement> offeredAccessories;

    @FindBy(xpath = "//a[@class='cart-items__product-name-link']")
    private WebElement nameOfProductInCart;

    @FindBy(xpath = "//span[@class='select-all-checkbox__icon']")
    private WebElement selectAllItems;

    @FindBy(xpath = "//div[@class='mass-selection__delete-btn']")
    private WebElement deleteSelectItems;

    @FindBy(xpath = "//button[@class='menu-control-button']")
    private List<WebElement> deleteItem;

    @FindBy(xpath = "//div[@class='cart-items__product']")
    private List<WebElement> productCarts;

    private static By deleteItemLocator= By.xpath("//button[@class='menu-control-button']");
    private static By productCartsLocator= By.xpath("//div[@class='cart-items__product']");
    private static By selectAllItemsLocator= By.xpath("//span[@class='select-all-checkbox__icon']");
    private static By deleteSelectItemsLocator= By.xpath("//div[@class='mass-selection__delete-btn']");
    private static By viewProductInCartLocator= By.xpath("//div[@class='cart-items__product-name']");
    private static By viewPriceLocator= By.xpath("//span[@class='price__current']");//span[@class='price__current']//span[@class='common-products-list-description__price']
    private static By refreshCartLocator= By.xpath("//div[1]/div[@class='price__block price__block_main' and 1]/span[@class='price__current' and 2]");



    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    protected CartPage openPage() {
        driver.navigate().to(CART_URL);
        logger.info("Cart page opened");
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }
    public double getPriceOfProductInCart(){
        CustomWaits.checkPresence(viewPriceLocator,driver);
        String priceOfProductInCartInString = productPriceInCart.get(0).getText();
        double priceOfProductInCart = Double.parseDouble(priceOfProductInCartInString.replaceAll("[^0-9]", ""));
        return priceOfProductInCart;
    }
    public String getNameOfProductInCart(){
        CustomWaits.checkPresence(viewProductInCartLocator,driver);
        String productName = nameOfProductInCart.getText();
        return productName;
    }
    public List<WebElement> getListOfOfferedAccessories(){
        List<WebElement> listOfOfferedAccessories = offeredAccessories;
        return listOfOfferedAccessories;
    }
    public CartPage selectAllItemsInCart(){
        CustomWaits.checkClickable(selectAllItemsLocator,driver);
        selectAllItems.click();
        return this;
    }
    public CartPage deleteSelectedItemsInCart(){


        CustomWaits.checkClickable(deleteSelectItemsLocator,driver);
        deleteSelectItems.click();
        return this;
    }
    public CartPage deleteAllItems(){
        if(driver.findElements(selectAllItemsLocator).size() > 0){
            selectAllItemsInCart().deleteSelectedItemsInCart();
        }
        else{
            CustomWaits.checkClickable(deleteItemLocator,driver);
            while(deleteItem.size()>0){
                deleteItem = driver.findElements(deleteItemLocator);
                if(driver.findElements(deleteItemLocator).size() > 0){
                    deleteItem.get(1).click();
                }
                deleteItem = driver.findElements(deleteItemLocator);
            }
        }
        return this;
    }
    public boolean isCartEmpty(){
        driver.navigate().refresh();
        productCarts = driver.findElements(productCartsLocator);
        return productCarts.isEmpty();
    }

}

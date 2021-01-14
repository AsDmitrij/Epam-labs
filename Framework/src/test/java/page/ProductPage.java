package page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.ConfigPCCreator;
import test.ConfigPCTests;
import wait.CustomWaits;

public class ProductPage extends AbstractPage {

    private static final String PRODUCT_PAGE_URL = "https://www.dns-shop.ru/product/5fbc44bb7b2a3332/64-smartfon-samsung-galaxy-m21-64-gb-cernyj/";
    private static final String SECOND_PRODUCT_PAGE_URL = "https://www.dns-shop.ru/product/01806bf519863332/63-smartfon-xiaomi-redmi-note-8-128-gb-belyj/";

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

    @FindBy(xpath = "//span=[@class='wishlist-button-placeholder wishlist-button-placeholder_icon']")
    private WebElement favoritesCheckBox;

    @FindBy(xpath = "//i[@class='wishlist__icon-add']")
    private WebElement favoritesCheckBoxClick;

    @FindBy(xpath = "//h1[@class='page-title price-item-title']")
    private WebElement nameOfItem;

    @FindBy(xpath = "//span[@class='wishlist-link__lbl']")
    private WebElement toFavorites;

    @FindBy(xpath = "//span[@class ='compare-link__lbl']")
    private WebElement toMatch;

    @FindBy(xpath = "//a[@class='add-item-to-configuration-link']")
    private WebElement addProductToConfigurator;

    private final static By nameOfItemLocator = By.xpath("//h1[@class='page-title price-item-title']");
    private final static By toConfiguratorLocator = By.xpath("//a[@class='add-item-to-configuration-link']");
    private final static By toMatchLocator = By.xpath("//span[@class ='compare-link__lbl']");
    private final static By checkboxLocator = By.xpath("//span[@class='ui-checkbox__box']");
    private final static By addedToFavoritesLocator = By.xpath("//i[@class='wishlist__icon-add wishlist__icon-add_added']");
    private final static By viewCartLocator = By.xpath("//button[@class='button-ui button-ui_brand buy-btn btn-cart button-ui_buy-card button-ui_passive']");
    private final static By viewPriceLocator = By.xpath("//span[@class='product-card-price__current product-card-price__current_active']");
    private final static By goToCartLocator = By.xpath("//a[@class='cart-modal__button button-ui button-ui_brand']");
    private final static By compareCheckBoxLocator = By.xpath("//span[@class='ui-checkbox__box']");


    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductPage openPage() {
        driver.navigate().to(PRODUCT_PAGE_URL);
        return this;
    }

    public ProductPage openSecondPage() {
        driver.navigate().to(SECOND_PRODUCT_PAGE_URL);
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public ProductPage addProductToCompare() {
        CustomWaits.waitForPageLoaded(driver);
        CustomWaits.checkClickable(compareCheckBoxLocator, driver);
        compareCheckBox.click();
        return this;
    }

    public double getPriceOfProduct() {
        CustomWaits.checkPresence(viewPriceLocator, driver);
        String priceOfAddedProductInString = priceOfChosenProduct.getText();
        return Double.parseDouble(priceOfAddedProductInString.replaceAll("[^0-9]", ""));
    }

    public String getNameOfProduct() {
        CustomWaits.checkPresence(viewPriceLocator, driver);
        return nameOfItem.getText();
    }

    public ProductPage putNameOfProductToList() {
        CustomWaits.checkPresence(nameOfItemLocator, driver);
        ConfigPCTests.nameOfPCConfigures.add(nameOfItem.getText());
        return this;
    }

    public ProductPage putProductToCart() {
        CustomWaits.checkPresence(viewCartLocator, driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(addToCartPassive).build().perform();
        addToCartActive.click();
        return this;
    }

    public ProductPage addToFavorites() {
        CustomWaits.checkClickable(checkboxLocator, driver);
        if (driver.findElements(addedToFavoritesLocator).size() == 0) {
            favoritesCheckBoxClick.click();
        }
        return this;
    }

    public PCConfigPage addProductToConfigurator() {
        CustomWaits.checkClickable(toConfiguratorLocator, driver);
        addProductToConfigurator.click();
        return new PCConfigPage(driver);
    }

    public String getFavoritesStatusText() {
        return favoritesCheckBox.getText();
    }

    public FavoritesPage goToFavorites() {
        toFavorites.click();
        return new FavoritesPage(driver);
    }

    public CartPage goToCart() {
        CustomWaits.checkPresence(goToCartLocator, driver);
        viewCart.click();
        return new CartPage(driver);
    }

    public MatchPage goToMatch() {
        CustomWaits.checkClickable(toMatchLocator, driver);
        toMatch.click();
        return new MatchPage(driver);
    }
}

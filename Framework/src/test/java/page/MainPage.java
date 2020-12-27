package page;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.util.List;

public class MainPage extends AbstractPage{
    private static final String MAIN_PAGE_URL = "https://www.dns-shop.ru/";
    @FindBy(xpath = "//div[@class='header__login']")
    private WebElement headerWithLoginOfUser;

    @FindBy(xpath = "//button[@class='button-ui button-ui_white header__login_button']")
    private WebElement mainPageLoginButton;

    @FindBy(xpath = "//div[@class='base-button-container base-button-container_blue']")
    private WebElement loginWithPasswordButton;

    @FindBy(xpath = "//input[@class='base-ui-input-row__input base-ui-input-row__input_with-icon' and @type='text']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@class='base-ui-button base-ui-button_brand base-ui-button_big-flexible-width']")
    private WebElement enterToAccount;

    @FindBy(xpath = "//input[@class='ui-input-search__input ui-input-search__input_presearch']")
    private List<WebElement> searchField;

    @FindBy(xpath = "//div[2]/span[@class='ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch' and 2]")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@class='header-profile__username']")
    private WebElement profileUsername;

    @FindBy(xpath = "//ul[@class='settings__menu']/li[1]/a[@class='header-top-menu__common-link header-top-menu__common-link_child' and 1]")
    private WebElement profileSetting;

    @FindBy(xpath = "//a[@class='homepage-auth__links-profile ui-link ui-link_blue']")
    private WebElement accountLink;

    @FindBy(xpath = "//a[@class='ui-link cart-link']")
    private WebElement cartLink;

    @FindBy(xpath = "//a[@class='btn btn-additional']")
    private WebElement choiceRegion;

    @FindBy(xpath = "//a[@class='homepage-auth__link-rsu ui-link ui-link_blue']")
    private WebElement configPC;

    private static final By configPCLocator = By.xpath("//a[@class='homepage-auth__link-rsu ui-link ui-link_blue']");

    private static final By loginWithPasswordLocator = By.xpath("//div[@class='base-button-container base-button-container_blue']");

    private static final By emailFieldLocator = By.xpath("//input[@class='base-ui-input-row__input base-ui-input-row__input_with-icon' and @type='text']");

    private static final By linkToAccountLocator = By.xpath("//a[@class='homepage-auth__links-profile ui-link ui-link_blue']");

    private static final By cartLinkLocator = By.xpath("//a[@class='ui-link cart-link']");

    private static final By mainLoginLocator = By.xpath("//button[@class='button-ui button-ui_white header__login_button']");

    private static final By headerUserNameLocator = By.xpath("//a[@class='header-profile__username']");
    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public MainPage openPage(){
        driver.get(MAIN_PAGE_URL);
        return  this;
    }
    public MainPage login(User user){
        CustomWaits.checkClickable(mainLoginLocator,driver);
        mainPageLoginButton.click();
        CustomWaits.checkPresence(loginWithPasswordLocator, driver);
        loginWithPasswordButton.click();
        CustomWaits.checkPresence(emailFieldLocator,driver);
        emailField.sendKeys(user.getUserMail());
        passwordField.sendKeys(user.getPassword());
        enterToAccount.click();
        return  this;
    }
    public SearchPage search(String findProduct){
        searchField.get(1).sendKeys(findProduct);
        searchButton.click();
        return new SearchPage(driver);
    }
    public String getUserWelcomeName(){
        CustomWaits.waitForPageLoaded(driver);
        Actions builder = new Actions(driver);
        builder.clickAndHold(headerWithLoginOfUser).click(headerWithLoginOfUser).build().perform();
        CustomWaits.checkPresence(headerUserNameLocator,driver);
        String getUserName = profileUsername.getText();
        return getUserName;
    }
    public CartPage goToCart() {
        CustomWaits.checkClickable(cartLinkLocator,driver);
        cartLink.click();
        return new CartPage(driver);
    }
    public AccountPage goToAccountPage(){
        CustomWaits.checkClickable(linkToAccountLocator,driver);
        accountLink.click();
        return new AccountPage(driver);
    }
    public PCConfigPage goToConfigPC(){
        CustomWaits.checkClickable(configPCLocator, driver);
        configPC.click();
        return new PCConfigPage(driver);
    }
    public ProductPage goToProductPage(){
        return new ProductPage(driver);
    }



}

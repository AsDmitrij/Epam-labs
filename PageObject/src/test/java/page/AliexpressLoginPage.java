package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.CommonConditions;

public class AliexpressLoginPage {
    private static final String LOGIN_PAGE_URL = "https://login.aliexpress.ru/";
    protected static final String USER_MAIL="simplecloudforonetime@yandex.by";
    protected static final String USER_PASSWORD="testPassword";

    private WebDriver driver;
    @FindBy(xpath = "//input[@id='fm-login-id']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='fm-login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button")
    private WebElement enterToAccount;

    public AliexpressLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public AliexpressLoginPage openPage(){
        driver.get(LOGIN_PAGE_URL);
        return  this;
    }
    public AliexpressLoginPage login(String userName, String password ){
        loginField.sendKeys(USER_MAIL);
        passwordField.sendKeys(USER_PASSWORD);
        enterToAccount.click();
        return this;
    }
    public AliexpressMainPage goToMainPage(){
        return new AliexpressMainPage(driver);
    }
}

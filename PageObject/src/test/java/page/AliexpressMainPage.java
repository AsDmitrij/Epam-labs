package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.CommonConditions;

public class AliexpressMainPage {
    private static final String MAIN_PAGE_URL = "https://aliexpress.ru/";
    private WebDriver driver;
    @FindBy(xpath = "//b[@class='welcome-name']")
    private WebElement userNameField;

    @FindBy(xpath = "//span[@class='user-account-port']")
    private WebElement hiddenButton;

    @FindBy(xpath = "//input[@id='search-key']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@class='search-button']")
    private WebElement searchButton;

    private static By viewWelcomeName = By.xpath("//b[@class='welcome-name']");

    public AliexpressMainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public AliexpressMainPage openPage(){
        driver.get(MAIN_PAGE_URL);
        return  this;
    }
    public String getUserWelcomeName(){
        driver.get(MAIN_PAGE_URL);
        CommonConditions.AddCookies(driver);
        driver.get(MAIN_PAGE_URL);
        Actions action = new Actions(driver);
        action.moveToElement(hiddenButton);
        action.perform();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.presenceOfElementLocated(viewWelcomeName));
        String userWelcomeName = userNameField.getText();
        return userWelcomeName;
    }
}

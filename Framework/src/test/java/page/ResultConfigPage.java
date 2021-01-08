package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.ConvertUtils;
import wait.CustomWaits;

import java.util.ArrayList;
import java.util.List;

public class ResultConfigPage extends AbstractPage {

    private static final String CONFIGURATION_PAGE_URL = "https://www.dns-shop.ru/configurator/";
    private static String xPathOfItemName = "//div[@class='product-name']/a[1]";

    @FindBy(xpath = "//div[@class='header__login']")
    private WebElement headerWithLoginOfUser;

    @FindBy(xpath = "//a[@href='/logout/']")
    private WebElement logout;

    @FindBy(xpath = "//div[@class='product-name']/a[1]")
    private List<WebElement> listOfConfigureItems;

    private static By nameOfItemLocator = By.xpath("//div[@class='product-name']/a[1]");

    public ResultConfigPage openPage() {
        driver.navigate().to(CONFIGURATION_PAGE_URL);
        return this;
    }

    public ResultConfigPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ResultConfigPage openPage(String pageURL) {
        driver.get(pageURL);
        return this;
    }

    public List<String> getListWithNameOfItems() {
        CustomWaits.checkClickable(nameOfItemLocator, driver);
        listOfConfigureItems = driver.findElements(By.xpath(xPathOfItemName));
        List<String> listWithNameOfItems = ConvertUtils.convertWebElementToListString(listOfConfigureItems);
        return listWithNameOfItems;
    }

    public ResultConfigPage logout() {
        Actions builder = new Actions(driver);
        builder.clickAndHold(headerWithLoginOfUser).click(headerWithLoginOfUser).build().perform();
        logout.click();
        return this;
    }

}

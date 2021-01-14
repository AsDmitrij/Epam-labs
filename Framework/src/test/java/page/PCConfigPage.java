package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.util.ArrayList;
import java.util.List;

public class PCConfigPage extends AbstractPage {
    private static final String CONFIG_PAGE_URL = "https://www.dns-shop.ru/configurator/";


    @FindBy(xpath = "//a[@class='close-link']")
    private WebElement closeInstruction;

    @FindBy(xpath = "//p[@class='configurator-items-count']/span[1]")
    private WebElement itemsCount;

    @FindBy(xpath = "//div[1]/div[@class='product-name' and 2]/p[1]/a[1]")
    private WebElement nameOfAddingItem;

    @FindBy(xpath = "//div[@class='save-configuration configurator-buttons__button']/a[1]")
    private WebElement saveConfiguration;

    @FindBy(xpath = " //*[@id='modal-for-save-configuration']/div/div/div[2]/div/a")
    private WebElement endConfiguration;

    @FindBy(xpath = "//button[@class='btn btn-default btn-add']")
    private List<WebElement> addAccessories;

    @FindBy(xpath = "//input[@class='ui-input-search__input ui-input-search__input_presearch']")
    private List<WebElement> searchField;

    @FindBy(xpath = "//span[@class='ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch']")
    private List<WebElement> searchButton;

    @FindBy(xpath = "//a[@class='add-item-to-configuration-link']")
    private WebElement addProductToConfigurator;

    private static final By endConfigurationLocator = By.xpath("//*[@id='modal-for-save-configuration']/div/div/div[2]/div/a");

    private static final String xPathOfAddButton = "//button[@class='btn btn-default btn-add']";

    private static final String xPathOfFindContainer = "/div[@class='configurator-catalog expanded' and 2]/div[@class='catalog-category-container' and 2]/div[@class='category-groups' and 4]";

    public PCConfigPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        CustomWaits.waitForPageLoaded(driver);
    }

    public PCConfigPage openPage() {
        driver.get(CONFIG_PAGE_URL);
        return this;
    }

    public ProductPage configSearch(String findProduct) {

        ArrayList<String> tabs= new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        searchField.get(1).sendKeys(findProduct);
        searchButton.get(1).click();
        return new ProductPage(driver);
    }

    public ResultConfigPage saveConfiguration(){
        ArrayList<String> tabs= new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        driver.navigate().refresh();
        saveConfiguration.click();
        CustomWaits.checkClickable(endConfigurationLocator, driver);
        endConfiguration.click();
        return new ResultConfigPage(driver);
    }

}

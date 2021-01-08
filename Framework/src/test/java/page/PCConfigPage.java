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
    private static final String MAIN_PAGE_URL = "https://www.dns-shop.ru/configurator/";


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

    private static final By endConfigurationLocator = By.xpath("//*[@id='modal-for-save-configuration']/div/div/div[2]/div/a");

    private static final String xPathOfAddButton = "//button[@class='btn btn-default btn-add']";

    private static final String xPathOfFindContainer = "/div[@class='configurator-catalog expanded' and 2]/div[@class='catalog-category-container' and 2]/div[@class='category-groups' and 4]";

    public PCConfigPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public PCConfigPage openPage() {
        driver.get(MAIN_PAGE_URL);
        return this;
    }

    public List<String> getConfigureOfPC() {
        closeInstruction.click();
        List<String> namesOfAddedItems = new ArrayList<String>();
        for (int i = 0; i < EXPECTED_NUMBER_OF_CONFIGURE; i++) {
            addAccessories = driver.findElements(By.xpath(xPathOfAddButton));
            addAccessories.get(0).click();
            By findContainerLocator = By.xpath("//div[" + (i + 1) + "]" + xPathOfFindContainer);
            CustomWaits.checkPresenceForConfigurator(findContainerLocator, driver);
            addAccessories = driver.findElements(By.xpath(xPathOfAddButton));
            namesOfAddedItems.add(nameOfAddingItem.getText());
            addAccessories.get(0).click();
            CustomWaits.checkText(itemsCount, String.valueOf(i + 1), driver);
        }

        return namesOfAddedItems;
    }

    public ResultConfigPage endConfiguration() {
        saveConfiguration.click();
        CustomWaits.checkClickable(endConfigurationLocator, driver);
        endConfiguration.click();
        return new ResultConfigPage(driver);
    }

}

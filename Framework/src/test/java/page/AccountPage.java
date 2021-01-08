package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.util.List;

public class AccountPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[@class='addition-menu__link' and 1]")
    private List<WebElement> subMenuItems;

    private static By subMenuItemsLocator = By.xpath("//a[@class='addition-menu__link' and 1]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UserConfigPCPage goToUserConfigPage() {
        CustomWaits.checkClickable(subMenuItemsLocator, driver);
        subMenuItems.get(0).click();
        return new UserConfigPCPage(driver);
    }

}

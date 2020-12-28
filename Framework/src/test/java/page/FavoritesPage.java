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

public class FavoritesPage extends AbstractPage{
    private static final String FAVORITES_PAGE_URL = "https://www.dns-shop.ru/profile/wishlist";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='name']")
    private List<WebElement> listOfAddedItems;

    private final static By addedItemLocator= By.xpath("//div[@class='name']");

    public FavoritesPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public FavoritesPage openPage(){
        driver.navigate().to(FAVORITES_PAGE_URL);
        logger.info("Page with favorite items opened");
        return this;
    }
    public String getNameOfAddedToFavoritesItem(){
        CustomWaits.checkPresence(addedItemLocator, driver);
        return listOfAddedItems.get(1).getText();
    }


}

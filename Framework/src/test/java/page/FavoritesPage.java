package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.util.List;

public class FavoritesPage extends AbstractPage{
    private static final String FAVORITES_PAGE_URL = "https://www.dns-shop.ru/profile/wishlist";

    @FindBy(xpath = "//div[@class='name']")
    private List<WebElement> listOfAddedItems;

    private final static By addedItemLocator= By.xpath("//div[@class='name']");

    public FavoritesPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public FavoritesPage openPage(){
        driver.navigate().to(FAVORITES_PAGE_URL);
        return this;
    }
    public String getNameOfAddedToFavoritesItem(){
        CustomWaits.checkPresence(addedItemLocator, driver);
        return listOfAddedItems.get(1).getText();
    }


}

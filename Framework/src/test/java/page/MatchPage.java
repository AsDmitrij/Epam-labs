package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.ConvertUtils;
import wait.CustomWaits;

import java.util.List;

public class MatchPage extends AbstractPage{
    private static final String MATCH_PAGE_URL = "https://www.dns-shop.ru/compare/";

    @FindBy(xpath = "//div[@class='products-slider__product-name']")
    private List<WebElement> listOfMatchingProducts;

    private final static By matchProductLocator = By.xpath("//div[@class='products-slider__product-name']");

    public MatchPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public MatchPage openPage(){
        driver.navigate().to(MATCH_PAGE_URL);
        return this;
    }

    public List<String> getNameListOfMatchingProducts(){
        CustomWaits.waitForPageLoaded(driver);
        CustomWaits.checkClickable(matchProductLocator,driver);
        return ConvertUtils.convertWebElementToListString(listOfMatchingProducts);
    }
}

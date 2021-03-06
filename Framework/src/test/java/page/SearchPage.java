package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ScrollUtils;
import wait.CustomWaits;

import java.util.List;

public class SearchPage extends AbstractPage{

    private static final String SEARCH_PAGE_URL = "https://www.dns-shop.ru/search/";
    @FindBy(xpath = "//div[@class='product-info__title-link' and 1]")
    private List<WebElement> findProductList;

    @FindBy(xpath = "//div[@class='product-min-price__current']")
    private List<WebElement> findProductPriceList;

    @FindBy(xpath = "//div[2]/input[@class='ui-input-small__input ui-input-small__input_list' and 1]")
    private WebElement maxPriceField;

    @FindBy(xpath = "//button[@class='button-ui button-ui_brand left-filters__button']")
    private WebElement applyFilter;

    @FindBy(xpath = " //div[@class='top-filters__picked']/span[@class='picked-filter' and 2]/button[@class='picked-filter__reset-btn' and 1]/span[1]")
    private WebElement labelFilters;

    private static final By allPrisesLocator= By.xpath("//div[@class='product-min-price__current']");
    private static final By maxPriceLocator= By.xpath("//div[2]/input[@class='ui-input-small__input ui-input-small__input_list' and 1]");
    private static final By filterLocator= By.xpath(" //div[@class='product-info__title-link']");
    private static final By labelFilterLocator= By.xpath("//button[@class='picked-filter__reset-btn' and 1]");


    public SearchPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    @Override
    protected SearchPage openPage() {
        driver.navigate().to(SEARCH_PAGE_URL);
        return this;
    }
    public List<WebElement> getListOfFindProducts(){
        return driver.findElements(filterLocator);
    }
    public SearchPage setMaximalPrice(int maximalPrice){
        CustomWaits.waitForPageLoaded(driver);
        ScrollUtils.scrollPage(driver);
        CustomWaits.checkPresence(maxPriceLocator, driver);
        maxPriceField.sendKeys(String.valueOf(maximalPrice));
        return this;
    }
    public SearchPage applyFilter(){
        applyFilter.click();
        return this;
    }
    public double getMaxFilterPrice(){
        CustomWaits.checkPresence(labelFilterLocator,driver);
        return Double.parseDouble(labelFilters.getText().replaceAll("[^0-9]", ""));
    }
    public List<WebElement> getListOfPriceItems(){
        CustomWaits.checkPresence(allPrisesLocator,driver);
        return driver.findElements(allPrisesLocator);
    }

}

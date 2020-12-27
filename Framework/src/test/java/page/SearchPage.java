package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.util.List;

public class SearchPage extends AbstractPage{

    @FindBy(xpath = "//div[@class='product-info__title-link' and 1]")
    private List<WebElement> findProductList;

    @FindBy(xpath = "//div[@class='product-min-price__current' and 2]")
    private List<WebElement> findProductPriceList;

    @FindBy(xpath = "//div[2]/input[@class='ui-input-small__input ui-input-small__input_list' and 1]")
    private WebElement maxPriceField;

    @FindBy(xpath = "//button[@class='button-ui button-ui_brand left-filters__button']")
    private WebElement applyFilter;

    @FindBy(xpath = " //div[@class='top-filters__picked']/span[@class='picked-filter' and 2]/button[@class='picked-filter__reset-btn' and 1]/span[1]")
    private WebElement labelFilters;

    private static By maxPriceLocator= By.xpath("//div[2]/input[@class='ui-input-small__input ui-input-small__input_list' and 1]");
    private static By filterLocator= By.xpath(" //div[@class='top-filters__picked']/span[@class='picked-filter' and 2]/button[@class='picked-filter__reset-btn' and 1]/span[1]");
    private static By labelFilterLocator= By.xpath("//div[@class='top-filters__picked']/span[@class='picked-filter' and 2]/button[@class='picked-filter__reset-btn' and 1]/span[1]");


    public SearchPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    protected SearchPage openPage() {
        return null;
    }
    public List<WebElement> getListOfFindProducts(){
        List<WebElement> selectProductForBuy = findProductList;
        return selectProductForBuy;
    }
    public SearchPage setMaximalPrice(){
        double firstPriceOfProduct = Double.parseDouble( findProductPriceList.get(0).getText().replaceAll("[^0-9]", ""));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("scroll(0, 500);");
        CustomWaits.checkPresence(maxPriceLocator,driver);
        maxPriceField.sendKeys(String.valueOf(firstPriceOfProduct));
        applyFilter.click();
        return this;
    }
    public double getMaxFilterPrice(){
        CustomWaits.checkPresence(labelFilterLocator,driver);
        double maxFilterPrice = Double.parseDouble(labelFilters.getText().replaceAll("[^0-9]", ""));
        return maxFilterPrice;
    }
    public List<WebElement> getListOfPriceItems(){
        CustomWaits.checkPresence(filterLocator,driver);
        List<WebElement> productPriceList = findProductPriceList;
        return productPriceList;
    }

}

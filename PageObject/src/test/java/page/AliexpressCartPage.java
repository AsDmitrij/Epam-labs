package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.CommonConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AliexpressCartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String startTotalPriceInCart;

    @FindBy(xpath = "//span[@class='next-checkbox-label']")
    private WebElement selectPurchasedItem;

    @FindBy(xpath = "//div[1]/dl[@class='charges-totle ' and 1]/dd[1]")
    private WebElement productPriceInCart;

    @FindBy(xpath = "//div[2]/dl[@class='charges-totle ' and 1]/dd[1]")
    private WebElement priceOfDeliveringProductInCart;

    private static By selectedItemCheckBox = By.xpath("//span[@class='next-checkbox-label']");
    private static By totalSelectedItemPrice = By.xpath("//div[2]/dl[@class='charges-totle ' and 1]/dd[1]");

    public AliexpressCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectPurchasedItem(){
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.presenceOfElementLocated(selectedItemCheckBox));
        selectPurchasedItem.click();
        startTotalPriceInCart = productPriceInCart.getText();
    }
    public double getPriceOfProductInCart(){
        String priceOfProductInCartInString =productPriceInCart.getText();
        double priceOfProductInCart = CommonConditions.convertPriceToDouble(priceOfProductInCartInString);
        return priceOfProductInCart;
    }
    public double getDeliveringPriceOfProductInCart(){
        startTotalPriceInCart = productPriceInCart.getText();
        new WebDriverWait(driver,15)
               .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(totalSelectedItemPrice, startTotalPriceInCart)));
        String deliveringPriceOfProductInCartInString =priceOfDeliveringProductInCart.getText();
        double deliveringPriceOfProductInCart = CommonConditions.convertPriceToDouble(deliveringPriceOfProductInCartInString);
        return deliveringPriceOfProductInCart;
    }
    private WebElement checkPresence(WebElement element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }
    private Boolean checkTextToBePresentInElement(WebElement element) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated((By) element,startTotalPriceInCart)));
    }
}

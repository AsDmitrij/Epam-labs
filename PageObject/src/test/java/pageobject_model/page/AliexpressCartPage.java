package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public AliexpressCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectPurchasedItem(){
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='next-checkbox-label']")));
        selectPurchasedItem.click();
        startTotalPriceInCart = productPriceInCart.getText();
    }
    public double getPriceOfProductInCart(){
        String priceOfProductInCartInString =productPriceInCart.getText();
        double priceOfProductInCart = convertPriceToDouble(priceOfProductInCartInString);
        return priceOfProductInCart;
    }
    public double getDeliveringPriceOfProductInCart(){
        startTotalPriceInCart = productPriceInCart.getText();
        new WebDriverWait(driver,15)
               .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[2]/dl[@class='charges-totle ' and 1]/dd[1]"),startTotalPriceInCart)));
        String deliveringPriceOfProductInCartInString =priceOfDeliveringProductInCart.getText();
        double deliveringPriceOfProductInCart = convertPriceToDouble(deliveringPriceOfProductInCartInString);
        return deliveringPriceOfProductInCart;
    }
    private WebElement checkPresence(WebElement element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }
    private Boolean checkTextToBePresentInElement(WebElement element) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated((By) element,startTotalPriceInCart)));
    }
    private double convertPriceToDouble(String price)
    {
        Pattern pattern=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pattern.matcher(price);
        int start = 0;
        double result =0;
        while (matcher.find(start)) {
            String value = price.substring(matcher.start(), matcher.end());
            value =value.replace(',', '.');
            result = Double.parseDouble(value);
            start = matcher.end();
        }
        return result;
    }
}

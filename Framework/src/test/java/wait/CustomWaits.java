package wait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CustomWaits {

    private static final int WAIT_TIMEOUT_SECONDS = 10;

    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted(){
        return new ExpectedCondition<Boolean>(){
            public  Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor)
                        driver).executeScript("return (window.jQuery != null && (jQuery.active === 0)); ");

            }
        };
    }

    public static void waitForPageLoaded(WebDriver driver){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(jQueryAJAXsCompleted());
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(waitForLoad());
    }

    public static ExpectedCondition<Boolean> waitForLoad(){
        return driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
    }

    public static WebElement checkPresence(By element, WebDriver driver) {
        return new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static WebElement checkPresenceForConfigurator(By element, WebDriver driver) {
        return new WebDriverWait(driver,2*WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static WebElement checkClickable(By element, WebDriver driver) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean checkText(WebElement element,String waitText, WebDriver driver) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.textToBePresentInElement(element,waitText));
    }
    public static boolean waitUntilAttributeNotPresent(List<WebElement> elements, Integer indexOfElement, WebDriver driver) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(elements.get(indexOfElement),"disabled")));
    }

}

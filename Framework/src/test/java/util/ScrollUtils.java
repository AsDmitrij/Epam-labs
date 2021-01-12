package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import page.SearchPage;
import wait.CustomWaits;

public class ScrollUtils {

    public static void scrollPage(WebDriver driver){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("scroll(0, 500);");
    }
}

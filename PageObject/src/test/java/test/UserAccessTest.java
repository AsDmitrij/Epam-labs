package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.AliexpressLoginPage;
import org.testng.annotations.Test;

import java.util.Set;

public class UserAccessTest {
    protected static final String USER_MAIL="simplecloudforonetime@yandex.by";
    protected static final String USER_PASSWORD="testPassword";
    protected static final String USER_NAME="simplecloudforonetime";
    private WebDriver driver;
    @Before
    public void setBrowserOptions()
    {
        if(CommonConditions.isWindows()){
            System.setProperty("webdriver.chrome.driver","D:/webdriver/chromedriver.exe" );
        }
        else if(CommonConditions.isMac()){
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver  = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void userCanLoginTest() throws InterruptedException {
        String userWelcomeName = new AliexpressLoginPage(driver).
                openPage()
                .login(USER_MAIL,USER_PASSWORD)
                .goToMainPage()
                .getUserWelcomeName();
        Set<Cookie> allcookies = driver.manage().getCookies();
        Assert.assertEquals(USER_NAME,userWelcomeName);
    }
    @After
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
}

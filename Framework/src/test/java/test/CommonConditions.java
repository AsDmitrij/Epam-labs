package test;

import driver.DriverSingleton;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import page.MainPage;
import service.UserCreator;
import util.TestListener;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    public static final int EXPECTED_MATCH_PERCENT = 20;
    public static final String NAME_OF_FIND_PRODUCT = "Samsung";
    @BeforeTest(alwaysRun = true)
    public void setBrowserOptions()
    {
        driver = DriverSingleton.getDriver();

    }
    @BeforeMethod
    public void loginUser(){
        User testUser = UserCreator.withCredentialsFromProperty();
        MainPage mainPage = new MainPage(driver).
                openPage()
                .login(testUser);

    }
    @AfterMethod
    public void logout(){
        MainPage mainPage = new MainPage(driver).logout();
    }
    @AfterTest(alwaysRun = true)
    public void closeBrowser(){
        DriverSingleton.closeDriver();
    }

}

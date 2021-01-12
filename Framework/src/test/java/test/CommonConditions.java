package test;

import driver.DriverSingleton;
import model.User;
import org.openqa.selenium.By;
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
    public static final Integer MAX_PRICE_OF_FIND_PRODUCT = 17000;
   // @BeforeClass(alwaysRun = true)
    public void setBrowserOptions()
    {
        driver = DriverSingleton.getDriver();
    }
    @BeforeClass
    public void loginUser(){
        driver = DriverSingleton.getDriver();
        User testUser = UserCreator.withCredentialsFromProperty();
        new MainPage(driver).
                openPage()
                .login(testUser);
    }
    @AfterClass
    public void logout(){
        new MainPage(driver)
                .logout();
        DriverSingleton.closeDriver();
    }
  //  @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        DriverSingleton.closeDriver();
    }

}

package page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage
{
    protected WebDriver driver;

    protected abstract AbstractPage openPage();
    protected final int EXPECTED_NUMBER_OF_CONFIGURE = 4;

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }
}

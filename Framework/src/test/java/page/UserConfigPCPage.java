package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.CustomWaits;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public class UserConfigPCPage extends AbstractPage{
    private static final String USER_CONFIG_PC_PAGE_URL = "https://www.dns-shop.ru/userProfile/profile/rsu-configurations/";

    @FindBy(xpath = "//button[@class='btn btn-default btn-copy']")
    private List<WebElement> copyLinkToConfiguration;

    @FindBy(xpath = "//div[@class='dns-row goto-link']/a[1]")
    private List<WebElement> linkToSavedConfiguration;

    @FindBy(xpath = "//a[@class='pseudo-link']")
    private List<WebElement> actionMenuOfConfiguration;

    @FindBy(xpath = "//div[@class='dns-row rsu-user-configuration']")
    private List<WebElement> listOfUserConfigurations;

    @FindBy(xpath = "//a[@data-role='select-all']")
    private WebElement selectAllConfigurations;

    private static final By configurationCardLocator = By.xpath("//div[@class='dns-row rsu-user-configuration']");
    private static final By actionMenuOfConfigurationSelectAllLocator = By.xpath("//a[@data-role='select-all']");
    private static final By actionMenuOfConfigurationLocator = By.xpath("//div[@class='container userpage']");
    private static final By deleteOfConfigurationLocator = By.xpath("//a[@class='pseudo-link']");

    public UserConfigPCPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public UserConfigPCPage openPage(){
        driver.get(USER_CONFIG_PC_PAGE_URL);
        return this;
    }
    public String copyConfigurationLink() throws IOException, UnsupportedFlavorException {
        CustomWaits.checkPresence(actionMenuOfConfigurationLocator,driver);
        copyLinkToConfiguration.get(1).click();
        String linkToConfiguration = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        return linkToConfiguration;
    }
    public ResultConfigPage goToResultConfigPage(){
        linkToSavedConfiguration.get(0).click();
        return new ResultConfigPage(driver);
    }
    public UserConfigPCPage selectAllConfig(){
        CustomWaits.checkClickable(actionMenuOfConfigurationSelectAllLocator,driver);
        selectAllConfigurations.click();
        return this;
    }
    public UserConfigPCPage deleteSelectedConfig(){
        CustomWaits.waitUntilAttributeNotPresent(actionMenuOfConfiguration,2,driver);
        if(listOfUserConfigurations.size()>0){
            CustomWaits.waitUntilAttributeNotPresent(actionMenuOfConfiguration,2,driver);
            CustomWaits.checkClickable(deleteOfConfigurationLocator,driver);
            actionMenuOfConfiguration.get(2).click();
        }
        return this;
    }
    public boolean isCurrentLengthUserConfigListEmpty(){

        if(actionMenuOfConfiguration.get(2).getAttribute("disabled")==null){
            driver.navigate().refresh();
        }
        CustomWaits.waitForPageLoaded(driver);
        listOfUserConfigurations = driver.findElements(configurationCardLocator);
        return listOfUserConfigurations.isEmpty();
    }
}
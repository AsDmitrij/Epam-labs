package test;

import org.junit.Assert;
import org.testng.annotations.Test;
import page.MainPage;
import page.PCConfigPage;
import page.ResultConfigPage;
import page.UserConfigPCPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ConfigPCTests extends CommonConditions{
    @Test(priority=1)
    public void testAccordanceOfSelectedAndSavedConfiguration(){
        PCConfigPage configPage = new MainPage(driver)
                .openPage()
                .goToConfigPC();
        List<String> composedConfigureList = configPage
                .getConfigureOfPC();
        List<String> finalConfigureList = configPage
                .endConfiguration()
                .getListWithNameOfItems();
        assertTrue(composedConfigureList.equals(finalConfigureList));
    }
    @Test(priority=2)
    public void testAccordanceOfSavedAndSentConfiguration() throws IOException, UnsupportedFlavorException {
        UserConfigPCPage userConfigPCPage = new MainPage(driver)
                .openPage()
                .goToAccountPage()
                .goToUserConfigPage();
        String configurationLink = userConfigPCPage.openPage().copyConfigurationLink();
        List<String> listOfSavedItemsConfiguration = userConfigPCPage
                .goToResultConfigPage()
                .logout()
                .getListWithNameOfItems();
        List<String> listOfSavedItemsConfigurationByPublicLink = new ResultConfigPage(driver)
                .openPage(configurationLink)
                .getListWithNameOfItems();
        Assert.assertEquals(listOfSavedItemsConfiguration,listOfSavedItemsConfigurationByPublicLink);
    }
    @Test(priority = 3)
    public void testDeletingAllPCConfig(){
        boolean isEmptyConfigList = new MainPage(driver)
                .openPage()
                .goToUserConfigPC()
                .selectAllConfig()
                .deleteSelectedConfig()
                .isCurrentLengthUserConfigListEmpty();;
        assertTrue(isEmptyConfigList);
    }
}

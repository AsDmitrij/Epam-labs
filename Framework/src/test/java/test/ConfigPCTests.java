package test;

import model.ComputerComponents;
import org.junit.Assert;
import org.testng.annotations.Test;
import page.*;
import service.ConfigPCCreator;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.testng.Assert.assertTrue;

public class ConfigPCTests extends CommonConditions{
    public static final List<String>  nameOfPCConfigures = new ArrayList<>();
    @Test(priority=1)
    public void testAccordanceOfSelectedAndSavedConfiguration(){
        ComputerComponents computerComponents = ConfigPCCreator.withCredentialsFromProperty();
        List<String> composedConfigureList = new MainPage(driver)
                .openPage()
                .configSearch(computerComponents.getCpuName())
                .putNameOfProductToList()
                .addProductToConfigurator()
                .configSearch(computerComponents.getMotherboardName())
                .putNameOfProductToList()
                .addProductToConfigurator()
                .configSearch(computerComponents.getComputerCaseName())
                .putNameOfProductToList()
                .addProductToConfigurator()
                .configSearch(computerComponents.getGpuName())
                .putNameOfProductToList()
                .addProductToConfigurator()
                .configSearch(computerComponents.getRamName())
                .putNameOfProductToList()
                .addProductToConfigurator()
                .saveConfiguration()
                .getListWithNameOfItems();
        assertTrue(nameOfPCConfigures.equals(composedConfigureList));
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

package test;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import page.MainPage;
import org.testng.annotations.Test;
import page.SearchPage;
import util.ConvertUtils;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

public class SearchTests  extends CommonConditions{
    @Test
    public void testSearch(){
        String findProduct = "Samsung";
        List<WebElement> searchResults = new MainPage(driver).openPage().search(findProduct).getListOfFindProducts();
        assertThat(searchResults).extracting(WebElement::getText).allMatch(text -> text.contains(findProduct));
    }
    @Test
    public void testSortingSearch(){
        String findProduct = "Samsung";
        SearchPage searchResults = new MainPage(driver)
                .openPage()
                .search(findProduct)
                .setMaximalPrice();
        double maxFilterPrice = searchResults.getMaxFilterPrice();
        List<Double> prisesOfSortedProducts = ConvertUtils.convertWebElementPriceListToListDouble(searchResults.getListOfPriceItems());
        Assert.assertTrue(maxFilterPrice <= Collections.max(prisesOfSortedProducts));
    }
}

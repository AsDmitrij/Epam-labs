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
        List<WebElement> searchResults = new MainPage(driver)
                .openPage()
                .search(NAME_OF_FIND_PRODUCT)
                .getListOfFindProducts();
        assertThat(searchResults).extracting(WebElement::getText).allMatch(text -> text.contains(NAME_OF_FIND_PRODUCT));
    }
    @Test
    public void testSortingSearch() {
        SearchPage searchResults = new MainPage(driver)
                .openPage()
                .search(NAME_OF_FIND_PRODUCT)
                .setMaximalPrice(MAX_PRICE_OF_FIND_PRODUCT)
                .applyFilter();
        List<Double> prisesOfSortedProducts = ConvertUtils.convertWebElementPriceListToListDouble(searchResults.getListOfPriceItems());
        Assert.assertTrue(MAX_PRICE_OF_FIND_PRODUCT <= Collections.max(prisesOfSortedProducts));
    }
}

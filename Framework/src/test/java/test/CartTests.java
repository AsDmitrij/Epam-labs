package test;


import org.junit.Assert;
import org.openqa.selenium.WebElement;
import page.CartPage;
import page.MainPage;
import page.ProductPage;
import org.testng.annotations.Test;
import util.ConvertUtils;
import util.SearchUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class CartTests extends CommonConditions{


    @Test(priority = 1)
    public void testPriceOfProductInCart() {
        ProductPage productPage = new MainPage(driver)
                .openPage()
                .goToProductPage()
                .openPage();
        double costOfAddedProduct = productPage
                .getPriceOfProduct();
        double cartProductPrice = productPage
                .putProductToCart()
                .goToCart()
                .getPriceOfProductInCart();
        assertEquals(cartProductPrice,costOfAddedProduct);
    }

    @Test(priority = 2)
    public void testRelevanceOfOfferedAccessoriesInCart() {
        CartPage cartPage = new MainPage(driver)
                .openPage()
                .goToCart();
        String productName = cartPage.getNameOfProductInCart();
        List<WebElement> listOfOfferedAccessories = cartPage.getListOfOfferedAccessories();
        List<String> listOfNamesOfOfferedAccessories = ConvertUtils.convertWebElementToListString(listOfOfferedAccessories);
        int matchPercent = SearchUtils.getMatchPercentage(productName,listOfNamesOfOfferedAccessories);
        assertThat(EXPECTED_MATCH_PERCENT, lessThan(matchPercent));
    }
    @Test(priority = 3)
    public void testDeletingAllItemsInCart(){
        boolean isCartEmpty = new MainPage(driver)
                .openPage()
                .goToCart()
                .deleteAllItems()
                .isCartEmpty();
        assertTrue(isCartEmpty);
    }
    @Test(priority = 4)
    public void testAddToFavorites() {
        ProductPage productPage = new MainPage(driver)
                .openPage()
                .goToProductPage()
                .openPage()
                .addToFavorites();
        String nameOfCurrentProduct = productPage.getNameOfProduct();
        String nameOfItemInFavorites = productPage.goToFavorites().getNameOfAddedToFavoritesItem();
        Assert.assertTrue(nameOfCurrentProduct.contains(nameOfItemInFavorites));
    }
    @Test(priority = 5)
    public void testAddToCompare(){
        ProductPage productPage = new MainPage(driver)
                .openPage()
                .goToProductPage()
                .openPage();
        List<String> listOfAddedProductNames = new ArrayList<String>();
        listOfAddedProductNames.add(productPage.addProductToCompare().getNameOfProduct());
        listOfAddedProductNames.add(productPage.openSecondPage().addProductToCompare().getNameOfProduct());
        List<String> listOfNameProductsInMatch= productPage.goToMatch().getNameListOfMatchingProducts();
        assertEquals(listOfAddedProductNames,listOfNameProductsInMatch);
    }
}

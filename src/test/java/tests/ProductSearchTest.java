package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;

public class ProductSearchTest extends BaseTest {

    //Search for a product and verify results
    @Test
    public void verifySearchResultsDisplayed() {
        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct("MacBook");

        Assert.assertTrue(sp.areProductsDisplayed(),"Products are not displayed for valid search");
    }

    //Navigate to category and verify products
    @Test
    public void verifyCategoryNavigation() {
        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.navigateToMacCategory();

        Assert.assertTrue(sp.areProductsDisplayed(),"Products not displayed in selected category");
    }

    //Open product detail page and verify name & price
    @Test
    public void verifyProductDetailsPage() {
        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct("MacBook");

        ProductPage pp=sp.clickFirstProduct();

        Assert.assertTrue(pp.isProductNameDisplayed(),"Product name is not displayed");
        Assert.assertTrue(pp.isProductPriceDisplayed(),"Product price is not displayed");
    }

    //Search for non-existing product
    @Test
    public void verifyNoResultsMessage() {
        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct("InvalidProductXYZ123");

        Assert.assertTrue(sp.isNoResultMessageDisplayed(),"No results message not displayed for invalid search");
    }
}
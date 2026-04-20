package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;

public class CartTest extends BaseTest {

    //Add product and verify
    @Test
    public void verifyAddToCart() {
        String productName="MacBook";

        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct(productName);
        ProductPage pp=sp.clickProductByName(productName);

        pp.addToCart();

        Assert.assertTrue(pp.isProductAddedToCart(),"Product not added to cart");

        CartPage cp=hp.goToCart();

        Assert.assertTrue(cp.getProductName().toLowerCase().contains(productName.toLowerCase()),"Product name mismatch in cart");
    }

    //Update quantity and verify total
    @Test
    public void verifyUpdateQuantity() {
        String productName="iPhone";

        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct(productName);
        ProductPage pp=sp.clickProductByName(productName);

        pp.addToCart();
        CartPage cp=hp.goToCart();

        String oldTotal=cp.getTotalPrice();
        cp.updateQuantity("2");

        String newTotal=cp.getTotalPrice();

        Assert.assertNotEquals(oldTotal,newTotal,"Total price did not update after quantity change");
    }

    //Remove product and verify empty cart
    @Test
    public void verifyRemoveProduct() {
        String productName="Sony VAIO";

        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct(productName);
        
        ProductPage pp=sp.clickProductByName(productName);
        pp.addToCart();
        
        CartPage cp=hp.goToCart();
        cp.removeProduct();

        Assert.assertTrue(cp.isCartEmpty(),"Cart is not empty after removing product");
    }

    //Verify cart icon count
    @Test
    public void verifyCartIconCount() {
    	String productName="MacBook";
    	
        HomePage hp=new HomePage(getDriver());
        String before=hp.getCartCountText();
        SearchPage sp=hp.searchProduct(productName);
        
        ProductPage pp=sp.clickProductByName(productName);
        pp.addToCart();

        String after = hp.getCartCountText();

        Assert.assertNotEquals(before, after,"Cart count did not update");
    }
}
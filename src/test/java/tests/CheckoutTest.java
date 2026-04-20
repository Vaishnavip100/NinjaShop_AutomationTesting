package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.ConfigReader;

public class CheckoutTest extends BaseTest {
    ConfigReader config=new ConfigReader();

    //Proceed to checkout and confirm the order
    @Test
    public void verifyCompleteCheckoutFlow() {
        String productName="HP LP3065";

        HomePage hp=new HomePage(getDriver());

        LoginPage lp=hp.clickLogin();
        lp.login(config.getEmail(), config.getPassword());

        Assert.assertTrue(lp.isLoginSuccessful(), "Login failed");

        SearchPage sp=hp.searchProduct(productName);
        ProductPage pp=sp.clickProductByName(productName);
        pp.addToCart();

        CartPage cp = hp.goToCart();
        cp.waitForCartToLoad();
        cp.setQuantityToOne();
        
        Assert.assertFalse(cp.isStockWarningDisplayed(),"Product is out of stock, cannot proceed");

        cp.setQuantityToOne();
        
        CheckoutPage ch=cp.proceedToCheckout();
	     if (ch.isBillingFormDisplayed()) {
	         ch.fillBillingDetails("Vaishnavi","Perumalla","Street 1","Vijayawada","520001");
	     } else {
	         ch.proceedBilling();
	     }
	
	     ch.proceedDeliveryDetails();
	     ch.proceedShippingMethod();
	     ch.proceedPayment();
	     ch.confirmOrder();
	
	     String successMsg=ch.getSuccessMessage();
	     Assert.assertTrue(successMsg != null && successMsg.toLowerCase().contains("your order has been placed"),"Order confirmation failed. Message: " + successMsg);
    }

    //Checkout without login and verify
    @Test
    public void verifyCheckoutWithoutLogin() {
        String productName="HP LP3065";

        HomePage hp=new HomePage(getDriver());
        SearchPage sp=hp.searchProduct(productName);
        ProductPage pp=sp.clickProductByName(productName);
        pp.addToCart();

        CartPage cp = hp.goToCart();
        cp.waitForCartToLoad();
        cp.setQuantityToOne();
        cp.clickCheckoutOnly();

        CheckoutPage ch = new CheckoutPage(getDriver());

        Assert.assertTrue(ch.isLoginSectionDisplayed(),"Login section not displayed for guest user");

        Assert.assertTrue(getDriver().getCurrentUrl().contains("route=account/login")|| getDriver().getCurrentUrl().contains("route=checkout/checkout"),"User not redirected properly to login/checkout page");
    }
}
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.RegisterPage;
import pages.SearchPage;
import utils.ConfigReader;

public class FormValidationsTest extends BaseTest{
	ConfigReader config=new ConfigReader();
	
	//Verify registration with empty fields
	@Test
	public void verifyEmptyRegistrationForm() {
	    HomePage hp=new HomePage(getDriver());
	    RegisterPage rp=hp.clickRegister();

	    rp.clickContinue();

	    Assert.assertTrue(rp.isFirstNameErrorDisplayed(), "First name error missing");
	    Assert.assertTrue(rp.isLastNameErrorDisplayed(), "Last name error missing");
	    Assert.assertTrue(rp.isEmailErrorDisplayed(), "Email error missing");
	    Assert.assertTrue(rp.isTelephoneErrorDisplayed(), "Telephone error missing");
	    Assert.assertTrue(rp.isPasswordErrorDisplayed(), "Password error missing");
	}
	
	//Verify registration with invalid email
	@Test
	public void verifyInvalidEmailFormat() {
	    HomePage hp=new HomePage(getDriver());
	    RegisterPage rp=hp.clickRegister();

	    rp.enterFirstName("Vaishnavi");
	    rp.enterLastName("Perumalla");
	    rp.enterEmail("invalid-email");
	    rp.enterTelephone("9876543210");
	    rp.enterPassword("Password123");

	    rp.clickContinue();

	    String validationMsg=rp.getEmailValidationMessage();

	    Assert.assertTrue(validationMsg.toLowerCase().contains("@"),"Invalid email validation not shown");
	}
	
	//Verify checkout with missing delivery address fields
	@Test
	public void verifyCheckoutMissingAddressFields() {
	    String productName="HP LP3065";
	    HomePage hp=new HomePage(getDriver());
	    
	    LoginPage lp = hp.clickLogin();
	    lp.login(config.getEmail(), config.getPassword());

	    SearchPage sp=hp.searchProduct(productName);
	    ProductPage pp=sp.clickProductByName(productName);
	    pp.addToCart();

	    CartPage cp=hp.goToCart();
	    cp.waitForCartToLoad();
	    cp.setQuantityToOne();

	    CheckoutPage ch = cp.proceedToCheckout();
	    ch.selectNewAddress();
	    ch.clickBillingContinue();

	    Assert.assertTrue(ch.isFirstNameErrorDisplayed(), "First name error missing");
	    Assert.assertTrue(ch.isAddressErrorDisplayed(), "Address error missing");
	    Assert.assertTrue(ch.isCityErrorDisplayed(), "City error missing");
	    Assert.assertTrue(ch.isPostcodeErrorDisplayed(), "Postcode error missing");
	}
}

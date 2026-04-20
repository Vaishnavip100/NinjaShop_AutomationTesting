package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ConfigReader;
import utils.ExcelUtil;

public class AuthTest extends BaseTest {
    ConfigReader config=new ConfigReader();

    @DataProvider(name="LoginData")
    public Object[][] getTest() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.xlsx";
        return ExcelUtil.getData(path, "Sheet1");
    }

    @Test(dataProvider="LoginData")
    public void login(String email, String password, String expected) {
        HomePage hp=new HomePage(getDriver());
        LoginPage lp=hp.clickLogin();

        lp.login(email, password);

        if (expected.equalsIgnoreCase("success")) {
            Assert.assertTrue(lp.isLoginSuccessful(),"Login should succeed but failed for: " + email);
        } 
        else if (expected.equalsIgnoreCase("failure")) {
            Assert.assertTrue(lp.isLoginFailed(),"Login should fail but passed for: " + email);
        } 
        else {
            Assert.fail("Invalid expected value in test data");
        }
    }

    //Verify logout
    @Test
    public void verifyLogout() {
        HomePage hp=new HomePage(getDriver());
        LoginPage lp=hp.clickLogin();
        lp.login(config.getEmail(), config.getPassword());

        Assert.assertTrue(lp.isLoginSuccessful(),"Precondition failed: Login was not successful");

        lp.clickLogoutBtn();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("route=account/logout"),"User not redirected to logout success page");
        Assert.assertTrue(getDriver().getPageSource().contains("Account Logout"),"Logout success page not displayed");
    }
    
    public String generateEmail() {
        return "user" + System.currentTimeMillis() + "@test.com";
    }

    //Registration
    @Test
    public void verifyRegistration() {
        HomePage hp=new HomePage(getDriver());
        RegisterPage rp=hp.clickRegister();

        String email=generateEmail();

        rp.register("Vaishnavi","Perumalla",email,"9876543210","Password123");

        Assert.assertTrue(rp.isRegistrationSuccessful(),"Registration failed");
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {
    private By emailInput=By.id("input-email");
    private By passwordInput=By.id("input-password");
    private By loginButton=By.xpath("//input[@value='Login']");
    private By warningMessage=By.xpath("//div[contains(@class,'alert-danger')]");
    private By myAccountHeader=By.xpath("//h2[text()='My Account']");
    private By logoutLink=By.linkText("Logout");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginButton);
    }

    public void clickLogoutBtn() {
        click(logoutLink);
    }

    public boolean isLoginSuccessful() {
        return isDisplayed(myAccountHeader);
    }

    public boolean isLoginFailed() {
        return isDisplayed(warningMessage);
    }

    public String getErrorMessage() {
        return getText(warningMessage);
    }
}
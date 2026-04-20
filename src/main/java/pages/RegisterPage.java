package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class RegisterPage extends BasePage {
    private By firstName=By.id("input-firstname");
    private By lastName=By.id("input-lastname");
    private By email=By.id("input-email");
    private By telephone=By.id("input-telephone");
    private By password=By.id("input-password");
    private By confirmPassword=By.id("input-confirm");
    private By privacyPolicy=By.name("agree");
    private By continueBtn=By.xpath("//input[@value='Continue']");
    private By successMsg=By.xpath("//h1[text()='Your Account Has Been Created!']");
    
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(String fName, String lName, String mail,String phone, String pwd) {
        type(firstName, fName);
        type(lastName, lName);
        type(email, mail);
        type(telephone, phone);
        type(password, pwd);
        type(confirmPassword, pwd);
    }

    public void acceptPrivacyPolicy() {
        click(privacyPolicy);
    }

    public void register(String fName, String lName, String mail,String phone, String pwd) {
        fillRegistrationForm(fName, lName, mail, phone, pwd);
        acceptPrivacyPolicy();
        clickContinue();
    }

    public boolean isRegistrationSuccessful() {
        return isDisplayed(successMsg);
    }
    
    

    public boolean isFirstNameErrorDisplayed() {
        return isDisplayed(By.xpath("//div[contains(text(),'First Name')]"));
    }

    public boolean isEmailErrorDisplayed() {
        return isDisplayed(By.xpath("//div[contains(text(),'E-Mail Address')]"));
    }
       

    //Error messages
    private By firstNameError=By.xpath("//div[contains(text(),'First Name must')]");
    private By lastNameError=By.xpath("//div[contains(text(),'Last Name must')]");
    private By emailError=By.xpath("//div[contains(text(),'valid') or contains(text(),'E-Mail')]");
    private By telephoneError=By.xpath("//div[contains(text(),'Telephone must')]");
    private By passwordError=By.xpath("//div[contains(text(),'Password must')]");

    public void enterFirstName(String value) {
        type(firstName, value);
    }

    public void enterLastName(String value) {
        type(lastName, value);
    }

    public void enterEmail(String value) {
        type(email, value);
    }

    public void enterTelephone(String value) {
        type(telephone, value);
    }

    public void enterPassword(String value) {
        type(password, value);
        type(confirmPassword, value);
    }

    public void clickContinue() {
        click(continueBtn);
    }
    
    public String getEmailValidationMessage() {
        return driver.findElement(email).getAttribute("validationMessage");
    }

    //Validations
    public boolean isLastNameErrorDisplayed() {
        return isDisplayed(lastNameError);
    }

    public boolean isTelephoneErrorDisplayed() {
        return isDisplayed(telephoneError);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(passwordError);
    }
}
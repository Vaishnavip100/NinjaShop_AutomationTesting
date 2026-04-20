package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;

public class CheckoutPage extends BasePage {
    private By billingContinue=By.id("button-payment-address");
    
    private By firstName=By.id("input-payment-firstname");
    private By lastName=By.id("input-payment-lastname");
    private By address=By.id("input-payment-address-1");
    private By city=By.id("input-payment-city");
    private By postcode=By.id("input-payment-postcode");
    private By deliveryContinue=By.id("button-shipping-address");
    
    private By successMsg=By.xpath("//h1[contains(text(),'Your order has been placed')]");
    private By shippingContinue=By.id("button-shipping-method");
    private By agreeCheckbox=By.name("agree");
    private By paymentContinue=By.id("button-payment-method");
    private By confirmOrder=By.id("button-confirm");
    private By loginHeader=By.xpath("//h2[text()='Returning Customer']");

    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

   
    public boolean isBillingFormDisplayed() {
        return isDisplayed(firstName);
    }

    public void fillBillingDetails(String fName, String lName, String addr, String cityName, String zip) {
        type(firstName, fName);
        type(lastName, lName);
        type(address, addr);
        type(city, cityName);
        type(postcode, zip);
        Select country=new Select(driver.findElement(By.id("input-payment-country")));
        country.selectByVisibleText("India");
        waitForVisibility(By.id("input-payment-zone"));

        Select region=new Select(driver.findElement(By.id("input-payment-zone")));
        region.selectByVisibleText("Telangana");
        click(billingContinue);
    }

    public void proceedBilling() {
        click(billingContinue);
    }

    public void proceedDelivery() {
        click(deliveryContinue);
    }
    
    public String getSuccessMessage() {
        return getText(successMsg);
    }

    public void proceedShippingMethod() {
        click(shippingContinue);
    }

    public void acceptTerms() {
        click(agreeCheckbox);
    }

    public void confirmOrder() {
        By confirmBtn=By.id("button-confirm");
        waitForVisibility(confirmBtn);
        scrollIntoView(confirmBtn);

        try {
            click(confirmBtn);
        } catch (Exception e) {
            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(confirmBtn));
        }
    }
    
    public void proceedDeliveryDetails() {
        waitForClickable(deliveryContinue);
        click(deliveryContinue);
    }
    
    public void proceedPayment() {
        By agreeCheckbox=By.name("agree");
        By paymentContinue=By.id("button-payment-method");
        waitForVisibility(agreeCheckbox);
        scrollIntoView(agreeCheckbox);
        click(agreeCheckbox);
        scrollIntoView(paymentContinue);

        try {
            click(paymentContinue);
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(paymentContinue));
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-confirm")));
    }
    
    public boolean isLoginSectionDisplayed() {
        return isDisplayed(loginHeader);
    }
    
    public void selectNewAddress() {
        click(By.xpath("//input[@value='new']"));
    }

    public void clickBillingContinue() {
        click(By.id("button-payment-address"));
    }

    public boolean isFirstNameErrorDisplayed() {
        return isDisplayed(By.xpath("//div[contains(text(),'First Name')]"));
    }
    
    //Form Validations
    private By addressError=By.xpath("//div[contains(text(),'Address 1')]");
    private By cityError=By.xpath("//div[contains(text(),'City')]");
    private By postcodeError=By.xpath("//div[contains(text(),'Postcode')]");

    public boolean isAddressErrorDisplayed() {
        return isDisplayed(addressError);
    }

    public boolean isCityErrorDisplayed() {
        return isDisplayed(cityError);
    }

    public boolean isPostcodeErrorDisplayed() {
        return isDisplayed(postcodeError);
    }
    
    public void waitForCheckoutPage() {
        waitForVisibility(By.id("button-payment-address"));
    }
}
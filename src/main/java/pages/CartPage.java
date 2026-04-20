package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class CartPage extends BasePage {
    private By cartTable=By.xpath("//div[@class='table-responsive']//table");
    private By productName=By.xpath("//div[@class='table-responsive']//td[@class='text-left']/a");
    private By totalPrice=By.xpath("//div[@class='table-responsive']//tbody/tr/td[6]");
    private By quantityInput=By.xpath("//input[contains(@name,'quantity')]");
    private By updateBtn=By.xpath("//button[@data-original-title='Update']");
    private By removeBtn=By.xpath("//button[@data-original-title='Remove']");
    private By emptyCartMsg=By.xpath("//div[@id='content']//p[contains(text(),'Your shopping cart is empty')]");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public void waitForCartToLoad() {
        waitForVisibility(cartTable);
    }

    public String getProductName() {
        return waitForVisibility(productName).getText();
    }

    public String getTotalPrice() {
        return waitForVisibility(totalPrice).getText();
    }
    
    public void updateQuantity(String qty) {
        type(quantityInput, qty);
        click(updateBtn);
        waitForCartToLoad();
    }

    public void removeProduct() {
        click(removeBtn);
        wait.until(driver -> driver.findElements(By.xpath("//div[@class='table-responsive']//tbody/tr")).size() == 0);
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.xpath("//div[@class='table-responsive']//tbody/tr")).isEmpty();
    }
    
    //For Checkout
    private By checkoutBtn=By.linkText("Checkout");
    private By stockWarning=By.xpath("//div[contains(@class,'alert-danger')]");


    public CheckoutPage proceedToCheckout() {
        click(checkoutBtn);
        try {
            wait.until(driver -> driver.getCurrentUrl().contains("checkout/checkout"));
            return new CheckoutPage(driver);
        } catch (Exception e) {
            throw new RuntimeException("Checkout blocked - product may be out of stock");
        }
    }

    public void clickCheckoutOnly() {
        waitForClickable(checkoutBtn).click();
    }

    public boolean isStockWarningDisplayed() {
        return isDisplayed(stockWarning);
    }
    

    public void clearCart() {
        while (driver.findElements(removeBtn).size() > 0) {
            click(removeBtn);
            wait.until(driver -> driver.findElements(removeBtn).size() == 0 || driver.findElements(removeBtn).size() < 5);
        }
    }
    
    public void setQuantityToOne() {
        type(quantityInput, "1");
        click(updateBtn);
        waitForCartToLoad();
    }
}
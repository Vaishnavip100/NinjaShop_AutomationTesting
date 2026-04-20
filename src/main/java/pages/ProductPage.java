package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class ProductPage extends BasePage {
    private By productName=By.xpath("//h1");
    private By productPrice=By.xpath("//ul[@class='list-unstyled']//h2");
    private By addToCartBtn=By.id("button-cart");
    private By successAlert=By.xpath("//div[contains(@class,'alert-success')]");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductNameDisplayed() {
        return isDisplayed(productName);
    }

    public boolean isProductPriceDisplayed() {
        return isDisplayed(productPrice);
    }
    
    public void addToCart() {
        click(addToCartBtn);
        waitForVisibility(successAlert);
    }

    public boolean isProductAddedToCart() {
        return isDisplayed(successAlert);
    }
}
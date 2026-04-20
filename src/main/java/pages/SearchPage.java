package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import base.BasePage;

public class SearchPage extends BasePage {
    private By productTitles=By.xpath("//div[@class='product-thumb']//h4/a");
    private By noResultMsg=By.xpath("//p[contains(text(),'There is no product')]");
    
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean areProductsDisplayed() {
        return driver.findElements(productTitles).size() > 0;
    }

    public ProductPage clickFirstProduct() {
        waitForVisibility(productTitles);
        driver.findElements(productTitles).get(0).click();
        return new ProductPage(driver);
    }
    
    public void addProductToCart(String productName) {
        By products=By.xpath("//div[@class='product-thumb']");
        waitForVisibility(products);
        for (WebElement product : driver.findElements(products)) {
            String name=product.findElement(By.xpath(".//h4/a")).getText();
            if (name.equalsIgnoreCase(productName)) {
                product.findElement(By.xpath(".//button[contains(@onclick,'cart')]")).click();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }
    
    public ProductPage clickProductByName(String productName) {
        waitForVisibility(productTitles);
        for (WebElement element : driver.findElements(productTitles)) {
            if (element.getText().equalsIgnoreCase(productName)) {
                element.click();
                return new ProductPage(driver);
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public boolean isNoResultMessageDisplayed() {
        return isDisplayed(noResultMsg);
    }
}
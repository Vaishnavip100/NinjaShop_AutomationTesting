package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class HomePage extends BasePage{
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	private By myAccountBtn=By.xpath("//a[@title='My Account']");
	private By loginBtn=By.xpath("//a[normalize-space()='Login']");
	private By searchBox=By.name("search");
	private By searchBtn=By.cssSelector("button[type='button'][class*='btn-default']");
	
	public LoginPage clickLogin() {
        click(myAccountBtn);
        click(loginBtn);
        return new LoginPage(driver);
    }
	
	public RegisterPage clickRegister() {
	    click(myAccountBtn);
	    click(By.linkText("Register"));
	    return new RegisterPage(driver);
	}
	
	public SearchPage searchProduct(String productName) {
	    WebElement box=waitForVisibility(searchBox);
	    box.clear();
	    box.sendKeys(productName);
	    click(searchBtn);
	    return new SearchPage(driver);
	}
	
	private By productTitles=By.xpath("//div[@class='product-thumb']//h4/a");
	private By desktopsMenu = By.linkText("Desktops");
	private By macOption = By.linkText("Mac (1)");
	private By cartBtn = By.id("cart-total");
	
	public ProductPage clickFirstProduct() {
	    waitForVisibility(productTitles);
	    driver.findElements(productTitles).get(0).click();
	    return new ProductPage(driver);
	}
	
	public SearchPage navigateToMacCategory() {
	    click(desktopsMenu);
	    click(macOption);
	    return new SearchPage(driver);
	}
	
	public String getCartCountText() {
	    return getText(cartBtn);
	}
	
	public ProductPage clickProductByName(String productName) {
	    waitForVisibility(productTitles);
	    for (WebElement element : driver.findElements(productTitles)) {
	        String name=element.getText().trim().toLowerCase();
	        if (name.contains(productName.toLowerCase())) {
	            element.click();
	            return new ProductPage(driver);
	        }
	    }
	    throw new RuntimeException("Product not found: " + productName);
	}
	
	private By checkoutBtn = By.xpath("//a[contains(@href,'route=checkout/checkout')]");
	public CheckoutPage proceedToCheckout() {
	    waitForClickable(checkoutBtn);
	    scrollIntoView(checkoutBtn);
	    try {
	        click(checkoutBtn);
	    } catch (Exception e) {
	        JavascriptExecutor js=(JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", driver.findElement(checkoutBtn));
	    }
	    wait.until(driver -> driver.getCurrentUrl().contains("checkout/checkout"));
	    return new CheckoutPage(driver);
	}
	
	private By viewCart = By.xpath("//p//a[contains(@href,'route=checkout/cart')]");
	public CartPage goToCart() {
	    click(cartBtn);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(viewCart));
	    click(viewCart);
	    return new CartPage(driver);
	}
}

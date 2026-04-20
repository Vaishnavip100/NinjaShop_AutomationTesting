package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import utils.ConfigReader;

public class BaseTest {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    ConfigReader config=new ConfigReader();

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public void handleAdsIfPresent() {
        try {
            if (getDriver().getCurrentUrl().contains("google_vignette")) {
                getDriver().navigate().back();
            }
        } catch (Exception e) {
        }
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        tlDriver.set(new ChromeDriver());

        getDriver().manage().window().maximize();
        getDriver().get(config.getBaseUrl());

        handleAdsIfPresent();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}
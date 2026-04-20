package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath=System.getProperty("user.dir") + "/screenshots/";
        String filePath=folderPath + testName + "_" + timestamp + ".png";

        try {
            new File(folderPath).mkdirs();
            File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
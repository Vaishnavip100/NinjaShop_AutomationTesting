package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent==null) {
            String reportPath=System.getProperty("user.dir") + "/reports/ExtentReport.html";
            new File(System.getProperty("user.dir") + "/reports/").mkdirs();

            ExtentSparkReporter spark=new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent=new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}
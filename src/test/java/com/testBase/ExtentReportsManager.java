package com.testBase;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportsManager implements ITestListener {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private static final String REPORTS_DIR = System.getProperty("user.dir") + "/extent-report/";
    private static final String SCREENSHOTS_DIR = REPORTS_DIR + "/screenshots/";

    private WebDriver driver; // Local driver instance for capturing screenshots
    public ExtentReportsManager() {
    	ExtentReportsManager extentReportsManager = new ExtentReportsManager(driver);

    }
    public ExtentReportsManager(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onStart(ITestContext context) {
        // Create directories for reports and screenshots
        new File(REPORTS_DIR).mkdirs();
        new File(SCREENSHOTS_DIR).mkdirs();

        // Timestamped report file
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = REPORTS_DIR + "ExtentReport_" + timeStamp + ".html";

        // Configure ExtentSparkReporter
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle(" eCommerce Website Automated Test Report");
        sparkReporter.config().setReportName("Functional Testing Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info
        extent.setSystemInfo("Tester Name", "Mithu Roy");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(getTestCategory(result));
        test.log(Status.PASS, "Test Case PASSED: " + result.getName());
        attachScreenshotToReport(test, result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(getTestCategory(result));
        test.log(Status.FAIL, "Test Case FAILED: " + result.getName());
        test.log(Status.FAIL, "Reason: " + result.getThrowable());
        attachScreenshotToReport(test, result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(getTestCategory(result));
        test.log(Status.SKIP, "Test Case SKIPPED: " + result.getName());
        test.log(Status.SKIP, "Reason: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String getTestCategory(ITestResult result) {
        return result.getMethod().getTestClass().getName();
    }

    private void attachScreenshotToReport(ExtentTest test, String testName) {
        String screenshotPath = captureScreenshot(testName);
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath, "Test Screenshot");
        }
    }

    private String captureScreenshot(String testName) {
        if (driver == null) {
            System.out.println("Driver is not initialized. Screenshot cannot be captured.");
            return null;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = SCREENSHOTS_DIR + testName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(screenshotPath);
            Files.copy(srcFile.toPath(), destFile.toPath());
            return screenshotPath; // Return path for ExtentReports
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}

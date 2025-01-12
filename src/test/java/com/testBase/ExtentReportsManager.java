package com.testBase;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Set the correct path for the report
        String reportPath = System.getProperty("user.dir") + "/extent-report/extentReport.html";

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automated eCommerce Website");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information to the report
        extent.setSystemInfo("Computer Name", "Onsite");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Mithu Roy");
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser Name", "Chrome, Edge");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.log(Status.PASS, "Test Case PASSED is: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.log(Status.FAIL, "Test Case FAILED is: " + result.getName());
        test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.log(Status.SKIP, "Test Case SKIPPED is: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Generate the report
    }
}

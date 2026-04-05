package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	private static final Logger log = LogManager.getLogger(ExtentReportManager.class);

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		log.info("===== Test Execution Started =====");

		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report-" + timeStamp + ".html";

			log.debug("Generated report name: {}", repName);

			sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

			sparkReporter.config().setDocumentTitle("Automation Report");
			sparkReporter.config().setReportName("Functional Testing");
			sparkReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			extent.setSystemInfo("Application", "CloudBerry Store");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");

			String os = testContext.getCurrentXmlTest().getParameter("os");
			String browser = testContext.getCurrentXmlTest().getParameter("browser");

			log.debug("OS: {}, Browser: {}", os, browser);

			extent.setSystemInfo("Operating System", os);
			extent.setSystemInfo("Browser", browser);

			List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if (!includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includedGroups.toString());
				log.debug("Included Groups: {}", includedGroups);
			}

		} catch (Exception e) {
			log.error("Exception in onStart(): {}", e.getMessage(), e);
		}
	}

	public void onTestSuccess(ITestResult result) {

		log.info("Test PASSED: {}", result.getName());

		try {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());

			test.log(Status.PASS, result.getName() + " got successfully executed");

		} catch (Exception e) {
			log.error("Error while logging success for test '{}': {}", result.getName(), e.getMessage(), e);
		}
	}

	public void onTestFailure(ITestResult result) {

		log.error("Test FAILED: {}", result.getName());

		try {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());

			test.log(Status.FAIL, result.getName() + " got failed");

			if (result.getThrowable() != null) {
				test.log(Status.INFO, result.getThrowable().getMessage());
				log.debug("Failure reason: {}", result.getThrowable().getMessage());
			}

			// Retry Analyzer Logging
			if (result.getMethod().getRetryAnalyzer(result) != null) {
				log.warn("RetryAnalyzer is applied for test: {}", result.getName());
			}

			// Screenshot capture
			try {
				String imgPath = new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
				log.debug("Screenshot captured at: {}", imgPath);
			} catch (Exception e) {
				log.error("Failed to capture screenshot for '{}': {}", result.getName(), e.getMessage(), e);
			}

		} catch (Exception e) {
			log.error("Exception in onTestFailure(): {}", e.getMessage(), e);
		}
	}

	public void onTestSkipped(ITestResult result) {

		log.warn("Test SKIPPED: {}", result.getName());

		try {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());

			test.log(Status.SKIP, result.getName() + " got skipped");

			if (result.getThrowable() != null) {
				test.log(Status.INFO, result.getThrowable().getMessage());
				log.debug("Skip reason: {}", result.getThrowable().getMessage());
			}

		} catch (Exception e) {
			log.error("Exception in onTestSkipped(): {}", e.getMessage(), e);
		}
	}

	public void onFinish(ITestContext testContext) {

		log.info("===== Test Execution Finished =====");

		try {
			extent.flush();
			log.debug("Extent report flushed successfully.");

			String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
			File extentReport = new File(pathOfExtentReport);

			log.debug("Opening report at: {}", pathOfExtentReport);

			Desktop.getDesktop().browse(extentReport.toURI());

		} catch (IOException e) {
			log.error("Failed to open extent report: {}", e.getMessage(), e);
		} catch (Exception e) {
			log.error("Unexpected error in onFinish(): {}", e.getMessage(), e);
		}
	}
}
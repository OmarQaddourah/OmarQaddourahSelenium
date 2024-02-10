package core.features;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static core.base.BaseTestClass.webDriver;

public class ListenerClass implements ITestListener, IAnnotationTransformer {

    private final Logger LOGGER = LogManager.getLogger(ListenerClass.class);
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
    private final String formattedDateTime = now.format(formatter);

    private String captureScreenshot() {
        String screenshotName = "screenshot_" + formattedDateTime + ".png";
        Path screenshotPath = Paths.get("screenshots/", screenshotName);

        try {
            File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return screenshotPath.toString();
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-reports/report_" + formattedDateTime + ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        LOGGER.info("Test method '" + result.getName() + "' started.");

        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
        LOGGER.info("Test method '" + result.getName() + "' passed.");
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable());

        if (webDriver != null && result.getThrowable() != null) {
            String screenshotPath = captureScreenshot();
            extentTest.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        LOGGER.error("Test method '" + result.getName() + "' failed.");
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Exception: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    @Override
    public void transform(final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor, final Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}

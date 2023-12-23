package core.features;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LoggingListener implements ITestListener {

    private final Logger LOGGER = LogManager.getLogger(LoggingListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        LOGGER.info("Test method '" + result.getName() + "' started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test method '" + result.getName() + "' passed.");
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("Test method '" + result.getName() + "' failed.");
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Exception: " + result.getThrowable());
    }
}

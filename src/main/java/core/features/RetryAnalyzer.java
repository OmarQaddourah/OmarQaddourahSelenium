package core.features;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer, ITestNGListener {

    private static final Logger LOGGER = LogManager.getLogger("Retry.class");
    private static final int MAX_RETRY_COUNT = 3;
    private int retryCount = 1;
    String resultName = null;

    private String getResultStatusName(final int status) {
        if (status == 1) {
            resultName = "SUCCESS";
        }
        if (status == 2) {
            resultName = "FAILURE";
        }
        if (status == 3) {
            resultName = "SKIP";
        }
        return resultName;
    }

    @Override
    public boolean retry(final ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (this.retryCount < MAX_RETRY_COUNT) {
                LOGGER.info("Retrying test " + iTestResult.getName() + " with status " + getResultStatusName(
                        iTestResult.getStatus()) + " for the " + (this.retryCount + 1) + " time(s).");
                this.retryCount++;
                return true;
            }
        }
        return false;
    }
}

package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);

	private int retryCount = 0;
	private static final int maxRetryCount = 2; // Maximum retries

	@Override
	public boolean retry(ITestResult result) {

		log.debug("Entering retry method for test: {}", result.getName());

		try {
			if (retryCount < maxRetryCount) {

				retryCount++;

				log.warn("Retrying test '{}' (Attempt {} of {})", result.getName(), retryCount, maxRetryCount);

				log.debug("Test '{}' failed due to: {}", result.getName(),
						result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error");

				return true;
			} else {
				log.error("Max retry attempts reached for test '{}'. Marking as FAILED.", result.getName());
			}

		} catch (Exception e) {
			log.error("Exception occurred in RetryAnalyzer for test '{}': {}", result.getName(), e.getMessage(), e);
		}

		log.debug("Exiting retry method for test: {}", result.getName());
		return false;
	}
}
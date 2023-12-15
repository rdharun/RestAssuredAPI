package utilities;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class LogUtility {
    private static final Logger logger = LoggerFactory.getLogger(LogUtility.class);

    public static String captureLogs(Throwable throwable) {
        // Use SLF4J to capture logs
        logger.error("Test failed with the following exception:", throwable);
        // Return logs as a string
        return "Captured logs for the failed test. See attached exception for details.";
    }
}

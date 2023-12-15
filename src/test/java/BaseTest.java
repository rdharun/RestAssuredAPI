import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.AllureLogger;
import utilities.LogUtility;
import utilities.PropertyUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseTest {

    private long startTime;

    @BeforeTest
    public void setUp() {
        String baseUrl = PropertyUtils.getProperty("base_url");
        RestAssured.baseURI = baseUrl;
    }

    @AfterTest
    public void tearDown() {
        // Any teardown logic you want to add
    }


    public void logReportDetails(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                // Capture logs for failed tests
                String logs = LogUtility.captureLogs(result.getThrowable());
                AllureLogger.addLogsToAllureReport(logs, result.getThrowable());

                // Add key steps logs for diagnostic capability
                // Example: Allure.addAttachment("Key Steps", "text/plain", "Step 1: Perform action X\nStep 2: Verify result Y");
            }

            // Add execution time to the report
            long executionTime = System.currentTimeMillis() - startTime;
            Allure.addAttachment("Execution Time", "text/plain", "Execution time: " + executionTime + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility method to assert response status code
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but found " + actualStatusCode);
    }

    // Utility method for asserting content type
    public static void assertContentType(Response response, String expectedContentType) {
        String actualContentType = response.getContentType();
        assertTrue(actualContentType.startsWith(expectedContentType),
                "Expected content type to start with " + expectedContentType + " but found " + actualContentType);
    }

    // Utility method for asserting a key-value pair in the payload
    public static void assertPayloadValue(Response response, String jsonPathKey, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPathKey);
        assertEquals(actualValue, expectedValue,
                "Expected payload value for key '" + jsonPathKey + "' to be '" + expectedValue + "', but found '" + actualValue + "'");
    }
}

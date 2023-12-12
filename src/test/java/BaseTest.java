import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.PropertyUtils;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;




public class BaseTest {

    @BeforeTest
    public void setUp() {
        String baseUrl = PropertyUtils.getProperty("base_url");
        RestAssured.baseURI = baseUrl;
    }

    @AfterTest
    public void tearDown() {

    }

    // Utility method to assert response status code
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but found " + response.getStatusCode());
    }

    // Utility method for asserting content type
    public static void assertContentType(Response response, String expectedContentType) {
        assertTrue(response.getContentType().startsWith(expectedContentType),
                "Expected content type to start with " + expectedContentType + " but found " + response.getContentType());
    }

    // Utility method for asserting a key-value pair in the payload
    public static void assertPayloadValue(Response response, String jsonPathKey, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPathKey);
        assertEquals(actualValue, expectedValue,
                "Expected payload value for key '" + jsonPathKey + "' to be '" + expectedValue + "', but found '" + actualValue + "'");
    }
}

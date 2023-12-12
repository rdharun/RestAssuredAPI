import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.PropertyUtils;
import org.testng.Assert;
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

    // Utility method for asserting HTTP status code
    public static void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Unexpected status code");
    }


    // Utility method for asserting HTTP status code, content type, and payload
    public static void assertApiResponse(Response response, int expectedStatusCode, String expectedContentType, String expectedPayloadKey, Object expectedPayloadValue) {
        assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code.");
        assertTrue(response.getContentType().startsWith(expectedContentType), "Unexpected content type.");
        assertEquals(response.jsonPath().get(expectedPayloadKey), expectedPayloadValue, "Unexpected payload value.");
    }


}

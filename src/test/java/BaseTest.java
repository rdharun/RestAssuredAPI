import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.PropertyUtils;
import org.testng.Assert;


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
    protected void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Unexpected status code");
    }


}

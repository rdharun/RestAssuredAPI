import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.PropertyUtils;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        String baseUrl = PropertyUtils.getProperty("base_url");
        RestAssured.baseURI = baseUrl;
    }
}

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;
import static org.testng.Assert.assertEquals;


public class UserLoginTest extends BaseTest {

    @Test
    public void validateUserLogin() {

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("\n" +
                        "{\n" +
                        "   \"email\":\"" + "abc@gmail.com" + "\",\n" +
                        "   \"password\":\"" + "123456" + "\"\n" +
                        "}\n")
                .when()
                .post("/api/auth/login");

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200);

    }
}

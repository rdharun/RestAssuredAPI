import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


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

        assertNotNull(response.jsonPath().get("data.session.access_token"));

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200);

    }
}

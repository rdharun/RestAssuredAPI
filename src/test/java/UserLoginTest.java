import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


public class UserLoginTest extends BaseTest {

    @Test
    public void validateUserLogin() {

        File jsonBody = new File("src/main/resources/userLogin.json");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/api/auth/login");

        assertNotNull(response.jsonPath().get("data.session.access_token"));

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200);

    }
}

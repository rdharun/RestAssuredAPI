import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


public class UserLoginTest extends BaseTest {

    @Test
    public void validateUserLogin() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "abc@gmail.com");
        jsonObject.put("password" , "123456");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .when()
                .post("/api/auth/login");

        assertNotNull(response.jsonPath().get("data.session.access_token"));

        int statusCode = response.getStatusCode();

        assertEquals(statusCode, 200);

    }
}

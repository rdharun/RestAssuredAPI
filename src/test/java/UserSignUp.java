import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;

public class UserSignUp extends BaseTest {


    @Test
    public void validateUserSignUp() {

        String randomPassword = RandomStringUtils.randomAlphanumeric(7);
        String randomEmail = RandomEmailGenerator.generateRandomEmailId();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("\n" +
                        "{\n" +
                        "   \"email\":\"" + randomEmail + "\",\n" +
                        "   \"password\":\"" + randomPassword + "\"\n" +
                        "}\n")
                .when()
                .post("/api/auth/signup");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 201);

    }
}

package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class UserClient {


    public Response createUser(String email, String password) {
        String endpoint = "/api/auth/signup";
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .when()
                .post(endpoint);
    }

    public Response authenticateUser(String email, String password, String accessToken) {
        String endpoint = "/api/auth/login";

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(String.format("{\n" +
                        "    \"email\": \"%s\",\n" +
                        "    \"password\": \"%s\"\n" +
                        "}", email, password))
                .when()
                .post(endpoint);
    }

}

package clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupRequest;
import models.UserSignupResponse;
import org.json.simple.JSONObject;

public class UserClient {

    private ObjectMapper objectMapper;

    public UserClient() {
        this.objectMapper = new ObjectMapper();
    }


    public Response createUser(UserSignupRequest request) throws JsonProcessingException {
        String endpoint = "/api/auth/signup";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(request))
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

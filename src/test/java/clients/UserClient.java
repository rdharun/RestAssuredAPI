package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.LoginRequestBody;
import models.LoginResponseBody;
import models.SignupRequestBody;
import models.SignupResponseBody;
import utilities.EndpointConfig;

public class UserClient {

    public SignupResponseBody signup(String email, String password) {

        String signupEndpoint = EndpointConfig.getEndpoint("auth", "signup");

        SignupRequestBody signupRequestBodyBody = SignupRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestBodyBody)
                .when()
                .post(signupEndpoint);

        SignupResponseBody signupResponseBodyBody = response.as(SignupResponseBody.class);
        signupResponseBodyBody.setStatusCode(response.getStatusCode());

        return signupResponseBodyBody;
    }


    public LoginResponseBody login(String email, String password, String accessToken) {
        String loginEndpoint = EndpointConfig.getEndpoint("auth", "login");

        LoginRequestBody loginRequestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(loginRequestBody)
                .when()
                .post(loginEndpoint);

        LoginResponseBody loginResponseBody = response.as(LoginResponseBody.class);
        loginResponseBody.setStatusCode(response.getStatusCode());

        return loginResponseBody;
    }

}

package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listener.RestAssuredListener;
import models.LoginRequestBody;
import models.LoginResponseBody;
import models.SignupRequestBody;
import models.SignupResponseBody;
import utilities.EndpointConfig;

public class UserClient {

    public SignupResponseBody signup(String email, String password) {

        String signupEndpoint = EndpointConfig.getEndpoint("auth", "signup");

        SignupRequestBody signupRequestBody = SignupRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .body(signupRequestBody)
                .when()
                .post(signupEndpoint);

        SignupResponseBody signupResponseBody = response.as(SignupResponseBody.class);
        signupResponseBody.setStatusCode(response.getStatusCode());

        return signupResponseBody;
    }


    public LoginResponseBody login(String email, String password) {
        String loginEndpoint = EndpointConfig.getEndpoint("auth", "login");

        LoginRequestBody loginRequestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .when()
                .post(loginEndpoint);

        LoginResponseBody loginResponseBody = response.as(LoginResponseBody.class);
        loginResponseBody.setStatusCode(response.getStatusCode());

        return loginResponseBody;
    }

}

package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.LoginRequestBody;
import models.LoginResponseBody;
import models.SignupRequestBody;
import models.SignupResponseBody;

public class UserClient {

    public SignupResponseBody signup(String email, String password) {
        SignupRequestBody signupRequestBodyBody = SignupRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestBodyBody)
                .when()
                .post("/api/auth/signup");

        SignupResponseBody signupResponseBodyBody = response.as(SignupResponseBody.class);
        signupResponseBodyBody.setStatusCode(response.getStatusCode());

        return signupResponseBodyBody;
    }


    public LoginResponseBody login(String email, String password, String accessToken) {
        LoginRequestBody loginRequestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(loginRequestBody)
                .when()
                .post("/api/auth/login");

        LoginResponseBody loginResponseBody = response.as(LoginResponseBody.class);
        loginResponseBody.setStatusCode(response.getStatusCode());

        return loginResponseBody;
    }

}

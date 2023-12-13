import clients.UserClient;
import models.SignupResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;
import utilities.TestDataLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserSignUp extends BaseTest {

    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void validateUserSignUp() {

        String randomPassword = TestDataLoader.getValidUserPassword();

        String randomEmail = RandomEmailGenerator.generateRandomEmailId();

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, randomPassword);

//        signupResponseBody.assertSignupResponse(randomEmail);
        assertEquals(signupResponseBody.getStatusCode(), 201);
        assertEquals(signupResponseBody.getData().getUser().getEmail(), randomEmail);


    }


    @Test
    public void invalidCredentialsSignUp() {
        String invalidEmail = TestDataLoader.getInvalidUserEmail();
        String invalidPassword = TestDataLoader.getInvalidUserPassword();

        SignupResponseBody signupResponseBody = userClient.signup(invalidEmail, invalidPassword);

        Assert.assertEquals(signupResponseBody.getStatusCode(), 401, "Expected status code 400 for invalid sign-up");


    }
}

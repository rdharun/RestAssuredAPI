import clients.UserClient;
import models.SignupResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;


public class UserSignUp extends BaseTest {

    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void validateUserSignUp() {

        String randomPassword = RandomStringUtils.randomAlphanumeric(7);
        String randomEmail = RandomEmailGenerator.generateRandomEmailId();

        SignupResponseBody signupResponseBodyBody = userClient.signup(randomEmail, randomPassword);

        signupResponseBodyBody.assertSuccessfullySignupResponse(randomEmail);


    }
}

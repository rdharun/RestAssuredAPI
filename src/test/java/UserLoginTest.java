import clients.UserClient;
import models.LoginResponseBody;
import models.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

public class UserLoginTest extends BaseTest {

    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void validateUserLogin() {

        String randomEmail = RandomEmailGenerator.generateRandomEmailId();
        String password = "12345678";

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();


        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);


        loginResponseBody.assertSuccessfullyLoginResponse(randomEmail);

    }
}

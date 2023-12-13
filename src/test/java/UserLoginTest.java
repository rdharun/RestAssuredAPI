import clients.UserClient;
import models.LoginResponseBody;
import models.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.UserData;
import utilities.DataProvider;
import utilities.RandomGenerator;

public class UserLoginTest extends BaseTest {

    private UserClient userClient;
    private DataProvider userDataProvider;
    private RandomGenerator randomGenerator;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userDataProvider = new DataProvider("src/main/java/testData/userData.json");
        randomGenerator = new RandomGenerator();

    }

    @Test
    public void validateUserLogin() {
        String randomEmail = randomGenerator.generateRandomEmail("gmail.com");

        String password = userDataProvider.getData("validUser", UserData.class).getPassword();

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        loginResponseBody.assertLoginResponse(randomEmail);

    }
}

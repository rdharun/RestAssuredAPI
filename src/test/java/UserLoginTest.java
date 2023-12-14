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
    private RandomGenerator randomGenerator;
    private DataProvider userDataProvider;


    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        randomGenerator = new RandomGenerator();
        userDataProvider = new DataProvider("src/main/java/testData/userData.json");

    }

    @Test
    public void validateUserLogin() {
        String randomEmail = randomGenerator.generateRandomEmailId();
        UserData validUser = userDataProvider.getData("validUser", UserData.class);
        String password = validUser.getPassword();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        loginResponseBody.assertLoginResponse(randomEmail);

    }
}

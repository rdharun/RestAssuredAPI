import clients.UserClient;
import models.LoginResponseBody;
import models.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DataProvider;
import utilities.RandomGenerator;
import utilities.TestDataRepository;

public class UserLoginTest extends BaseTest {

    private UserClient userClient;
    private DataProvider userDataProvider;
    private RandomGenerator randomGenerator;

    private TestDataRepository testDataRepository;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userDataProvider = new DataProvider("src/main/java/testData/userData.json");
        randomGenerator = new RandomGenerator();
        testDataRepository.loadTestData("src/main/java/testData/userData.json");

    }

    @Test
    public void validateUserLogin() {
        String randomEmail = randomGenerator.generateRandomEmail("gmail.com");

        String password = testDataRepository.getTestData("validUser").get("password").asText();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        loginResponseBody.assertLoginResponse(randomEmail);

    }
}

import clients.UserClient;
import models.SignupResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomGenerator;
import utilities.TestDataRepository;

public class UserSignUp extends BaseTest {

    private UserClient userClient;
    private RandomGenerator randomGenerator;
    private TestDataRepository testDataRepository;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        testDataRepository.loadTestData("src/main/java/testData/userData.json");

        randomGenerator = new RandomGenerator();

    }

    @Test
    public void validateUserSignUp() {


        String randomEmail = randomGenerator.generateRandomEmailId();

        String password = testDataRepository.getTestData("validUser").get("password").asText();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);

        signupResponseBody.assertSignupResponse(randomEmail);

//        assertEquals(signupResponseBody.getStatusCode(), 201);
//        assertEquals(signupResponseBody.getData().getUser().getEmail(), randomEmail);


    }

    @Test
    public void invalidCredentialsSignUp() {

        String email = testDataRepository.getTestData("invalidUser").get("email").asText();
        String password = testDataRepository.getTestData("invalidUser").get("password").asText();


        SignupResponseBody signupResponseBody = userClient.signup(email, password);

        Assert.assertEquals(signupResponseBody.getStatusCode(), 401, "Expected status code 400 for invalid sign-up");

    }
}

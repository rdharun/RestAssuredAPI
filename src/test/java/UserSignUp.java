import clients.UserClient;
import models.SignupResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.UserData;
import utilities.DataProvider;
import utilities.RandomData;

public class UserSignUp extends BaseTest {

    private UserClient userClient;
    private RandomData randomData;

    private DataProvider userDataProvider;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userDataProvider = new DataProvider("src/main/java/testData/userData.json");

        randomData = new RandomData();

    }

    @Test
    public void validateUserSignUp() {


        String randomEmail = randomData.generateRandomEmail("gmail.com");

        String password = userDataProvider.getData("validUser", UserData.class).getPassword();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);

        signupResponseBody.assertSignupResponse(randomEmail);

    }

    @Test
    public void invalidCredentialsSignUp() {

        String password = userDataProvider.getData("invalidUser", UserData.class).getPassword();
        String email = userDataProvider.getData("invalidUser", UserData.class).getEmail();


        SignupResponseBody signupResponseBody = userClient.signup(email, password);

        Assert.assertEquals(signupResponseBody.getStatusCode(), 401, "Expected status code 400 for invalid sign-up");

    }
}

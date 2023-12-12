import clients.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;
import static org.testng.AssertJUnit.assertNotNull;


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

        Response response = userClient.createUser(randomEmail, password);
        String accessToken = response.jsonPath().getString("data.session.access_token");

        Response signInResponse = userClient.authenticateUser(randomEmail, password, accessToken);

        assertStatusCode(signInResponse, 200);
        assertPayloadValue(signInResponse, "data.user.email", randomEmail);
        assertNotNull(signInResponse.jsonPath().getString("data.session.access_token"));
    }
}

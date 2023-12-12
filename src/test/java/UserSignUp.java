import clients.UserClient;
import io.restassured.response.Response;
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

        Response response = userClient.createUser(randomEmail, randomPassword);

//        assertEquals(response.getStatusCode(), 201);
//        BaseTest.assertStatusCode(response.getStatusCode(), 201);
        assertApiResponse(response, 201, "application/json", "data.user.email", randomEmail);

//        assertEquals(response.jsonPath().getString("data.user.email"), randomEmail);
    }
}

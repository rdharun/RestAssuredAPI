import clients.UserClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import models.UserSignupRequest;
import models.UserSignupResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;

public class UserSignUp extends BaseTest {

    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void validateUserSignUp() throws JsonProcessingException {

        String randomPassword = RandomStringUtils.randomAlphanumeric(7);
        String randomEmail = RandomEmailGenerator.generateRandomEmailId();

        // Create UserSignupRequest object
        UserSignupRequest signupRequest = new UserSignupRequest();
        signupRequest.setEmail(randomEmail);
        signupRequest.setPassword(randomPassword);

        // Call createUser with the request object
        Response response = userClient.createUser(signupRequest);

        assertEquals(response.getStatusCode(), 201);

        assertEquals(response.jsonPath().getString("data.user.email"), randomEmail);
    }
}

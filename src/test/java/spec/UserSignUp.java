package spec;

import clients.UserClient;
import io.qameta.allure.*;
import models.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.UserData;
import utilities.DataProvider;
import utilities.RandomData;

public class UserSignUp extends BaseTest {

    private UserClient userClient;
    private RandomData randomData;
    private DataProvider userDataProvider;
    // Use ThreadLocal to maintain separate instances per thread
    private static final ThreadLocal<RandomData> threadLocalRandomData = ThreadLocal.withInitial(() -> new RandomData(42));
    private static final ThreadLocal<DataProvider> threadLocalUserDataProvider = ThreadLocal.withInitial(() -> new DataProvider("src/main/java/testData/userData.json"));


    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        randomData = threadLocalRandomData.get();
        userDataProvider = threadLocalUserDataProvider.get();
    }

    @Test(description = "Validating the user signup API")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Signup")
    public void validateUserSignUp() {


        String randomEmail = randomData.generateRandomEmail("gmail.com");

        String password = userDataProvider.getData("validUser", UserData.class).getPassword();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);

        signupResponseBody.assertSignupResponse(randomEmail);

    }


}

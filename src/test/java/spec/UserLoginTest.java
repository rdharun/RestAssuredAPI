package spec;

import clients.UserClient;
import io.qameta.allure.*;
import models.LoginResponseBody;
import models.SignupResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.UserData;
import utilities.DataProvider;
import utilities.RandomData;


public class UserLoginTest extends BaseTest {

    private UserClient userClient;
    private RandomData randomData;
    private DataProvider userDataProvider;

    // Use ThreadLocal to maintain separate instances per thread
    private static final ThreadLocal<RandomData> threadLocalRandomData = ThreadLocal.withInitial(() -> new RandomData(42));
    private static final ThreadLocal<DataProvider> threadLocalUserDataProvider = ThreadLocal.withInitial(() -> new DataProvider("src/main/java/testData/userData.json"));

    private static final Logger logger = LogManager.getLogger(UserSignUp.class);

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        randomData = threadLocalRandomData.get();
        userDataProvider = threadLocalUserDataProvider.get();

    }


    @Test(description = "Validating the user login API")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login")
    public void validateUserLogin() {

        logger.info("validateUserLogin test execution started");


        String randomEmail = randomData.generateRandomEmail("gmail.com");
        UserData validUser = userDataProvider.getData("validUser", UserData.class);
        String password = validUser.getPassword();

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        loginResponseBody.assertLoginResponse(randomEmail);

        logger.info("validateUserLogin test execution ended");


    }

}

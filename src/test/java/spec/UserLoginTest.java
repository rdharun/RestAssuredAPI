package spec;

import clients.UserClient;
import io.qameta.allure.*;
import models.LoginResponseBody;
import models.SignupResponseBody;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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


    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        randomData = threadLocalRandomData.get();
        userDataProvider = threadLocalUserDataProvider.get();

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            logReportDetails(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(description = "Validating the user login API")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login")
    public void validateUserLogin() {
        String randomEmail = randomData.generateRandomEmail("gmail.com");
        UserData validUser = userDataProvider.getData("validUser", UserData.class);
        String password = validUser.getPassword();

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        loginResponseBody.assertLoginResponse(randomEmail);

    }

}

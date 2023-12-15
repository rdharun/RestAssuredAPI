import clients.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.LoginResponseBody;
import models.SignupResponseBody;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.UserData;
import utilities.AllureLogger;
import utilities.DataProvider;
import utilities.LogUtility;
import utilities.RandomData;



public class UserLoginTest extends BaseTest {

    private UserClient userClient;
    private RandomData randomData;
    private DataProvider userDataProvider;


    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        randomData = new RandomData(42);
        userDataProvider = new DataProvider("src/main/java/testData/userData.json");

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            // Get logs and add them to the Allure report
            String logs = LogUtility.captureLogs(result.getThrowable());
            AllureLogger.addLogsToAllureReport(logs, result.getThrowable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Description("Validating the user login API")
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

import clients.UserClient;
import io.qameta.allure.*;
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

public class UserSignUp extends BaseTest {

    private UserClient userClient;
    private RandomData randomData;
    private DataProvider userDataProvider;

    private long startTime;


    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userDataProvider = new DataProvider("src/main/java/testData/userData.json");
        randomData = new RandomData(42);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            // Get logs and add them to the Allure report
            String logs = LogUtility.captureLogs(result.getThrowable());
            AllureLogger.addLogsToAllureReport(logs, result.getThrowable());

            // Add execution time to the report
            long executionTime = System.currentTimeMillis() - startTime;
            Allure.addAttachment("Execution Time", "text/plain", "Execution time: " + executionTime + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Description("Validating the user sign API")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Signup")
    public void validateUserSignUp() {


        String randomEmail = randomData.generateRandomEmail("gmail.com");

        String password = userDataProvider.getData("validUser", UserData.class).getPassword();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);

        signupResponseBody.assertSignupResponse(randomEmail);

    }


}

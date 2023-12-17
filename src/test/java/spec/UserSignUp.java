package spec;

import clients.UserClient;
import io.qameta.allure.*;
import models.SignupResponseBody;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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
        randomData = new RandomData(42);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            logReportDetails(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test (description = "Validating the user signup API")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Signup")
    public void validateUserSignUp() {


        String randomEmail = randomData.generateRandomEmail("gmail.com");

        String password = userDataProvider.getData("validUser", UserData.class).getPassword();


        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);

        signupResponseBody.assertSignupResponse(randomEmail);

    }


}

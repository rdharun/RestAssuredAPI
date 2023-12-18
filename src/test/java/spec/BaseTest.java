package spec;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.PropertyUtils;

import java.io.PrintWriter;
import java.io.StringWriter;


public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);


    @BeforeTest
    public void setUp() {
        String baseUrl = PropertyUtils.getProperty("base_url");
        RestAssured.baseURI = baseUrl;
    }

    @AfterTest
    public void tearDown() {
        // Any teardown logic you want to add
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            Throwable t = result.getThrowable();

            StringWriter error = new StringWriter();
            t.printStackTrace(new PrintWriter(error));

            logger.info(error.toString());
        }

    }

}

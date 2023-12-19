package spec;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;
import utilities.ConfigConstants;

public class ConfigurationValidationTest {

    @Test
    public void validateDevConfiguration() {
        validateConfigurationForEnvironment(ConfigConstants.DEV_PROPERTIES);
    }

    @Test
    public void validateQAConfiguration() {
        validateConfigurationForEnvironment(ConfigConstants.QA_PROPERTIES);
    }

    @Test
    public void validateProdConfiguration() {
        validateConfigurationForEnvironment(ConfigConstants.PROD_PROPERTIES);
    }

    private void validateConfigurationForEnvironment(String environment) {
        BaseTest baseTest = new BaseTest();
        baseTest.loadConfigurationsForEnvironment(environment);

        assertNotNull(RestAssured.baseURI, "Base URL should not be null for " + environment + " environment");
        // Add more assertions if needed
    }
}

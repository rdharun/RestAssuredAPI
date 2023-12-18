package spec;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;

public class ConfigurationValidationTest {

    @Test
    public void validateDevConfiguration() {
        validateConfigurationForEnvironment("dev");
    }

    @Test
    public void validateQAConfiguration() {
        validateConfigurationForEnvironment("qa");
    }

    @Test
    public void validateProdConfiguration() {
        validateConfigurationForEnvironment("prod");
    }

    private void validateConfigurationForEnvironment(String environment) {
        BaseTest baseTest = new BaseTest();
        baseTest.loadConfigurationsForEnvironment(environment);

        assertNotNull(RestAssured.baseURI, "Base URL should not be null for " + environment + " environment");
        // Add more assertions if needed
    }
}

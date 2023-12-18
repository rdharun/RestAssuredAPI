package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static final Logger logger = LogManager.getLogger(PropertyUtils.class);


    public static String getProperty(String propertyName, String environment) {
        String configPropertyFilePath = "src/main/resources/" + environment + ".properties";
        Properties properties = new Properties();
        try {
            FileInputStream configFile = new FileInputStream(configPropertyFilePath);
            properties.load(configFile);
        } catch (IOException e) {
            logger.error("Failed to load environment-specific properties file", e);
            throw new RuntimeException("Configuration could not be loaded for environment: " + environment, e);
        }
        return properties.getProperty(propertyName);
    }
}

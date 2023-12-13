package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestDataLoader {

    private static final String Json_file =  "src/test/java/testData/userCrenditals.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static JsonNode testData;


    static {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(Json_file)));
            testData = objectMapper.readTree(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getValidUserEmail() {
        return testData.get("validUserData").get("email").asText();
    }

    public static String getValidUserPassword() {
        return testData.get("validUserData").get("password").asText();
    }

    public static String getInvalidUserEmail() {
        return testData.get("invalidUserData").get("email").asText();
    }

    public static String getInvalidUserPassword() {
        return testData.get("invalidUserData").get("password").asText();
    }

}

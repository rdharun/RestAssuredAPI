package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestDataRepository {
    private static final Map<String, JsonNode> testDataMap = new HashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void loadTestData(String filePath) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JsonNode rootNode = objectMapper.readTree(jsonContent);

            for (Iterator<String> it = rootNode.fieldNames(); it.hasNext(); ) {
                String key = it.next();
                testDataMap.put(key, rootNode.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static JsonNode getTestData(String key) {
        return testDataMap.get(key);
    }

    // Other utility methods to manipulate test data as needed
}

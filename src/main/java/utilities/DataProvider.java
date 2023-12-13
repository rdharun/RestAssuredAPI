package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataProvider {

    private String filePath;

    public DataProvider(String filePath) {
        this.filePath = filePath;
    }

    public <T> T getData(String dataKey, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode testData = TestDataRepository.getTestData(filePath);

        if (testData != null) {
            JsonNode userDataNode = testData.get(dataKey);
            try {
                return objectMapper.treeToValue(userDataNode, classType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

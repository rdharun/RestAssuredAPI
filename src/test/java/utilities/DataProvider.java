package utilities;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProvider {

    private static final Logger logger = LogManager.getLogger(DataProvider.class);
    private String filePath;

    public DataProvider(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves data of type T from a JSON file based on the provided key.
     *
     * @param <T>       The type of the data to retrieve.
     * @param dataKey   The key identifying the user within the JSON file.
     * @param classType The class type representing the desired data type.
     * @return An object of type T representing the data, or null if an error occurs.
     * @throws IOException If the provided file path is null or empty.
     */
    public <T> T getData(String dataKey, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(filePath);
            JsonNode jsonData = objectMapper.readTree(file);
            JsonNode userDataNode = jsonData.get(dataKey);

            return objectMapper.treeToValue(userDataNode, classType);
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath, e);
            e.printStackTrace();
            return null;
        } catch (JsonParseException e) {
            logger.error("Error parsing JSON file: {}", filePath, e);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            logger.error("An I/O error occurred when accessing the file: {}", filePath, e);
            e.printStackTrace();
            return null;
        }
    }
}
package core.readers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static String readJson(String filePath, String key) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filePath);
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode valueNode = rootNode.get(key);

            if (valueNode != null) {
                return valueNode.asText();
            } else {
                throw new IllegalArgumentException("Key not found: " + key);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    @Test(enabled = false)
    public void test() {
        String filePath = "src/test/resources/WriteFiles/jsonData.json";

        System.out.println(readJson(filePath, "name"));
    }
}

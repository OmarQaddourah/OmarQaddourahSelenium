package core.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonWriter {

    private static final String JSON_FILE_PATH = "src/test/resources/WriteFiles/jsonData.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static Map<String, Object> jsonData = new HashMap<>();

    public static void writeJson(String key, String value) {
        try {
            jsonData = OBJECT_MAPPER.readValue(new File(JSON_FILE_PATH), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonData.put(key, value);
        try {
            OBJECT_MAPPER.writeValue(new File(JSON_FILE_PATH), jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
    public void test() {
        writeJson("location", "Saudi Arabia");
    }
}

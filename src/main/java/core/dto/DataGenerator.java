package core.dto;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class DataGenerator {

    public static <T> Object[][] getTestData(Method method, Class<T> tClass) throws FileNotFoundException {
        String methodName = method.getName();
        Field testDataField;
        String testDataFilePath;
        try {
            testDataField = tClass.getDeclaredField("testDataFilePath");
            testDataField.setAccessible(true);
            testDataFilePath = String.valueOf(testDataField.get(tClass.getConstructor().newInstance()));
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException |
                 NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        JsonElement jsonData = JsonParser.parseReader(new FileReader(testDataFilePath));
        JsonElement dataSet = null;
        try {
            dataSet = jsonData.getAsJsonObject().get(methodName);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString())).create();
        Type dataType = TypeToken.getParameterized(List.class, tClass).getType();
        List<T> testData = gson.fromJson(dataSet, dataType);
        assert testData != null : "No Data exists for method: " + methodName;
        Object[][] returnValue = new Object[testData.size()][1];


        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    @DataProvider
    public Object[][] provideTestData(Method method, ITestContext context) throws FileNotFoundException {
        return getTestData(method, method.getParameters()[0].getType());
    }
}

package core.helpers;

import java.util.Random;

import static core.base.BaseTestClass.globalObject;

public class DataHelper {

    public static String getRandomString(String language, int length) {
        String englishCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String arabicCharacters = "ابتثجحخدذرزسشصضطظعغفقكلمنهوي";
        StringBuilder str = new StringBuilder();
        Random random = new Random();

        while (str.length() < length) {
            switch (language) {
                case "Arabic" -> {
                    int arabicIndex = (int) (random.nextFloat() * arabicCharacters.length());
                    str.append(arabicCharacters.charAt(arabicIndex));
                }
                case "English" -> {
                    int englishIndex = (int) (random.nextFloat() * englishCharacters.length());
                    str.append(englishCharacters.charAt(englishIndex));
                }
            }
        }
        return str.toString();
    }

    public static int generateRandNumber(int maxNumber) {
        int randomNumber = (int) Math.floor(Math.random() * maxNumber);
        if (randomNumber == 0) {
            return 1;
        }
        return randomNumber;
    }

    public static void setGlobalVariable(String key, String value) {
        globalObject.put(key, value);
    }

    public static Object getGlobalVariable(String key) {
        return globalObject.get(key);
    }
}

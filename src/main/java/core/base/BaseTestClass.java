package core.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;

public class BaseTestClass {

    public static WebDriver webDriver;
    public static HashMap<String, Object> globalObject = new HashMap<>();

    @BeforeClass()
    public void setWebDriver() {
        ChromeOptions options = new ChromeOptions();
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.google.com/");
    }

    @AfterClass(alwaysRun = true)
    public void closeWebDriver() {
        webDriver.quit();
    }
}

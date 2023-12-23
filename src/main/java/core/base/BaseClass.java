package core.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;

public class BaseClass {

    public static WebDriver webDriver;
    public static HashMap<String, Object> globalVariable = new HashMap<>();

    @BeforeClass()
    public void setWebDriver() {
        ChromeOptions options = new ChromeOptions();
        webDriver = new ChromeDriver(options);
        webDriver.get("https://www.google.com/");
        webDriver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void closeWebDriver() {
        webDriver.quit();
    }
}

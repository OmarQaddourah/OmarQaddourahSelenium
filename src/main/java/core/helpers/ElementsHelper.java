package core.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.base.BaseClass.webDriver;

public class ElementsHelper {

    public static final int TIME_OUT_IN_SECONDS = 60;
    private static final String OPERATING_SYSTEM = System.getProperty("os.name");
    public static WebDriverWait webDriverWait;
    private static Actions actions;

    public static WebElement elementToBeVisible(WebElement webElement) {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static WebElement elementToBeClickable(WebElement webElement) {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void uploadFile(WebElement webElement, String fileName) {
        String path;
        path = System.getProperty("user.dir") + "/src/test/resources/ImagesAndFiles/" + fileName;
        webElement.sendKeys(path);
    }

    public static void scrollTo(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", webElement);
    }

    public static void updateTextField(WebElement webElement, String newText) {
        if (OPERATING_SYSTEM.equals("Mac OS X")) {
            webElement.sendKeys(Keys.chord(Keys.COMMAND, "a"), newText);
        } else {
            webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), newText);
        }
    }

    public static void hoverOnElement(WebElement webElement) {
        actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }

    public static void waitForTime(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

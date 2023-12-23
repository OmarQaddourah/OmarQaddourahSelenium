package core.helpers;

import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static core.base.BaseClass.webDriver;

public class BrowserHelper {

    public static void scrollBy(String width) {
        ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0," + width + ")", "");
    }

    public static void refreshPage() {
        webDriver.navigate().refresh();
    }

    public static void switchToTab(int tabIndex) {
        ArrayList<String> numberOfTabsTwo = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(numberOfTabsTwo.get(tabIndex));
    }

    public static void scrollToDirection(String direction) {
        if (direction.equals("bottom")) {
            ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        }
        if (direction.equals("top")) {
            ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, 0);");
        }
    }

    public static void openNewTab(String url) {
        ((JavascriptExecutor) webDriver).executeScript("window.open('" + url + "')");
    }
}

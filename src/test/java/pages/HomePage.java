package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.helpers.ElementsHelper.elementToBeVisible;

public class HomePage {

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "[alt='Google']")
    private WebElement homeLogo;

    public WebElement getHomeLogo() {
        return elementToBeVisible(this.homeLogo);
    }
}

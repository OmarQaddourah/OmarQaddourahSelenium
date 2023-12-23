package tests;

import core.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseClass {

    private HomePage homePage;

    @Test(description = "Verify homepage google logo", priority = 1)
    public void verifyHomeLogo() {
        homePage = new HomePage(webDriver);

        Assert.assertTrue(homePage.getHomeLogo().isDisplayed());
    }
}

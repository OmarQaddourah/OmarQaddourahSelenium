package tests;

import core.base.BaseTestClass;
import core.dto.BaseDTO;
import core.dto.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTestClass {

    private HomePage homePage;

    @Test(description = "Verify homepage google logo", groups = {}, priority = 1,
            dataProviderClass = DataGenerator.class, dataProvider = "provideTestData")
    public void verifyHomeLogo(BaseDTO dto) {
        homePage = new HomePage(webDriver);
        System.out.println(dto.testData);

        Assert.assertTrue(homePage.getHomeLogo().isDisplayed());
    }
}

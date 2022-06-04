package tests;

import base.BaseTest;

import data.config.dataProviders.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.HomePage;
import utils.SeleniumUtils;

public class HomeTest extends BaseTest {
    HomePage homePage;
    AccountPage accountPage;
    SeleniumUtils seleniumUtils;


    @BeforeMethod
    public void localSetUp() {
        homePage = new HomePage(getDriver());
        accountPage = new AccountPage(getDriver());
        seleniumUtils = new SeleniumUtils();

    }

    @Test(testName = "AUT-3 Footer - Information section",
            description = "Information section should have following buttons",
            dataProvider = "info", dataProviderClass = DataProviders.class)
    public void testInfo(String info) {
        getDriver().findElement(By.linkText(info)).click();
        Assert.assertTrue(getDriver().findElement(By.linkText(info)).isEnabled());
    }

    @Test(testName = "AUT-4 Footer - My account section",
            description = "My account section should have following options",
            dataProvider = "account", dataProviderClass = DataProviders.class)
    public void testAccount(String account) {
        getDriver().findElement(By.linkText(account)).click();
        Assert.assertEquals(getDriver().getTitle(), accountPage.expectedTitle);

    }

    @Test(testName = "AUT-5 Follow us - social media buttons", description = "Verify Follow Us section has following social button",
            dataProvider = "socialLink", dataProviderClass = DataProviders.class)
    public void TestSocial(String link, String title) {
        getDriver().findElement(By.xpath("//a[contains (@href, '" + link.toLowerCase() + "')]")).click();
        seleniumUtils.switchToNextWindow(getDriver());
        Assert.assertEquals(getDriver().getTitle(), title);
    }
}



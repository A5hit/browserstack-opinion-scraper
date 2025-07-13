package org.browserstack.tests;


import org.browserstack.pages.HomePage;
import org.browserstack.pages.OpinionPage;
import org.browserstack.utils.ConfigLoader;
import org.browserstack.utils.TranslationService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class OpinionScraperTest {

    private WebDriver driver;
    private HomePage homePage;
    private OpinionPage opinionPage;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        driver = new RemoteWebDriver(new URL("http://hub.browserstack.com/wd/hub"), new DesiredCapabilities());

        homePage = new HomePage(driver);
        opinionPage = new OpinionPage(driver);
    }

    @Test
    public void testScrapeOpinionArticles() {
        SoftAssert softAssert = new SoftAssert();

        try {
            homePage.goToHomePage();
            homePage.acceptCookiesIfPresent();
            homePage.clickOpinionLink();
            TranslationService translationService = new TranslationService(ConfigLoader.get("translation.apiKey"));
            opinionPage.fetchFirstFiveArticles(translationService);
        } catch (Exception e) {
            softAssert.fail("Scraping test failed: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

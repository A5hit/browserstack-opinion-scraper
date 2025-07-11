package org.browserstack.tests;

import org.browserstack.drivers.DriverManager;
import org.browserstack.pages.HomePage;
import org.browserstack.pages.OpinionPage;
import org.browserstack.utils.ConfigLoader;
import org.browserstack.utils.TranslationService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class OpinionScraperTest {

    private WebDriver driver;
    private HomePage homePage;
    private OpinionPage opinionPage;

    @BeforeClass
    @Parameters({"os", "osVersion", "browser", "browserVersion"})
    public void setUp(
            @Optional String os,
            @Optional String osVersion,
            @Optional String browser,
            @Optional String browserVersion) {
        driver = DriverManager.getDriver(os, osVersion, browser, browserVersion);
        homePage = new HomePage(driver);
        opinionPage = new OpinionPage(driver);
    }


    @Test
    public void testScrapeOpinionArticles() {
        homePage.goToHomePage();
        homePage.acceptCookiesIfPresent();
        homePage.clickOpinionLink();
        TranslationService translationService = new TranslationService(ConfigLoader.get("translation.apiKey"));
        opinionPage.fetchFirstFiveArticles(translationService);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

package org.browserstack.tests;

import org.browserstack.base.DriverManager;
import org.browserstack.pages.HomePage;
import org.browserstack.pages.OpinionPage;
import org.browserstack.utils.TranslationService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class OpinionScraperTest {

    private WebDriver driver;
    private HomePage homePage;
    private OpinionPage opinionPage;

    @BeforeClass
    public void setUp()  {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        opinionPage = new OpinionPage(driver);

    }

    @Test
    public void testScrapeOpinionArticles() {
        homePage.goToHomePage();
        homePage.acceptCookiesIfPresent();
        homePage.clickOpinionLink();
        TranslationService translationService = new TranslationService("64677a3b3amshfbfbd7cb30120dfp17798bjsnb22e93cf2ddb");
        opinionPage.fetchFirstFiveArticles(translationService);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

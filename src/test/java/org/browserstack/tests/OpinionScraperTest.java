package org.browserstack.tests;

import org.browserstack.base.DriverManager;
import org.browserstack.pages.HomePage;
import org.browserstack.pages.OpinionPage;
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
    public void setUp() throws IOException {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        opinionPage = new OpinionPage(driver);

    }

    @Test
    public void testScrapeOpinionArticles() {
        homePage.goToHomePage();
        homePage.acceptCookiesIfPresent();
        homePage.clickOpinionLink();
        opinionPage.fetchFirstFiveArticles();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

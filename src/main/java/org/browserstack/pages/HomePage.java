package org.browserstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String HOME_URL = "https://elpais.com";
    private static final By ACCEPT_COOKIES_BUTTON = By.xpath("//button[@id='didomi-notice-agree-button']");
    private static final By OPINION_LINK = By.xpath("//nav[@class='cs_m']//a[contains(text(),'Opinión')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToHomePage() {
        driver.get(HOME_URL);

        // Validate language
        String lang = driver.findElement(By.tagName("html")).getAttribute("lang");
        if (!"es-ES".equalsIgnoreCase(lang)) {
            throw new IllegalStateException("Website is not in Spanish! Detected: " + lang);
        }
    }


    public void acceptCookiesIfPresent() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BUTTON));
            if (acceptButton.isDisplayed() && acceptButton.isEnabled()) {
                acceptButton.click();
            }
        } catch (Exception e) {
            // Button not present, do nothing
        }
    }

    public void clickOpinionLink() {
        WebElement opinionLink = wait.until(ExpectedConditions.presenceOfElementLocated(OPINION_LINK));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opinionLink);
    }
}

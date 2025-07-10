package org.browserstack.pages;

import org.browserstack.utils.FileDownloader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class OpinionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Centralize all selectors here
    private static final By ARTICLE_SELECTOR = By.cssSelector("article");
    private static final By ARTICLE_LINK_SELECTOR = By.xpath(".//h2//a");

    private static final By IMAGE_SELECTOR = By.xpath("//figure[@class='a_m a_m-h ']/descendant::img");

    private static final List<By> CONTENT_SELECTORS = List.of(
            By.cssSelector(".a_c.clearfix"),
            By.cssSelector("[data-testid='article-content']"),
            By.xpath("//article[@id='main-content']")
    );

    public OpinionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fetchFirstFiveArticles() {
        int count = 5;

        for (int i = 0; i < count; i++) {
            try {
                List<WebElement> articles = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(ARTICLE_SELECTOR)
                );
                WebElement article = articles.get(i);

                String title = extractTitle(article);
                log("Processing article: " + title);

                WebElement link = article.findElement(ARTICLE_LINK_SELECTOR);
                clickLink(link);

                waitForContent();

                String content = extractContent();
                String imageUrl = extractImage();


                // ✨ Cleaner, consistent logging
                log("=====================================================");
                log("[ARTICLE %d]", i + 1);
                log("Link : %s", driver.getCurrentUrl());
                log("Title   : %s", title);
                log("Content : %s", truncateContent(content));
                log("Image   : %s", imageUrl);

                if (!"No image found".equals(imageUrl)) {
                    String safeTitle = title.replaceAll("[^a-zA-Z0-9\\-_]", "_");
                    String imagePath = String.format("output/images/%02d_%s.jpg", i + 1, safeTitle);
                    try {
                        FileDownloader.download(imageUrl, imagePath);
                        log("Saved   : %s", imagePath);
                    } catch (IOException e) {
                        log("Failed  : %s", e.getMessage());
                    }
                } else {
                    log("Saved   : [skipped]");
                }

                log("=====================================================");


                driver.navigate().back();

                // Wait again after back navigation
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ARTICLE_SELECTOR));
            } catch (Exception e) {
                log("Error processing article %d: %s", i + 1, e.getMessage());
            }
        }
    }

    private String extractTitle(WebElement article) {
        List<By> titleSelectors = List.of(By.tagName("h2"), By.tagName("h1"), By.tagName("h3"));
        for (By sel : titleSelectors) {
            List<WebElement> titles = article.findElements(sel);
            if (!titles.isEmpty()) {
                return titles.get(0).getText();
            }
        }
        return "No title found";
    }

    private void clickLink(WebElement link) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

    private void waitForContent() {
        wait.until(driver -> CONTENT_SELECTORS.stream()
                .anyMatch(selector -> !driver.findElements(selector).isEmpty()));
    }

    private String extractContent() {
        for (By selector : CONTENT_SELECTORS) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                return elements.get(0).getText();
            }
        }
        return "No content found";
    }

    private String extractImage() {
        List<WebElement> images = driver.findElements(IMAGE_SELECTOR);
        if (!images.isEmpty()) {
            return images.get(0).getAttribute("src");
        }
        return "No image found";
    }

    private String truncateContent(String content) {
        if (content.length() > 300) {
            return content.substring(0, 300) + "...";
        }
        return content;
    }

    private void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}

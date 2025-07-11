package org.browserstack.pages;

import org.browserstack.utils.FileDownloader;
import org.browserstack.utils.TranslationService;
import org.browserstack.utils.WordFrequencyAnalyzer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void fetchFirstFiveArticles(TranslationService translationService) {
        List<String> scrapedTitles = new ArrayList<>();
        int count = 5;

        for (int i = 0; i < count; i++) {
            try {
                List<WebElement> articles = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(ARTICLE_SELECTOR)
                );
                WebElement article = articles.get(i);

                String title = extractTitle(article);
                scrapedTitles.add(title);
                log("Processing article: " + title);

                safeClick(ARTICLE_LINK_SELECTOR);


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
                    String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
                    String imagePath = String.format("output/images/%02d_%s_%s.jpg", i + 1, safeTitle, uniqueSuffix);
                    try {
                        FileDownloader.download(imageUrl, imagePath);
                        log("Saved   : %s", imagePath);
                    } catch (IOException e) {
                        log("❌ Failed to download image for article %d: %s", i + 1, e.toString());
                    }
                } else {
                    log("Saved   : [skipped]");
                }

                log("=====================================================");


                driver.navigate().back();
                //Thread.sleep(1000);
                // Wait again after back navigation
                wait.until(ExpectedConditions.visibilityOfElementLocated(ARTICLE_SELECTOR));
            } catch (Exception e) {
                log("❌ Error processing article %d: %s", i + 1, e.toString());
                captureScreenshot(String.format("article_%02d_error", i + 1));
                e.printStackTrace(System.out);
            }

        }
        // Translate all titles after scraping
        List<String> translatedTitles = new ArrayList<>();
        try {
            translatedTitles = translationService.translateToEnglish(scrapedTitles);
            log("Translated Titles:");
            for (String translated : translatedTitles) {
                log(" - " + translated);
            }
        } catch (IOException e) {
            log("Translation failed: " + e.getMessage());
        }
        if (!translatedTitles.isEmpty()) {
            WordFrequencyAnalyzer.analyze(translatedTitles);
        } else {
            log("Skipping analysis because translation failed.");
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
        try {
            WebElement img = wait.withTimeout(Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(IMAGE_SELECTOR));
            String src = img.getAttribute("src");
            if (src == null || src.isBlank()) {
                return "No image found";
            }
            return src;
        } catch (TimeoutException e) {
            return "No image found";
        }
    }


    private String truncateContent(String content) {
        if (content.length() > 300) {
            return content.substring(0, 300) + "...";
        }
        return content;
    }

    private void log(String format, Object... args) {
        String thread = Thread.currentThread().getName();
        String message = String.format(format, args);
        System.out.printf("[%s] %s%n", thread, message);
    }


    private void safeClick(By by) {
        int retries = 3;
        while (retries > 0) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                return; // success
            } catch (StaleElementReferenceException e) {
                retries--;
                if (retries == 0) throw e;
            }
        }
    }
    private void captureScreenshot(String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            java.nio.file.Path path = java.nio.file.Paths.get("output/screenshots", name + ".png");
            java.nio.file.Files.createDirectories(path.getParent());
            java.nio.file.Files.write(path, screenshot);
            log("Screenshot saved: %s", path.toString());
        } catch (IOException io) {
            log("Failed to save screenshot: %s", io.getMessage());
        } catch (Exception ex) {
            log("Screenshot capture failed: %s", ex.getMessage());
        }
    }


}

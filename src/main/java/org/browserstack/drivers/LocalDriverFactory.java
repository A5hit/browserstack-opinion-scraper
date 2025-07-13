package org.browserstack.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LocalDriverFactory {

    public static WebDriver createDriver() {
        // If you have chromedriver in PATH or in the same directory, no need to set property
        // Otherwise, uncomment below and provide absolute path
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        return new ChromeDriver(options);
    }
}

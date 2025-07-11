package org.browserstack.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.browserstack.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Entry point - decides local or remote based on config.
     */
    public static WebDriver getDriver(
            String os,
            String osVersion,
            String browser,
            String browserVersion
    ) {
        if (driver.get() == null) {
            String runMode = ConfigLoader.get("run.mode");
            if ("remote".equalsIgnoreCase(runMode)) {
                driver.set(createRemoteDriver(os, osVersion, browser, browserVersion));
            } else {
                driver.set(createLocalDriver());
            }
        }
        return driver.get();
    }

    /**
     * Creates a local ChromeDriver.
     */
    private static WebDriver createLocalDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        WebDriver localDriver = new ChromeDriver(options);
        localDriver.manage().window().maximize();
        return localDriver;
    }

    /**
     * Creates a remote BrowserStack driver.
     */
    private static WebDriver createRemoteDriver(String os, String osVersion, String browser, String browserVersion) {
        try {
            String username = ConfigLoader.get("browserstack.username");
            String accessKey = ConfigLoader.get("browserstack.accessKey");
            String hubUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os", os);
            caps.setCapability("os_version", osVersion);
            caps.setCapability("browser", browser);
            caps.setCapability("browser_version", browserVersion);
            caps.setCapability("name", "BrowserStack cross-browser test");
            caps.setCapability("build", "browserstack-scraper-build-1");

            return new RemoteWebDriver(new URL(hubUrl), caps);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create RemoteWebDriver: " + e.getMessage());
        }
    }

    /**
     * Quits the driver.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}

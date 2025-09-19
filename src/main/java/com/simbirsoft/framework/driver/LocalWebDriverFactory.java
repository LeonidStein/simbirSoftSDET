package com.simbirsoft.framework.driver;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class LocalWebDriverFactory implements WebDriverFactory {

    @Override
    public WebDriver createDriver(@NonNull String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return createLocalChromeDriver();
        } else {
            throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }

    private WebDriver createLocalChromeDriver() {

        final ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");

        return new ChromeDriver(chromeOptions);
    }
}

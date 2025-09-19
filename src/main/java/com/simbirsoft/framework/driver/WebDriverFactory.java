package com.simbirsoft.framework.driver;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface WebDriverFactory {
    WebDriver createDriver(final @NonNull String browser);
}
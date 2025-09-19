package com.simbirsoft.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseTest {

    private final WebDriver driver;

    public BaseTest(final WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    protected final WebDriver getDriver() {
        return driver;
    }
}

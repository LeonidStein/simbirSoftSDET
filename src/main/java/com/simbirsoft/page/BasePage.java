package com.simbirsoft.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    private final WebDriver driver;

    public BasePage(final WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    protected final WebDriver getDriver() {
        return driver;
    }
}

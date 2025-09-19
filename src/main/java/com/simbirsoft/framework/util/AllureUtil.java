package com.simbirsoft.framework.util;

import io.qameta.allure.Allure;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public final class AllureUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AllureUtil.class);

    public static void attachPageSource(final @NonNull WebDriver driver) {
        final String pageSource = driver.getPageSource();
        Allure.addAttachment("Page Source", "text/html", Objects.requireNonNull(pageSource));
    }

    public static void attachWindowSize(final @NonNull WebDriver driver) {
        final Dimension size = driver.manage().window().getSize();
        final String windowSize = "Width: " + size.getWidth() + ", Height: " + size.getHeight();

        Allure.addAttachment("Window Size", "text/plain", windowSize);
    }

    public static void attachOSInfo() {
        final String osInfo = String.format("""
                        ================================
                        OS Information
                        ================================
                        OS Name        : %s
                        OS Version     : %s
                        Architecture   : %s
                        ================================
                        """,
                System.getProperty("os.name"),
                System.getProperty("os.version"),
                System.getProperty("os.arch"));

        Allure.addAttachment("OS Info", "text/plain", osInfo);
    }

    public static void attachScreenshot(final @NonNull WebDriver driver) {

        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment("Screenshot", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        } else {
            LOG.warn("Screenshot is not supported");
        }
    }
}

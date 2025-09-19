package com.simbirsoft;

import com.simbirsoft.framework.config.ConfigurationManager;
import com.simbirsoft.framework.driver.WebDriverFactoryProducer;
import com.simbirsoft.framework.util.AllureUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith({io.qameta.allure.junit5.AllureJunit5.class})
public abstract class BaseTest {

    private static final String CHROME_BROWSER = ConfigurationManager.config().browser();
    private final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    protected final WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach()
    public final void setUp() {
        Allure.step("Запуск тестов на браузере: " + CHROME_BROWSER);

        final WebDriver driver = WebDriverFactoryProducer.getFactory().createDriver(CHROME_BROWSER);
        this.threadLocalDriver.set(driver);

        getDriver().get(ConfigurationManager.config().baseUrl());
    }

    @AfterEach
    public final void tearDown() {
        AllureUtil.attachOSInfo();
        AllureUtil.attachPageSource(this.threadLocalDriver.get());
        AllureUtil.attachWindowSize(this.threadLocalDriver.get());
        AllureUtil.attachScreenshot(this.threadLocalDriver.get());

        if (threadLocalDriver.get() != null) {
            getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}

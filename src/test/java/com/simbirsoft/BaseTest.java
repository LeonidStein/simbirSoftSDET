package com.simbirsoft;

import com.simbirsoft.framework.driver.WebDriverFactoryProducer;
import com.simbirsoft.framework.util.AllureUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static com.simbirsoft.framework.config.ConfigurationManager.config;

@ExtendWith({io.qameta.allure.junit5.AllureJunit5.class})
public abstract class BaseTest {

    private static final String CHROME_BROWSER = config().browser();
    private static final String URL_FORM_FIELDS_PAGE = config().baseURL() + config().pathToFormFields();
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
        Allure.step("Запуск теста на браузере: " + CHROME_BROWSER);

        final WebDriver driver = WebDriverFactoryProducer.getFactory().createDriver(CHROME_BROWSER);
        this.threadLocalDriver.set(driver);

        getDriver().get(URL_FORM_FIELDS_PAGE);
        Allure.step("Открыт URL: " + URL_FORM_FIELDS_PAGE);
    }

    @AfterEach
    public final void tearDown() {

        final WebDriver driver = this.threadLocalDriver.get();

        if (driver != null) {
            AllureUtil.attachOSInfo();
            AllureUtil.attachPageSource(driver);
            AllureUtil.attachWindowSize(driver);
            AllureUtil.attachScreenshot(driver);

            driver.quit();
            this.threadLocalDriver.remove();
        }

        Allure.step("Браузер " + CHROME_BROWSER + " закрыт");
    }
}

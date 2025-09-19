package com.simbirsoft.framework.driver;

public final class WebDriverFactoryProducer {

    public static LocalWebDriverFactory getFactory() {
        return new LocalWebDriverFactory();
    }
}


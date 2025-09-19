package com.simbirsoft.framework.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationManager {

    public static Configuration config() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}

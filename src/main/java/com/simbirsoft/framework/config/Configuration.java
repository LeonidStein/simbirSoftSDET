package com.simbirsoft.framework.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:config.properties"})
public interface Configuration extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("browser")
    String browser();
}
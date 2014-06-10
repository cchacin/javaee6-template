package com.example.javaee.configurations;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

public class RedisFileConfig implements PropertyFileConfig {

    private static final long serialVersionUID = -77623805738821341L;

    @Override
    public String getPropertyFileName() {
	return "redis.properties";
    }
}

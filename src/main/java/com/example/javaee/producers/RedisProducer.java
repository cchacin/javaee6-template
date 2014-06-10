package com.example.javaee.producers;

import lombok.Data;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.redisson.Config;
import org.redisson.Redisson;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Data
@ApplicationScoped
public class RedisProducer {

    Config config;
    Redisson redisson;

    @Inject
    @ConfigProperty(name = "server", defaultValue = "127.0.0.1:6397")
    private String server;

    @PostConstruct
    public void init() {
	try {
	    this.config = new Config();
	    this.config.addAddress(this.server);
	    this.config.setConnectionPoolSize(10);

	    this.redisson = Redisson.create(config);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Produces
    public Redisson produceRedis() {
	return this.redisson;
    }

}
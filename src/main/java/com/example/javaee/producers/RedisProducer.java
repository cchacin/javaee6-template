package com.example.javaee.producers;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.redisson.Config;
import org.redisson.Redisson;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class RedisProducer {

	private Config config;
	private Redisson redisson;

	@Inject
	@ConfigProperty(name = "server", defaultValue = "127.0.0.1:6397")
	private String server;

	@PostConstruct
	public void init() {
		this.config = new Config();
		this.config.useSingleConnection().setAddress(this.server)
				.setConnectionPoolSize(10);

		this.redisson = Redisson.create(this.config);
	}

	@Produces
	public Redisson produceRedis() {
		return this.redisson;
	}

}

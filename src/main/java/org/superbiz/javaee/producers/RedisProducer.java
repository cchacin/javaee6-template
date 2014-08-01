package org.superbiz.javaee.producers;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.redisson.Config;
import org.redisson.Redisson;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class RedisProducer {
	private Redisson redisson;

	@Inject
	@ConfigProperty(name = "server", defaultValue = "128.0.0.1:6397")
	private String server;

	@PostConstruct
	public void init() {
		final Config config = new Config();
		config.useSingleServer().setAddress(this.server)
				.setConnectionPoolSize(10);
		this.redisson = Redisson.create(config);

	}

	@Produces
	public Redisson produceRedis() {
		return this.redisson;
	}
}

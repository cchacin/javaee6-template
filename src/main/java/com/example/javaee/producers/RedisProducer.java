package com.example.javaee.producers;

import com.example.javaee.qualifiers.ConfigProperty;
import org.redisson.Config;
import org.redisson.Redisson;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RedisProducer {

	@Produces
	public Redisson produceRedis(
			@ConfigProperty("redis.server") final String server) {
		Config config = new Config();
		config.useSingleConnection().setAddress(server)
				.setConnectionPoolSize(10);

		return Redisson.create(config);
	}
}

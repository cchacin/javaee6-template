package org.superbiz.javaee.producers;

import org.superbiz.javaee.qualifiers.ConfigProperty;
import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.codec.SerializationCodec;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RedisProducer {

	@Produces
	public Redisson produceRedis(
			@ConfigProperty("redis.server") final String server) {
		Config config = new Config();
		config.setCodec(new SerializationCodec());
		config.useSingleServer().setAddress(server).setConnectionPoolSize(10);

		return Redisson.create(config);
	}
}
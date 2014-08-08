/**
 * Copyright (C) 2014 Carlos Chacin (cchacin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

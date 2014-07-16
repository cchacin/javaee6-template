package org.superbiz.javaee.producers;

import org.superbiz.javaee.qualifiers.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.Properties;

@ApplicationScoped
public class ConfigPropertyProducer {
	private static Properties props;

	static {
		props = new Properties();
		try {
			props.load(ConfigPropertyProducer.class
					.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Produces
	@ConfigProperty
	public static String produceConfigProperty(InjectionPoint ip) {
		String key = ip.getAnnotated().getAnnotation(ConfigProperty.class)
				.value();

		return props.getProperty(key);
	}
}

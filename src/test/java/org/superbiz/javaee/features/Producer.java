package org.superbiz.javaee.features;

import java.io.IOException;
import java.util.Properties;

public abstract class Producer {

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(Producer.class
					.getResourceAsStream("/test_db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getProperties() {
		return properties;
	}
}

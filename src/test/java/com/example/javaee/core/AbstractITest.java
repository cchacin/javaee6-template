package com.example.javaee.core;

import com.example.javaee.configurations.RedisFileConfig;
import org.apache.deltaspike.core.api.config.PropertyFileConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractITest {
    @Deployment
    public static WebArchive createDeployment() {
	final WebArchive war = ShrinkWrap
	        .create(WebArchive.class)
	        .addPackage("com.example.javaee.configurations")
	        .addPackage("com.example.javaee.entities")
	        .addPackage("com.example.javaee.initializers")
	        .addPackage("com.example.javaee.endpoints")
	        .addPackage("com.example.javaee.interceptors")
	        .addPackage("com.example.javaee.producers")
	        .addPackage("com.example.javaee.qualifiers")
	        .addPackage("com.example.javaee.repositories")
	        .addPackage("com.example.javaee.services")
	        .addAsManifestResource("META-INF/persistence.xml",
	                "persistence.xml")
	        .addAsWebInfResource("resources.xml")
	        .addAsServiceProvider(PropertyFileConfig.class,
	                RedisFileConfig.class)
	        .addAsResource("redis.properties")
	        .addAsLibraries(Dependencies.get());
	return war;
    }
}

package com.example.javaee.repositories;

import com.example.javaee.configurations.RedisFileConfig;
import com.example.javaee.core.Dependencies;
import com.example.javaee.entities.User;
import org.apache.deltaspike.core.api.config.PropertyFileConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class UserRepositoryITest {

	@Inject
	private UserRepository cut;
	private User user;

	@Deployment
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap
				.create(WebArchive.class)
				.addPackage("com.example.javaee.configurations")
				.addPackage("com.example.javaee.entities")
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

	@Before
	@InSequence(1)
	public final void setUp() {
		this.user = new User("cchacin", "Carlos", "Chacin", "pass");

		List<User> toRemove = this.cut.findAll();

		for (User user : toRemove) {
			this.cut.remove(user);
		}
	}

	@Test
	@InSequence(2)
	public final void shouldReturnOneWhenSearchByFirstname() {
		this.cut.save(this.user);
		assertThat(this.cut.findByFirstnameLike("Carlos")).hasSize(1);
	}
}

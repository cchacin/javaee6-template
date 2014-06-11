package com.example.javaee.repositories;

import com.example.javaee.core.Dependencies;
import com.example.javaee.entities.User;
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

@RunWith(Arquillian.class)
public class UserRepositoryITest {

	@Inject
	private UserRepository cut;
	private User user;

	@Deployment
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap
				.create(WebArchive.class)
				.addPackage("com.example.javaee.entities")
				.addPackage("com.example.javaee.events")
				.addPackage("com.example.javaee.interceptors")
				.addPackage("com.example.javaee.producers")
				.addPackage("com.example.javaee.qualifiers")
				.addPackage("com.example.javaee.repositories")
				.addPackage("com.example.javaee.resources")
				.addPackage("com.example.javaee.services")
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml").addAsWebInfResource("resources.xml")
				.addAsResource("config.properties")
				.addAsLibraries(Dependencies.get());
		return war;
	}

	@Before
	@InSequence(1)
	public final void setUp() {
		this.user = new User("one@example.com", "passw0rd", "One name");

		List<User> toRemove = this.cut.find("", 0, 10);

		for (User user : toRemove) {
			this.cut.delete(1L);
		}
	}

	@Test
	@InSequence(2)
	public final void shouldReturnOneWhenSearchByFirstname() {
		this.cut.create(this.user);
		// assertThat(this.cut.findByEmail("one@example.com")).hasSize(1);
	}
}

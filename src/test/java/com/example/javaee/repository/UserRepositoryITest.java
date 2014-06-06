package com.example.javaee.repository;

import com.example.javaee.core.Dependencies;
import com.example.javaee.domain.User;
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
	        .addPackage(UserRepository.class.getPackage())
	        .addPackage(User.class.getPackage())
	        .addPackage("com.example.javaee.interceptors")
	        .addPackage("com.example.javaee.producers")
	        .addAsManifestResource("META-INF/persistence.xml",
	                "persistence.xml").addAsWebInfResource("resources.xml")
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

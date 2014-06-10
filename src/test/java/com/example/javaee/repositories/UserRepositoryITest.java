package com.example.javaee.repositories;

import com.example.javaee.core.AbstractITest;
import com.example.javaee.entities.User;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class UserRepositoryITest extends AbstractITest {

    @Inject
    private UserRepository cut;
    private User user;

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

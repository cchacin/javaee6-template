package com.example.javaee.events;

import com.example.javaee.entities.User;
import com.example.javaee.qualifiers.New;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class NewUserEvent {

	@Inject
	private transient Logger logger;

	public void logNewUser(@Observes @New User newUser) {
		logger.info("New user created " + newUser);
	}
}

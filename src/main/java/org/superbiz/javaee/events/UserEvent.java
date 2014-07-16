package org.superbiz.javaee.events;

import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.qualifiers.Edited;
import org.superbiz.javaee.qualifiers.New;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UserEvent {

	@Inject
	private transient Logger logger;

	public void logNewUser(@Observes @New User newUser) {
		logger.info("New user created " + newUser);
	}

	public void logEditedUser(@Observes @Edited User editedUser) {
		logger.info("User edited " + editedUser);
	}
}

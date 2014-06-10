package com.example.javaee.events;

import com.example.javaee.entities.User;
import com.example.javaee.qualifiers.New;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Slf4j
@ApplicationScoped
public class NewUserEvent {

	public void logNewUser(@Observes @New User newUser) {
		log.info("New user created {}", newUser);
	}
}

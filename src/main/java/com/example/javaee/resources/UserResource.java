package com.example.javaee.resources;

import com.example.javaee.entities.User;
import com.example.javaee.interceptors.LoggingInterceptor;
import com.example.javaee.qualifiers.Loggable;
import com.example.javaee.services.UserService;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/users")
@Loggable
@Interceptors(LoggingInterceptor.class)
@Produces({APPLICATION_JSON, APPLICATION_XML})
@Consumes({APPLICATION_JSON, APPLICATION_XML})
public class UserResource {

	@Inject
	private UserService userService;

	@GET
	public List<User> getUsers() {
		return this.userService.findAll();
	}
}

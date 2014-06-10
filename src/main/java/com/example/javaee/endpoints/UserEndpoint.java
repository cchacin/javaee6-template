package com.example.javaee.endpoints;

import com.example.javaee.entities.User;
import com.example.javaee.qualifiers.Loggable;
import com.example.javaee.interceptors.LoggingInterceptor;
import com.example.javaee.repositories.UserRepository;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Loggable
@Interceptors(LoggingInterceptor.class)
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {

	@Inject
	private UserRepository userRepository;

	@GET
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}
}

package com.example.javaee.resources;

import com.example.javaee.entities.User;
import com.example.javaee.interceptors.LoggingInterceptor;
import com.example.javaee.qualifiers.Loggable;
import com.example.javaee.services.UserService;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/users")
@Loggable
@Interceptors(LoggingInterceptor.class)
@Produces({APPLICATION_JSON, APPLICATION_XML})
@Consumes({APPLICATION_JSON, APPLICATION_XML})
public class UserResource {

	// @Inject
	private UriInfo uriInfo;

	@Inject
	private UserService userService;

	@GET
	public List<User> getUsers() {
		return this.userService.findAll();
	}

	@POST
	public Response saveUser(@Valid final User user) {
		User aUser = null;
		try {
			aUser = this.userService.save(new User("cchacin@groupon.com",
					"aaaaa", "bbbbb"));
		} catch (Exception e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return Response.ok(aUser).build();
	}
}

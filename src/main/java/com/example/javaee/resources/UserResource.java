package com.example.javaee.resources;

import com.example.javaee.entities.User;
import com.example.javaee.interceptors.LoggingInterceptor;
import com.example.javaee.qualifiers.Loggable;
import com.example.javaee.services.IUserService;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/users")
@Loggable
@NoArgsConstructor
@Interceptors(LoggingInterceptor.class)
public class UserResource implements IUserResource {

	// @Inject
	private UriInfo uriInfo;

	private IUserService userService;

	@Inject
	public UserResource(final IUserService userService) {
		this.userService = userService;
	}

	@Override
	public List<User> getUsers() {
		return this.userService.findAll();
	}

	@Override
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

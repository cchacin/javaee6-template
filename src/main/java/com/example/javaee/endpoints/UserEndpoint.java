package com.example.javaee.endpoints;

import com.example.javaee.domain.User;
import com.example.javaee.interceptors.ActionProtocolInterceptor;
import com.example.javaee.interceptors.LogInterceptorBinding;
import com.example.javaee.repository.UserRepository;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@LogInterceptorBinding
@Interceptors(ActionProtocolInterceptor.class)
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {

    @Inject
    private UserRepository userRepository;

    @GET
    public List<User> getUsers() {
	return this.userRepository.findAll();
    }
}

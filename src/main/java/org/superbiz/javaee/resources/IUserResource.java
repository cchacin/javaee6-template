package org.superbiz.javaee.resources;

import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces({APPLICATION_JSON})
@Consumes({APPLICATION_JSON})
public interface IUserResource {
	@GET
	List<UserDTO> getUsers();

	@POST
	Response saveUser(@Valid User user);
}

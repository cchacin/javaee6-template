/**
 * Copyright (C) 2014 Carlos Chacin (cchacin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.javaee.resources;

import lombok.NoArgsConstructor;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;
import org.superbiz.javaee.interceptors.LoggingInterceptor;
import org.superbiz.javaee.qualifiers.Loggable;
import org.superbiz.javaee.services.UserService;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Loggable
@NoArgsConstructor
@Interceptors(LoggingInterceptor.class)
@Produces({APPLICATION_JSON})
@Consumes({APPLICATION_JSON})
public class UserResource {

	private UserService userService;

	@Inject
	public UserResource(final UserService userService) {
		this.userService = userService;
	}

	@GET
	public List<UserDTO> getUsers() {
		return this.userService.findAll();
	}

	@POST
	public Response saveUser(@Valid final User user) {
		UserDTO aUser = null;
		try {
			aUser = this.userService.save(new UserDTO("cchacin@groupon.com",
					"aaaaa", "bbbbb"));
		} catch (Exception e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return Response.ok(aUser).build();
	}
}

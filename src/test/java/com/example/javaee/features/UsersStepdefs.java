package com.example.javaee.features;

import com.example.javaee.entities.User;
import com.example.javaee.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import lombok.Data;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class UsersStepdefs {

	private WebClient client = WebClient
			.create(CommonEndpointsStepDefs.BASE_URL).accept(APPLICATION_JSON)
			.type(APPLICATION_JSON);

	private UserRepository userRepository = new UserRepository();

	@Given("^I have the following users in the database:$")
	public void I_have_the_following_users_in_the_database(List<UserDto> users)
			throws Throwable {

		System.out.println("usersDto " + users);
		for (UserDto user : users) {
			User u = new User(user.email, user.password, user.fullname);
			UserWrapper wp = new UserWrapper();
			wp.setUser(u);
			System.out.println(wp);
			final String postBody = new ObjectMapper().writeValueAsString(wp);
			System.out.println("postBody" + postBody);
			Response response = this.client.path("/users").post(postBody);
			System.out.println(response.getStatus());
		}
	}

	@Data
	@XmlRootElement
	static class UserWrapper {
		User user;
	}

	@Data
	static class UserDto {
		String email;
		String password;
		String fullname;
	}
}

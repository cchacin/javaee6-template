package org.superbiz.javaee.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.superbiz.javaee.entities.dtos.UserDTO;
import org.superbiz.javaee.services.UserService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {

	@Mock
	UserService userService;

	UserResource cut;

	List<UserDTO> users;
	final UserDTO.UserDTOBuilder userBuilder = UserDTO.builder();

	@Before
	public void setUp() {
		this.users = Collections.singletonList(userBuilder.fullname("cchacin").build());
		this.cut = new UserResource(this.userService);
	}

	@Test
	public void shouldReturnAListOfUsers() {

		when(this.userService.findAll()).thenReturn(this.users);

		assertThat(this.cut.getUsers()).isEqualTo(this.users);

		verify(this.userService).findAll();
	}
}

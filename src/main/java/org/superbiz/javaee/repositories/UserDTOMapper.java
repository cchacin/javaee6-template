package org.superbiz.javaee.repositories;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;

import java.util.Date;

public class UserDTOMapper extends SimpleQueryInOutMapperBase<User, UserDTO> {

	@Override
	protected Object getPrimaryKey(final UserDTO dto) {
		return dto.getId();
	}

	@Override
	protected UserDTO toDto(final User user) {
		UserDTO dto = new UserDTO(user.getEmail(), user.getFullname(),
				user.getPassword());
		dto.setId(user.getId());
		if (user.getCreated() != null) {
			dto.setCreated(new Date(user.getCreated().getTime()));
		}
		if (user.getModified() != null) {
			dto.setModified(new Date(user.getModified().getTime()));
		}
		dto.setVersion(user.getVersion());
		return dto;
	}

	@Override
	protected User toEntity(final User user, final UserDTO dto) {
		// User _user = new User(dto.getEmail(), dto.getPassword(),
		// dto.getFullname());

		return user;
	}
}

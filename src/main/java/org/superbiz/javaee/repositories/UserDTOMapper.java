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

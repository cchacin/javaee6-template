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
package org.superbiz.javaee.services;

import lombok.NoArgsConstructor;
import org.redisson.core.RList;
import org.superbiz.javaee.cache.CacheManager;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;
import org.superbiz.javaee.qualifiers.Loggable;
import org.superbiz.javaee.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Loggable
@ApplicationScoped
@NoArgsConstructor
public class UserService {

	private UserRepository userRepository;

	private CacheManager<UserDTO> cacheManager;

	@Inject
	public UserService(final UserRepository userRepository,
			final CacheManager<UserDTO> cacheManager) {
		this.userRepository = userRepository;
		this.cacheManager = cacheManager;
	}

	public List<UserDTO> findAll() {
		RList<UserDTO> users = this.cacheManager.getList(User.FIND_ALL);

		if (users.isEmpty()) {
			List<UserDTO> dbUsers = this.userRepository.findAll(0, 10);

			if (!dbUsers.isEmpty()) {
				users.addAll(dbUsers);
				users.expire(1, TimeUnit.HOURS);
			}
		}

		return users;
	}

	public UserDTO save(final UserDTO user) {
		RList<UserDTO> users = this.cacheManager.getList(user.getIdsKey());
		return new UserDTO();
	}
}

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

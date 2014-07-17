package org.superbiz.javaee.services;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import org.redisson.Redisson;
import org.redisson.core.RList;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;
import org.superbiz.javaee.qualifiers.Loggable;
import org.superbiz.javaee.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Loggable
@ApplicationScoped
@NoArgsConstructor
public class UserService implements IUserService {

	private UserRepository userRepository;

	private Redisson redisson;

	@Inject
	public UserService(final UserRepository userRepository,
			final Redisson redisson) {
		this.userRepository = userRepository;
		this.redisson = redisson;
	}

	@Override
	public List<UserDTO> findAll() {
		RList<UserDTO> users = this.redisson.getList(User.FIND_ALL);

		if (users.isEmpty()) {
			List<User> dbUsers = this.userRepository.findAll(0, 10);

			if (!dbUsers.isEmpty()) {
				users.addAll(Lists.transform(dbUsers,
						new Function<User, UserDTO>() {
							@Override
							public UserDTO apply(final User user) {
								UserDTO dto = new UserDTO(user.getEmail(), user
										.getFullname(), user.getPassword());
								dto.setId(user.getId());
								dto.setCreated(new Date(user.getCreated()
										.getTime()));
								dto.setModified(user.getModified());
								dto.setVersion(user.getVersion());
								return dto;
							}
						}));
				users.expire(1, TimeUnit.HOURS);
			}
		}

		return users;
	}

	@Override
	public User save(final User user) {
		RList<User> users = this.redisson.getList(User.FIND_ALL);
		User aUser = this.userRepository.save(user);
		users.add(aUser);
		users.expire(1, TimeUnit.HOURS);
		return aUser;
	}
}

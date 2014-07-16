package org.superbiz.javaee.services;

import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.qualifiers.Loggable;
import org.superbiz.javaee.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.redisson.Redisson;
import org.redisson.core.RList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
	public List<User> findAll() {
		RList<User> users = this.redisson.getList(User.FIND_ALL);

		if (users.isEmpty()) {
			List<User> dbUsers = this.userRepository.namedFind(User.FIND_ALL,
					0, 10);

			if (!dbUsers.isEmpty()) {
				users.addAll(dbUsers);
				users.expire(1, TimeUnit.HOURS);
			}
		}
		return users;
	}

	@Override
	public User save(final User user) {
		RList<User> users = this.redisson.getList(User.FIND_ALL);
		User aUser = this.userRepository.create(user);
		users.add(aUser);
		users.expire(1, TimeUnit.HOURS);
		return aUser;
	}
}

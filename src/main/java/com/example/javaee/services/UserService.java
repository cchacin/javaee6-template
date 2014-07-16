package com.example.javaee.services;

import com.example.javaee.entities.User;
import com.example.javaee.qualifiers.Loggable;
import com.example.javaee.repositories.UserRepository;
import org.redisson.Redisson;
import org.redisson.core.RList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Loggable
@ApplicationScoped
public class UserService implements IUserService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private Redisson redisson;

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

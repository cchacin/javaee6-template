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
public class UserService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private Redisson redisson;

	public List<User> findAll() {
		RList<User> users = this.redisson.getList("users");

		if (users.isEmpty()) {
			List<User> dbUsers = userRepository.findAll();

			if (!dbUsers.isEmpty()) {
				users.addAll(dbUsers);
				users.expire(1, TimeUnit.MINUTES);
			}
		}
		return users;
	}
}

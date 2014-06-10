package com.example.javaee.services;

import com.example.javaee.entities.User;
import com.example.javaee.repositories.UserRepository;
import org.redisson.Redisson;

import javax.inject.Inject;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private Redisson redisson;

    public List<User> findAll() {
	List<User> users = redisson.getList("users");

	if (users == null || users.isEmpty()) {
	    users = userRepository.findAll();
	}
	return users;
    }
}

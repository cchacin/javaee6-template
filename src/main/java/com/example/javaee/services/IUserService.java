package com.example.javaee.services;

import com.example.javaee.entities.User;

import java.util.List;

public interface IUserService {
	List<User> findAll();
	User save(final User user);
}

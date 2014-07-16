package org.superbiz.javaee.services;

import org.superbiz.javaee.entities.User;

import java.util.List;

public interface IUserService {
	List<User> findAll();
	User save(final User user);
}

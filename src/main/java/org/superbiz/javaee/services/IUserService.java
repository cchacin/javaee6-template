package org.superbiz.javaee.services;

import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;

import java.util.List;

public interface IUserService {
	List<UserDTO> findAll();
	User save(final User user);
}

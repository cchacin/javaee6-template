package com.example.javaee.repositories;

import com.example.javaee.entities.User;
import com.example.javaee.qualifiers.Loggable;

import javax.enterprise.context.ApplicationScoped;

@Loggable
@ApplicationScoped
public class UserRepository extends AbstractRepository<User, Long> {
}

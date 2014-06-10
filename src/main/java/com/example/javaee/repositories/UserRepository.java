package com.example.javaee.repositories;

import com.example.javaee.entities.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {
    User findByOptionalUsername(String username);

    List<User> findByFirstnameLike(String firstname);
}

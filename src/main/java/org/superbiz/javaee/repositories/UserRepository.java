package org.superbiz.javaee.repositories;

import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.qualifiers.Loggable;

import javax.enterprise.context.ApplicationScoped;

@Loggable
@ApplicationScoped
public class UserRepository extends AbstractRepository<User, Long> {
}

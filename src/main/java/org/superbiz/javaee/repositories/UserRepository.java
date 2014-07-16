package org.superbiz.javaee.repositories;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.qualifiers.Loggable;

import javax.enterprise.context.ApplicationScoped;

@Loggable
@Repository
@ApplicationScoped
public interface UserRepository extends EntityRepository<User, Long> {
}

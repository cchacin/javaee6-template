package org.superbiz.javaee.repositories;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.entities.dtos.UserDTO;
import org.superbiz.javaee.qualifiers.Loggable;

import javax.enterprise.context.ApplicationScoped;

@Loggable
@Repository(forEntity = User.class)
@MappingConfig(UserDTOMapper.class)
@ApplicationScoped
public interface UserRepository extends EntityRepository<UserDTO, Long> {
}

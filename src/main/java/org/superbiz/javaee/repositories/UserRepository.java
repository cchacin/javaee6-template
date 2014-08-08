/**
 * Copyright (C) 2014 Carlos Chacin (cchacin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

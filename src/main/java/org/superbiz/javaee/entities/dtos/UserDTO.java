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
package org.superbiz.javaee.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.superbiz.javaee.cache.Cacheable;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends DatedModelDTO implements Cacheable {
	private String email;
	private String fullname;
	private String password;

	@Override
	public String getElementsKey() {
		return "users:all";
	}

	@Override
	public String getIdsKey() {
		return "users:ids";
	}
}

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
package org.superbiz.javaee.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({@NamedQuery(name = User.FIND_ALL, query = "select u from User u")})
public class User extends DatedModel {
	private static final long serialVersionUID = 3810638653455000233L;

	public static final String FIND_ALL = "users.all";

	@NotNull
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;

	@NotNull
	@Size(min = 5, max = 15)
	private String password;

	@NotNull
	@Size(min = 3, max = 15)
	private String fullname;
}

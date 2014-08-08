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
package org.superbiz.javaee.events;

import org.slf4j.Logger;
import org.superbiz.javaee.entities.User;
import org.superbiz.javaee.qualifiers.Edited;
import org.superbiz.javaee.qualifiers.New;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class UserEvent {

	@Inject
	private transient Logger logger;

	public void logNewUser(@Observes @New User newUser) {
		logger.info("New user created: {}", newUser);
	}

	public void logEditedUser(@Observes @Edited User editedUser) {
		logger.info("User edited: {}", editedUser);
	}
}

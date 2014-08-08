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
package org.superbiz.javaee.interceptors;

import org.slf4j.Logger;
import org.superbiz.javaee.qualifiers.Loggable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Loggable
@Interceptor
public class LoggingInterceptor {

	@Inject
	private transient Logger logger;

	@AroundInvoke
	protected Object log(final InvocationContext ic) throws Exception {
		logger.info("Entering >>> {}-{}", ic.getTarget().getClass().getName(),
				ic.getMethod().getName());
		if (logger.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder("[");
			for (Object obj : ic.getParameters()) {
				sb.append(obj.toString());
				sb.append(", ");
			}
			sb.append("]");
			logger.debug("Parameters: {}", sb.toString());
		}
		try {
			return ic.proceed();
		} finally {
			logger.info("Exiting <<< {}-{}", ic.getTarget().getClass()
					.getName(), ic.getMethod().getName());
		}
	}
}

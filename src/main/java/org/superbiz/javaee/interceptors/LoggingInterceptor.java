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

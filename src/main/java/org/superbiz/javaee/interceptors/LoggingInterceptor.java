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
	private Logger logger;

	@AroundInvoke
	protected Object log(final InvocationContext ic) throws Exception {
		logger.info(">>> " + ic.getTarget().getClass().getName() + "-"
				+ ic.getMethod().getName());
		StringBuilder sb = new StringBuilder("[");
		for (Object obj : ic.getParameters()) {
			sb.append(obj.toString());
			sb.append(", ");
		}
		sb.append("]");
		logger.info("parameters: {}", sb.toString());
		try {
			return ic.proceed();
		} finally {
			logger.info("<<< {}-{}", ic.getTarget().getClass().getName(), ic
					.getMethod().getName());
		}
	}
}

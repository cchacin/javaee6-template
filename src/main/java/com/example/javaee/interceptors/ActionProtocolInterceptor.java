package com.example.javaee.interceptors;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LogInterceptorBinding
public class ActionProtocolInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    protected Object log(final InvocationContext ic) throws Exception {
	StringBuilder sb = new StringBuilder("[");
	for (Object obj : ic.getParameters()) {
	    sb.append(obj.toString());
	    sb.append(", ");
	}
	sb.append("]");
	log.info("invoked {} with method {} and parameters: {}", ic.getTarget()
	        .toString(), ic.getMethod().getName(), sb.toString());
	return ic.proceed();
    }
}

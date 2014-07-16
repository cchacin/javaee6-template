package org.superbiz.javaee.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

@ApplicationScoped
public class LoggerProducer {
	@Produces
	public Logger produceLogger(InjectionPoint ip) {
		return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
	}
}

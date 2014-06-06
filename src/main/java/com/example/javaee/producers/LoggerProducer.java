package com.example.javaee.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
    @Produces
    public Logger createLogger(InjectionPoint ip) {
	return LoggerFactory.getLogger(ip.getMember().getDeclaringClass()
	        .getName());
    }
}

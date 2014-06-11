package com.example.javaee.providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
	@Override
	public ObjectMapper getContext(Class<?> type) {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
				true).configure(
				DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
				true);
		return objectMapper;
	}
}

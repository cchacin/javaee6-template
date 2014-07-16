package org.superbiz.javaee.providers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
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
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig()
				.getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
		objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN,
				true);
		objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,
				true);

		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
				true);
		objectMapper.configure(
				DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
				true);
		objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
}

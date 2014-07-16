package com.example.javaee.exceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper
		implements
			ExceptionMapper<ConstraintViolationException> {
	@Override
	public Response toResponse(final ConstraintViolationException e) {

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(e.getConstraintViolations())
				.type(MediaType.APPLICATION_JSON).build();

	}
}

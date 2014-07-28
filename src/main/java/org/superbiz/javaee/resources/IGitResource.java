package org.superbiz.javaee.resources;

import org.superbiz.javaee.producers.GitMetadataProducer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces({APPLICATION_JSON})
@Consumes({APPLICATION_JSON})
public interface IGitResource {

	@GET
	GitMetadataProducer gitStatus();
}

package org.superbiz.javaee.resources;

import org.superbiz.javaee.producers.GitMetadataProducer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/git-status")
@Produces({APPLICATION_JSON})
public class GitResource {

	@Inject
	private GitMetadataProducer gitMetadataProvider;

	@GET
	public GitMetadataProducer gitStatus() {
		return this.gitMetadataProvider;
	}
}

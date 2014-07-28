package org.superbiz.javaee.resources;

import lombok.NoArgsConstructor;
import org.superbiz.javaee.entities.dtos.GitMetadata;
import org.superbiz.javaee.producers.GitMetadataProducer;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/git-status")
@Produces({APPLICATION_JSON})
@NoArgsConstructor
public class GitResource implements IGitResource {

	private GitMetadataProducer gitMetadataProducer;

	@Inject
	public GitResource(final GitMetadataProducer gitMetadataProducer) {
		this.gitMetadataProducer = gitMetadataProducer;
	}

	@Override
	public GitMetadata gitStatus() {
		return this.gitMetadataProducer.metadata();
	}
}

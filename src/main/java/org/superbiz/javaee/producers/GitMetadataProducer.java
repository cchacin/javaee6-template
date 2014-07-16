package org.superbiz.javaee.producers;

import lombok.Data;
import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
@ApplicationScoped
public class GitMetadataProducer {

	@Inject
	@ConfigProperty(name = "git.branch")
	private String branch;

	@Inject
	@ConfigProperty(name = "git.build.time")
	private String buildTime;

	@Inject
	@ConfigProperty(name = "git.build.user.email")
	private String buildUserEmail;

	@Inject
	@ConfigProperty(name = "git.build.user.name")
	private String buildUserName;

	@Inject
	@ConfigProperty(name = "git.commit.id")
	private String commitId;

	@Inject
	@ConfigProperty(name = "git.commit.id.abbrev")
	private String commitIdAbbrev;

	@Inject
	@ConfigProperty(name = "git.commit.message.short")
	private String commitMessageShort;

	@Inject
	@ConfigProperty(name = "git.commit.message.full")
	private String commitMessageFull;

	@Inject
	@ConfigProperty(name = "git.commit.time")
	private String commitTime;

	@Inject
	@ConfigProperty(name = "git.commit.user.email")
	private String commitUserEmail;

	@Inject
	@ConfigProperty(name = "git.commit.user.name")
	private String commitUserName;

	@Inject
	@ConfigProperty(name = "git.commit.id.describe")
	private String describe;

}

/**
 * Copyright (C) 2014 Carlos Chacin (cchacin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.javaee.producers;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.superbiz.javaee.entities.dtos.GitMetadata;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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

	public GitMetadata metadata() {
		return new GitMetadata(this.branch, this.buildTime,
				this.buildUserEmail, this.buildUserName, this.commitId,
				this.commitIdAbbrev, this.commitMessageShort,
				this.commitMessageFull, this.commitTime, this.commitUserEmail,
				this.buildUserName, this.describe);
	}

}

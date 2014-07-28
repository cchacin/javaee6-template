package org.superbiz.javaee.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class GitMetadata {

	private String branch;

	private String buildTime;

	private String buildUserEmail;

	private String buildUserName;

	private String commitId;

	private String commitIdAbbrev;

	private String commitMessageShort;

	private String commitMessageFull;

	private String commitTime;

	private String commitUserEmail;

	private String commitUserName;

	private String describe;

}

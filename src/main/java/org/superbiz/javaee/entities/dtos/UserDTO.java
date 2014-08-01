package org.superbiz.javaee.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.superbiz.javaee.cache.Cacheable;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends DatedModelDTO implements Cacheable {
	private String email;
	private String fullname;
	private String password;

	@Override
	public String getElementsKey() {
		return "users:all";
	}

	@Override
	public String getIdsKey() {
		return "users:ids";
	}
}

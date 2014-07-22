package org.superbiz.javaee.features;

import org.junit.Before;
import org.junit.Test;
import org.superbiz.javaee.entities.dtos.UserDTO;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {

	private UserDTO cut;

	@Before
	public void init() {
		this.cut = new UserDTO("cchacin@superbiz.org", "Carlos", "passw0rd");
		this.cut.setId(1L);
		this.cut.setVersion(0L);
		this.cut.setCreated(new Date(12341234));
	}

	@Test
	public void sampleTest() {
		assertThat(this.cut.getEmail()).isEqualTo("cchacin@superbiz.org");
	}
}

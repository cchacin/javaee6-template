package org.superbiz.javaee.producers;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

public class GitFileConfig implements PropertyFileConfig {

	private static final long serialVersionUID = -234523454545778L;

	@Override
	public String getPropertyFileName() {
		return "git.properties";
	}
}

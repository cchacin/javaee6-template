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

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.sql.DataSource;

@Singleton
@Startup
public class DbMigrator {
	@Inject
	private transient Logger logger;

	@Resource(name = "jdbc/javaeeSampleDatabaseUnmanaged")
	private DataSource dataSource;

	@PostConstruct
	private void onStartup() {
		if (dataSource == null) {
			logger.error("no datasource found to execute the db migrations!");
			throw new EJBException(
					"no datasource found to execute the db migrations!");
		}

		Flyway flyway = new Flyway();
		flyway.setInitOnMigrate(true);
		flyway.setDataSource(dataSource);
		for (MigrationInfo i : flyway.info().all()) {
			logger.info("migrate task: {} : {} from file: {}", i.getVersion(),
					i.getDescription(), i.getScript());
		}
		flyway.migrate();
	}
}

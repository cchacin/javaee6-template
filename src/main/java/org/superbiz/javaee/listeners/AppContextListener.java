package org.superbiz.javaee.listeners;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import io.netty.util.ThreadDeathWatcher;
import io.netty.util.concurrent.FastThreadLocal;
import org.redisson.Redisson;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.util.Enumeration;

@WebListener
public class AppContextListener implements ServletContextListener {

	@Inject
	private transient Logger logger;

	@Inject
	private transient Redisson redisson;

	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		logger.info("Initializing application context: {}", servletContextEvent
				.getServletContext().getContextPath());
	}

	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		logger.info("Destroying application context: {}", servletContextEvent
				.getServletContext().getContextPath());
		try {
			logger.info("Shutting down AbandonedConnection");
			AbandonedConnectionCleanupThread.shutdown();
		} catch (Throwable t) {
			logger.error("Exception shutting down AbandonedConnection: {}", t);
		}

		// This manually deregisters JDBC driver, which prevents Tomcat 7 from
		// complaining about memory leaks
		Enumeration<Driver> drivers = java.sql.DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			java.sql.Driver driver = drivers.nextElement();
			logger.info("Deregistering JDBC driver: {}", driver);
			try {
				java.sql.DriverManager.deregisterDriver(driver);
			} catch (Throwable t) {
				logger.error("Exception deregistering drivers: {}", t);
			}
		}

		try {
			logger.info("Shutting down Redisson: {}", redisson.getConfig());
			redisson.shutdown();
		} catch (Exception e) {
			logger.error("Error shutting down Redisson: {}", e);
		}

		try {
			FastThreadLocal.removeAll();
			FastThreadLocal.destroy();
        } catch (Exception e) {
			logger.error("Fatal error: {}", e);
		}
	}
}

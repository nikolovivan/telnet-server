/**
 * 
 */
package com.ivan.nikolov.telnet.server.logger;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that initializes the log4j logger.
 * 
 * @author Ivan Nikolov
 * 
 */
public class Log4jInitializer {

	private static Logger logger = LoggerFactory.getLogger(Log4jInitializer.class);
	private static final String LOG4J_CONFIG = "log4j.properties";

	public static void initialize() {
		InputStream is = Log4jInitializer.class.getClassLoader().getResourceAsStream(Log4jInitializer.LOG4J_CONFIG);

		if (is != null) {
			PropertyConfigurator.configure(is);
			Log4jInitializer.logger.info("Logger successfully initialized.");
		} else {
			Log4jInitializer.logger.warn("Could not load log4j properties configuration.");
		}
	}
}

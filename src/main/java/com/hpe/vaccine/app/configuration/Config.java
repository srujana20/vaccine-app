package com.hpe.vaccine.app.configuration;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.hpe.vaccine.app.exception.ConfigurationException;
import com.hpe.vaccine.app.exception.ErrorMessage;


/**
 * A Config utility class to read properties configured.
 * 
 * @author Srujana Chipilla
 *
 */
public class Config {
	/*
	 * Logger for logging info
	 */
	private static Logger LOGGER = Logger.getLogger(Config.class.getName());

	/*
	 * File containing config properties
	 */
	private Properties configFile;

	/**
	 * @Constructor
	 * @param configFileName - name of the config file
	 * @throws ConfigurationException - throws an exception if file read fails
	 */
	public Config(String configFileName) throws ConfigurationException {
		configFile = new java.util.Properties();
		try {
			LOGGER.info("Reading config properties");
			configFile.load(this.getClass().getClassLoader().getResourceAsStream(configFileName));
		} catch (Exception ex) {
			throw new ConfigurationException(
					ErrorMessage.CONFIGURATION_FILE_READ_ERROR + ", Reason: " + ex.getMessage());
		}
	}

	/**
	 * Returns the property value of the config property from the configuration
	 * 
	 * @param key          - property key
	 * @param defaultValue - sets default value if property key's value is blank or
	 *                     not provided
	 * @return value - property value
	 * @throws ConfigurationException
	 */
	public String getProperty(String key, String defaultValue) throws ConfigurationException {
		LOGGER.info("Fetching conguration for " + key);

		String value = null;
		try {
			value = this.configFile.getProperty(key, defaultValue);
		} catch (Exception e) {
			throw new ConfigurationException(ErrorMessage.FAILED_TO_FIND_PROPERTY + ", Reason: " + e.getMessage());
		}
		// returns default value if property value as defined in config is empty or null
		return value = value != null && value.length() > 0 ? value : defaultValue;
	}

}

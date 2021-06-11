package com.hpe.vaccine.app.exception;

/**
 * An custom exception class to process all configuration exceptions thrown
 * during configuring the application
 * 
 * @author Srujana Chipilla
 *
 */
public class ConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message - error message
	 */
	public ConfigurationException(String message) {
		super(String.format(message));

	}

}

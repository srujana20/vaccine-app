package com.hpe.vaccine.app.exception;

/**
 * A custom exception class to handle exceptions thrown during processing of
 * data
 * 
 * @author Srujana Chipilla
 *
 */
public class ProcessingException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message - error message
	 */
	public <T> ProcessingException(String message) {
		super(String.format("Failed to process the data", message));

	}

}

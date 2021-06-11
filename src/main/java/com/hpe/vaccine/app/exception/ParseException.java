package com.hpe.vaccine.app.exception;

/**
 * A custom exception class to handle exceptions thrown during paring the input
 * data
 * 
 * @author Srujana Chipilla
 *
 */
public class ParseException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message - error message
	 */
	public ParseException(String message) {
		super(String.format("Failed to parse data", message));

	}

}

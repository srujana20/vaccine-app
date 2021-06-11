package com.hpe.vaccine.app.parser;

import com.hpe.vaccine.app.exception.ParseException;

/**
 * Abstract class to handle data processing
 * @author Srujana Chipilla
 *
 */
public abstract class DataParser {

	private String fileName;

	public abstract void processData() throws ParseException;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

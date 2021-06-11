package com.hpe.vaccine.app.exception;

/**
 * Error message info class
 * 
 * @author Srujana Chipilla
 *
 */
public interface ErrorMessage {

	String LOAD_CONFIGURATION_ERROR = "Failed to load configuration";
	String PARSE_EXCEPTION = "Failed to parse data";
	String GROUPING_DATA_ERROR = "Failed to group data";
	String CONFIGURATION_FILE_READ_ERROR = "Failed to read config file";
	String FAILED_TO_FIND_PROPERTY = "Failed to find property form configuration";
	String READ_VACCINATION_INFO_ERROR = "Failed to read Vaccination centre info";
	String READ_POPULATION_INFO_ERROR = "Failed to read population data";
	String PROCESS_VACCINATION_INFO_ERROR = "Failed to process Vaccination centre info";
	String PROCESS_POPULATION_INFO_ERROR = "Failed to process Population data";
	String FAILED_TO_PROCESS_DATA = "Failed to group population data to vaccination centres";
	String SHORTEST_DISTANCE_ERROR = "Failed to find shortest distance";

}

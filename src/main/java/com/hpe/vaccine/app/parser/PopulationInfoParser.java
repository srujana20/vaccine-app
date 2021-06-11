package com.hpe.vaccine.app.parser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.log4j.Logger;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.vaccine.app.exception.ErrorMessage;
import com.hpe.vaccine.app.exception.ParseException;
import com.hpe.vaccine.app.model.Person;

/**
 * This class handles the parsing of population data and processing it for
 * further analysis.
 * 
 * @author Srujana Chipilla
 *
 */
public class PopulationInfoParser extends DataParser {

	/*
	 * Logger
	 */
	static Logger LOGGER = Logger.getLogger(PopulationInfoParser.class.getName());

	/*
	 * Collection of population data
	 */
	List<Person> populationData = null;

	/*
	 * Utility to map json to objects and vice-versa
	 */
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Constructor
	 * 
	 * @param fileName - name of the file containing population data to be processed
	 */
	public PopulationInfoParser(String fileName) {
		this.setFileName(fileName);
	}

	/**
	 * This method reads the file contents for population data and maps it to a
	 * specific collection or processing
	 */
	@Override
	public void processData() throws ParseException {
		String data = this.readFile();
		populationData = (List<Person>) this.mapToObject(data);

	}
	
	/**
	 * Reads the population information from file. Assumptions: 
	 * -- The file is in a JSON format 
	 * -- The file is present in the class path under src/main/resources
	 * 
	 * @return parsed data
	 * @throws ParseException - if file read fails
	 */
	private String readFile() throws ParseException {
		LOGGER.info("Reading population data");
		InputStream stream = getClass().getResourceAsStream("/" + this.getFileName());
		try {
			return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException(ErrorMessage.READ_VACCINATION_INFO_ERROR + ",Reason: " + e.getMessage());
		}
	}

	/**
	 * This method maps the raw data into a collection for further analysis.
	 * 
	 * @param jsonData
	 * @return List<Person>
	 * @throws ParseException
	 */
	private List<Person> mapToObject(Object jsonData) throws ParseException {
		LOGGER.info("Processing population data");

		try {
			return mapper.readValue((String) jsonData, new TypeReference<List<Person>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException(ErrorMessage.PROCESS_POPULATION_INFO_ERROR + ",Reason: " + e.getMessage());

		}

	}


	/**
	 * Getter to fetch population info colletion
	 * @return
	 */
	public List<Person> getPopulationInfo() {
		return this.populationData;
	}

}

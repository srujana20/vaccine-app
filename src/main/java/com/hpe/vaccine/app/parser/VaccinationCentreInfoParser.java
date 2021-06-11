package com.hpe.vaccine.app.parser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.hpe.vaccine.app.exception.ErrorMessage;
import com.hpe.vaccine.app.exception.ParseException;
import com.hpe.vaccine.app.model.VaccinationCentre;


public class VaccinationCentreInfoParser extends DataParser {

	static Logger LOGGER = Logger.getLogger(VaccinationCentreInfoParser.class.getName());

	Map<String, VaccinationCentre> vaccinationCentreInfo = null;

	public VaccinationCentreInfoParser(String fileName) {
		this.setFileName(fileName);
	}
	
	/**
	 * This method reads the file contents for vaccination centres data and maps it to a
	 * specific collection or processing
	 */
	@Override
	public void processData() throws ParseException {
		Stream<String> data = this.readFile();
		this.vaccinationCentreInfo = this.mapToObject(data);
	}

	/**
	 * Reads the vaccination information from file. 
	 * Assumptions: 
	 * --The file is in a CSV format 
	 * -- The file is present in the class path under src/main/resources
	 * 
	 * @return parsed data
	 * @throws ParseException - if file read fails
	 */
	private Stream<String> readFile() throws ParseException {
		LOGGER.info("Reading vaccination centre information");
		Stream<String> lines;
		try {
			lines = Files.lines(Paths.get(ClassLoader.getSystemResource(this.getFileName()).toURI()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException(ErrorMessage.READ_VACCINATION_INFO_ERROR + ",Reason: " + e.getMessage());
		}

		return lines;

	}

	/**
	 * This method maps the raw data into a collection for further analysis.
	 * 
	 * @param csv content as Stream<String>
	 * @return Map<String, VaccinationCentre>>
	 * @throws ParseException
	 */
	private Map<String, VaccinationCentre> mapToObject(Object jsonData) throws ParseException {
		LOGGER.info("Processing vaccination centre information");
		Map<String, VaccinationCentre> VaccinationcentreData;
		try {
			VaccinationcentreData = ((Stream<String>) jsonData).map(line -> line.split(","))
					.collect(Collectors.toMap(line -> line[0], line -> new VaccinationCentre(line[0],
							Double.parseDouble(line[1].trim()), Double.parseDouble(line[2]))));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException(ErrorMessage.PROCESS_VACCINATION_INFO_ERROR + ",Reason: " + e.getMessage());
		}

		return VaccinationcentreData;
	}

	/**
	 * Getter to fetch population info colletion
	 * @return
	 */
	public Map<String, VaccinationCentre> getVaccinationInfo() {
		return this.vaccinationCentreInfo;
	}

}

package com.hpe.vaccine.app;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hpe.vaccine.app.configuration.Config;
import com.hpe.vaccine.app.exception.ConfigurationException;
import com.hpe.vaccine.app.exception.ErrorMessage;
import com.hpe.vaccine.app.exception.ParseException;
import com.hpe.vaccine.app.model.Constants;
import com.hpe.vaccine.app.parser.DataParser;
import com.hpe.vaccine.app.parser.PopulationInfoParser;
import com.hpe.vaccine.app.parser.VaccinationCentreInfoParser;
import com.hpe.vaccine.app.util.AppUtils;



/**
 * Vaccination Application
 * 
 * @author Srujana Chipilla
 *
 */
public class VaccinationApplication {

	/*
	 * Logger
	 */
	static Logger LOGGER = Logger.getLogger(VaccinationApplication.class.getName());

	/*
	 * AppUtils object
	 */
	static AppUtils utils = new AppUtils();

	public static void main(String[] args) {
		DataParser vaccinationInfoParser = null;
		DataParser populationInfoParser = null;
		try {

			LOGGER.info("PREPARING TO CONFIGURING THE APPLICATION\n");
			Config config = new Config(Constants.CONFIG_FILE_NAME);

			// fetch files name of vaccination info and population data files from config
			vaccinationInfoParser = new VaccinationCentreInfoParser(
					config.getProperty(Constants.VACCINATION_centres_FILE_NAME_PROPKEY, "vaccination-centres.txt"));

			populationInfoParser = new PopulationInfoParser(
					config.getProperty(Constants.POPULATION_INFO_FILE_NAME_PROPKEY, "vaccination-centres.txt"));

			LOGGER.info("SUCCESSFULLY CONFIGURED APPLICATION\n");

			LOGGER.info("PROCESSING INPUT DATA\n");

			// process vaccination info and population data
			vaccinationInfoParser.processData();
			populationInfoParser.processData();

			LOGGER.info("SUCCESSFULLY PROCCESS DATA\n");

			LOGGER.info("GROUPING DATA");

			// process the information to group persons with the nearest vaccination
			// centre
			utils.groupPopulation(((PopulationInfoParser) populationInfoParser).getPopulationInfo(),
					((VaccinationCentreInfoParser) vaccinationInfoParser).getVaccinationInfo());

		} catch (Exception e1) {
			e1.getMessage();
			handleException(e1, e1.getMessage());
		}

	}

	private static void handleException(Exception e, String message) {
		LOGGER.error(message);
		LOGGER.error("Exiting application");
		e.printStackTrace();
		System.exit(1);
	}

}

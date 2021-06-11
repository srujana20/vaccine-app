package com.hpe.vaccine.app.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.vaccine.app.comparator.AgeComparator;
import com.hpe.vaccine.app.exception.ErrorMessage;
import com.hpe.vaccine.app.exception.ProcessingException;
import com.hpe.vaccine.app.model.Person;
import com.hpe.vaccine.app.model.VaccinationCentre;

/**
 * Utility class with all distance calculations and grouping to map person to
 * his nearest vaccination centre
 * 
 * @author Srujana Chipilla
 *
 */
public class AppUtils {

	/*
	 * Logger
	 */
	static Logger LOGGER = Logger.getLogger(AppUtils.class.getName());

	/**
	 * This method groups the population data ordered by their age in descending
	 * order and maps them to their nearest vaccination centre
	 * 
	 * Each person's location is compared with the distance to all the available
	 * vaccination centres and the shortest of them is considered. Series of steps :
	 * 1. Loop through the vaccination centres and findDistance between person's address and vaccination centre's address.
	 * 2. Find the shortest distance among the listed distances from above.
	 * 3. Assign the person to that vaccination centre. 
	 * 4. Sort the collection by Age using AgeComparator. 
	 * 5. Collect the resulted collection grouping the by the vaccination centre assigned.
	 * 
	 * @param populationInfo  - population data read from file
	 * @param vaccinationInfo - vaccination centres data read from file
	 * @return Map<String, List<Person>> -> A map with key as vaccination centre
	 *         name and value as list of persons mapped to that vaccination centre
	 * @throws Exception
	 */
	public Map<String, List<Person>> groupPopulation(List<Person> populationInfo,
			Map<String, VaccinationCentre> vaccinationInfo) throws Exception {

		Map<String, List<Person>> data;

		try {
			data = populationInfo.stream().map(t -> {
				try {
					return findNearestVaccinationCentre(t, vaccinationInfo);
				} catch (Exception e) {
					// log the error reason for failed record and continue
					LOGGER.error("Failed to find shortest distance, skipping the record");
				}
				return t;
			}).sorted(new AgeComparator()).collect(Collectors.groupingBy(Person::getVaccinationCentre));
			printGroupedData(data);
		} catch (Exception e) {
			throw new ProcessingException(ErrorMessage.FAILED_TO_PROCESS_DATA + " Reason: " + e.getMessage());
		}

		return data;
	}

	/**
	 * This method finds the nearest vaccination centre to a person's location.
	 * 
	 * Loops through the available vaccination centres list and finds the distance
	 * between person's location and each vaccination centre. Minimum of the
	 * distances is considered as the nearest vaccination centre.
	 * 
	 * @param t
	 * @param VaccinationcentreData
	 * @return
	 * @throws ProcessingException
	 */
	public Person findNearestVaccinationCentre(Person person, Map<String, VaccinationCentre> VaccinationcentreData)
			throws ProcessingException {
		Double minDistance = Double.MAX_VALUE;
		String vaccinationCentreName = null;
		for (String key : VaccinationcentreData.keySet()) {
			Double distance = getDistanceInKm(person.getLatitude(), person.getLongitude(),
					VaccinationcentreData.get(key).getLatitude(), VaccinationcentreData.get(key).getLongitude());
			if (minDistance > distance) {
				vaccinationCentreName = key;
				minDistance = distance;
			}
		}
		if (vaccinationCentreName == null) {
			throw new ProcessingException(ErrorMessage.SHORTEST_DISTANCE_ERROR);
		}
		person.setVaccinationCentre(vaccinationCentreName);
		return person;
	}

	/**
	 * Finds the distance between two points, denoted by latitude and longitude.
	 * 
	 * @param lat1 - latitude of 1st location
	 * @param lon1 - longitude of 1st location
	 * @param lat2 - latitude of 2nd location
	 * @param lon2 - longitude of 2nd location
	 * @return
	 */
	public double getDistanceInKm(double lat1, double lon1, double lat2, double lon2) {
		int radiusOfEarth = 6371; // Radius of the earth in km
		double differenceInLatitudes = degreeToRadian(lat2 - lat1); // difference between two latitudes in radians
		double differenceInLongitudes = degreeToRadian(lon2 - lon1); // difference between two longitudes in radians
		double distance = Math.sin(differenceInLatitudes / 2) * Math.sin(differenceInLatitudes / 2)
				+ Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) * Math.sin(differenceInLongitudes / 2)
						* Math.sin(differenceInLongitudes / 2);
		double c = 2 * Math.atan2(Math.sqrt(distance), Math.sqrt(1 - distance));
		double distanceInKm = radiusOfEarth * c; // Distance in km
		return distanceInKm;
	}

	/**
	 * Converts degress to radian
	 * 
	 * @param deg - degree value to be converted
	 * @return - the radian variant of the degree value
	 */
	public double degreeToRadian(Double deg) {
		return deg * (Math.PI / 180);
	}

	/**
	 * Log the resultant data
	 * 
	 * @param data - result to be logged
	 * @throws JsonProcessingException
	 */
	public static void printGroupedData(Map<String, List<Person>> data) throws JsonProcessingException {
		LOGGER.info("Vaccination centres with the mapped population list can be found below");
		LOGGER.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(data));
	}
}

package com.hpe.vaccine.app.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hpe.vaccine.app.exception.ProcessingException;
import com.hpe.vaccine.app.model.Person;
import com.hpe.vaccine.app.model.VaccinationCentre;
import com.hpe.vaccine.app.parser.DataParser;
import com.hpe.vaccine.app.parser.PopulationInfoParser;
import com.hpe.vaccine.app.parser.VaccinationCentreInfoParser;


public class AppUtilTest {

	@DisplayName("testGetDistanceInKm: Successfully returns the distance between 2 points")
	@Test
	public void testGetDistanceInKm() {
		AppUtils utils = new AppUtils();
		double distance = utils.getDistanceInKm(53.09402405506328, -8.020019531250002, 53.298810877564875,
				-8.997003657335881);
		assertEquals(distance, 68.9488415191973);
	}

	@DisplayName("testGetDistanceInKmWithZero: Successfulyl returns distance between 2 points with 0,0 co-ords as 0")
	@Test
	public void testGetDistanceInKmWithZero() {
		AppUtils utils = new AppUtils();
		double distance = utils.getDistanceInKm(0, 0, 0, 0);
		assertEquals(distance, 0);
	}

	@DisplayName("testGetDistanceInKmBetweenSamePoints: Successfully returns distance between two same points as 0")
	@Test
	public void testGetDistanceInKmBetweenSamePoints() {
		AppUtils utils = new AppUtils();
		double distance = utils.getDistanceInKm(53.09402405506328, -8.020019531250002, 53.09402405506328,
				-8.020019531250002);
		assertEquals(distance, 0);
	}

	@DisplayName("testDegreeToRadian: Successfully converts degrees to corresponding radians")
	@Test
	public void testDegreeToRadian() {
		AppUtils utils = new AppUtils();
		double valueInRadians = utils.degreeToRadian(53.09402405506328);
		assertEquals(valueInRadians, 0.9266655328939253);
	}

	@DisplayName("testDegreeToRadianWith0: Returns 0 radians")
	@Test
	public void testDegreeToRadianWith0() {
		AppUtils utils = new AppUtils();
		double valueInRadians = utils.	degreeToRadian(0.0);
		assertEquals(valueInRadians, 0);
	}

	@DisplayName("testfindNearestVaccinationCentre: Returns the nearest vaccination centre")
	@Test
	public void testfindNearestVaccinationCentre() throws Exception {
		DataParser popParser = new PopulationInfoParser("People.txt");
		DataParser vacParser = new VaccinationCentreInfoParser("vaccination-centres.txt");
		AppUtils utils = new AppUtils();
		popParser.processData();
		vacParser.processData();
		Person data = utils.findNearestVaccinationCentre(((PopulationInfoParser) popParser).getPopulationInfo().get(0),
				((VaccinationCentreInfoParser) vacParser).getVaccinationInfo());
		assertEquals(data.getVaccinationCentre(), "Galway Racecourse");
	}

	@DisplayName("testfindNearestVaccinationCentreWithInvalid: Fails to process data and throws exception")
	@Test
	public void testfindNearestVaccinationCentreWithInvalid() throws Exception {
		AppUtils utils = new AppUtils();
		try {
			utils.findNearestVaccinationCentre(null, null);
		} catch (Exception e) {
			assertNotNull(e);			
		}
	}

	@DisplayName("testfindNearestVaccinationCentreWithEmpty: Fails to process data and throws exception")
	@Test
	public void testfindNearestVaccinationCentreWithEmpty() throws Exception {
		DataParser popParser = new PopulationInfoParser("People.txt");
		DataParser vacParser = new VaccinationCentreInfoParser("vaccination-centres.txt");
		AppUtils utils = new AppUtils();
		popParser.processData();
		vacParser.processData();
		try {
			utils.findNearestVaccinationCentre(((PopulationInfoParser) popParser).getPopulationInfo().get(0),
					new HashMap<String, VaccinationCentre>());
		} catch (ProcessingException ex) {
			assertNotNull(ex);
		}
	}

	@DisplayName("testGroupPopulation: Returns the population data grouped by vaccination centres and the population data sorted by age in desc order")
	@Test
	public void testGroupPopulation() throws Exception {
		DataParser popParser = new PopulationInfoParser("People.txt");
		DataParser vacParser = new VaccinationCentreInfoParser("vaccination-centres.txt");
		AppUtils utils = new AppUtils();
		popParser.processData();
		vacParser.processData();
		Map<String, List<Person>> data = utils.groupPopulation(((PopulationInfoParser) popParser).getPopulationInfo(),
				((VaccinationCentreInfoParser) vacParser).getVaccinationInfo());
		assertEquals(data.get("Galway Racecourse").size(), 1);
		assertTrue(data.get("Convention Centre Dublin").get(0).getAge() > data.get("Convention Centre Dublin").get(1)
				.getAge());
	}

}

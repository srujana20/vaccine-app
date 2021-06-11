package com.hpe.vaccine.app.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.hpe.vaccine.app.exception.ParseException;


public class ParserTest {

	@DisplayName("testReadFileVaccinationParser: Successfully reads data from vaccination centre info file")
	@Test
	public void testReadFileVaccinationParser() {
		DataParser parser = new VaccinationCentreInfoParser("vaccination-centres.txt");
		try {
			parser.processData();
			assertNotNull(((VaccinationCentreInfoParser) parser).getVaccinationInfo());
		} catch (ParseException e) {
		}
	}

	@DisplayName("testReadFileVaccinationParserInvalid: Fails to find the file and exits the application")
	@Test
	public void testReadFileVaccinationParserInvalid() {
		DataParser parser = new VaccinationCentreInfoParser("abc.txt");
		try {
			parser.processData();
		} catch (ParseException e) {
			assertNotNull(e);
		}
	}

	@DisplayName("testReadFilePopulationParser: Successfully reads data from population info file")
	@Test
	public void testReadFilePopulationParser() {
		DataParser parser = new PopulationInfoParser("People.txt");
		try {
			parser.processData();
			assertNotNull(((PopulationInfoParser) parser).getPopulationInfo());
		} catch (ParseException e) {
		}
	}

	@DisplayName("testReadFilePopulationParserInvalid: Fails to find the file and exits the application")
	@Test
	public void testReadFilePopulationParserInvalid() {
		DataParser parser = new PopulationInfoParser("People.txt");
		try {
			parser.processData();
		} catch (ParseException e) {
			assertNotNull(e);
		}
	}

	@DisplayName("testMapToObjectVaccinationParserInvaldiObj: Fails to map data as object is invalid(null), throws an exception")
	@Test
	public void testMapToObjectVaccinationParserInvaldiObj() {
		DataParser parser = new VaccinationCentreInfoParser("vaccination_centres_empty.txt");
		try {
			parser.processData();
		} catch (ParseException e) {
			assertNotNull(e);
		}
	}

	@DisplayName("testMapToObjectPopulationParserInvaldiObj: Fails to map data as object is invalid(null), throws an exception")
	@Test
	public void testMapToObjectPopulationParserInvaldiObj() {
		DataParser parser = new PopulationInfoParser("People_emtpy.txt");
		try {
			parser.processData();
		} catch (ParseException e) {
			assertNotNull(e);
		}
	}

}

package com.hpe.vaccine.app.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hpe.vaccine.app.exception.ConfigurationException;
import com.hpe.vaccine.app.exception.ErrorMessage;
import com.hpe.vaccine.app.model.Constants;


public class ConfigTest {

	@DisplayName("testConfigWithInvalidFileName: Fails to find the file and exits the application")
	@Test
	public void testConfigWithInvalidFileName() {
		try {
			new Config("abc.properties");
		} catch (ConfigurationException e) {
			assertNotNull(e);
			assertTrue(e.getMessage().contains(ErrorMessage.CONFIGURATION_FILE_READ_ERROR));
		}
	}

	@DisplayName("testConfigWithEmptyFileName: Fails to find the file and exits the application")
	@Test
	public void testConfigWithEmptyFileName() {
		try {
			new Config("");
		} catch (ConfigurationException e) {
			assertNotNull(e);
			assertTrue(e.getMessage().contains(ErrorMessage.CONFIGURATION_FILE_READ_ERROR));
		}
	}

	@DisplayName("testConfigWithValidFileName: Successfully loads the configuration")
	@Test
	public void testConfigWithValidFileName() {
		try {
			Config config = new Config(Constants.CONFIG_FILE_NAME);
			assertNotNull(config);
		} catch (ConfigurationException e) {
		}
	}

	@DisplayName("testGetPropertyWithInvalidInput: Fails to fetch key and throws exception")
	@Test
	public void testGetPropertyWithInvalidInput() {
		try {
			Config config = new Config(Constants.CONFIG_FILE_NAME);
			config.getProperty("", "abc");
		} catch (ConfigurationException e) {
			assertNotNull(e);
			assertTrue(e.getMessage().contains(ErrorMessage.FAILED_TO_FIND_PROPERTY));
		}
	}

	@DisplayName("testGetPropertyWithNullInput: Fails to fetch key and throws exception")
	@Test
	public void testGetPropertyWithNullInput() {
		try {
			Config config = new Config(Constants.CONFIG_FILE_NAME);
			config.getProperty(null, "abc");
		} catch (ConfigurationException e) {
			assertNotNull(e);
			assertTrue(e.getMessage().contains(ErrorMessage.FAILED_TO_FIND_PROPERTY));
		}
	}

	@DisplayName("testGetPropertyWithValidInput: Successfully fetches the file name to fetch info")
	@Test
	public void testGetPropertyWithValidInput() {
		try {
			Config config = new Config(Constants.CONFIG_FILE_NAME);
			String value = config.getProperty(Constants.POPULATION_INFO_FILE_NAME_PROPKEY, "abc.txt");
			assertEquals(value, "People.txt");
		} catch (ConfigurationException e) {
		}
	}

	@DisplayName("testGetPropertyWithEmptyValueInKey: Overrides the empty value from config file with the default value")
	@Test
	public void testGetPropertyWithEmptyValueInKey() {
		try {
			Config config = new Config("config_empty.properties");
			String value = config.getProperty(Constants.VACCINATION_centres_FILE_NAME_PROPKEY,
					"vaccination-centres.txt");
			assertEquals(value, "vaccination-centres.txt");
		} catch (ConfigurationException e) {
		}
	}

}

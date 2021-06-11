package com.hpe.vaccine.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Model class to capture population attributes
 * 
 * @author Srujana Chipilla
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "VaccinationCentre" })
@JsonPropertyOrder({ "Name", "Age", "Latitude", "Longitude" })
public class Person {

	public Person() {
		super();
	}

	/**
	 * Constructor
	 * Fields are marked as required, in case any field value is not provided, throws an exception
	 * @param Name      - name of the person
	 * @param Age       - age of the person
	 * @param Latitude  - latitude of the person's address
	 * @param Longitude - longitude of the person's address
	 */
	public Person(@JsonProperty(value = "Name", required = true) String Name,
			@JsonProperty(value = "Age", required = true) Integer Age,
			@JsonProperty(value = "Latitude", required = true) Double Latitude,
			@JsonProperty(value = "Longitude", required = true) Double Longitude) {
		super();
		this.name = Name;
		this.age = Age;
		this.latitude = Latitude;
		this.longitude = Longitude;
	}

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Age")
	private Integer age;

	@JsonProperty("Latitude")
	private Double latitude;

	@JsonProperty("Longitude")
	private Double longitude;

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Name")
	public void setName(String Name) {
		this.name = Name;
	}

	@JsonProperty("Age")
	public int getAge() {
		return age;
	}

	@JsonProperty("Age")
	public void setAge(int Age) {
		this.age = Age;
	}

	@JsonProperty("Latitude")
	public double getLatitude() {
		return latitude;
	}

	@JsonProperty("Latitude")
	public void setLatitude(double Latitude) {
		this.latitude = Latitude;
	}

	@JsonProperty("Longitude")
	public double getLongitude() {
		return longitude;
	}

	@JsonProperty("Longitude")
	public void setLongitude(double Longitude) {
		this.longitude = Longitude;
	}

	private String VaccinationCentre;

	public String getVaccinationCentre() {
		return VaccinationCentre;
	}

	public void setVaccinationCentre(String VaccinationCentre) {
		this.VaccinationCentre = VaccinationCentre;
	}

}

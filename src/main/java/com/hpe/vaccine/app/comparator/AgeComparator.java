package com.hpe.vaccine.app.comparator;

import java.util.Comparator;

import com.hpe.vaccine.app.model.Person;


/**
 * A comparator to order elements based on age of a person (Descending order)
 * 
 * @author Srujana Chipilla
 *
 */
public class AgeComparator implements Comparator<Person> {

	/**
	 * Compares ages of two persons passed as input arguments for ordering data.
	 * Returns or a negative integer if the second person's age is greater than
	 * first and a positive integer if second person's age is smaller than first
	 */
	@Override
	public int compare(Person e1, Person e2) {
		if (e2.getAge() > e1.getAge()) {
			return 1;
		} else {
			return -1;
		}
	}
}

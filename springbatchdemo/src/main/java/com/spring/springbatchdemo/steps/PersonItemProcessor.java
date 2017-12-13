package com.spring.springbatchdemo.steps;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.spring.springbatchdemo.model.Person;

/**
 * @author VishalCRai
 *
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
	private static final Logger logger = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(Person person) throws Exception {

		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();

		logger.info("firstname=" + firstName + ", lastname=" + lastName + ", id=" + person.getId() + " dateTime="
				+ (new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a")).format(person.getDateTime()));
		final Person transformedPerson = new Person(person.getId(), firstName, lastName, person.getStatus(),
				person.getDateTime());

		logger.info("Converting (" + person + ") into (" + transformedPerson + ")");

		return transformedPerson;
	}

}

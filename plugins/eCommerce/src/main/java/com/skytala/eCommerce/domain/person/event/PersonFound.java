package com.skytala.eCommerce.domain.person.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.person.model.Person;
public class PersonFound implements Event{

	private List<Person> persons;

	public PersonFound(List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getPersons()	{
		return persons;
	}

}

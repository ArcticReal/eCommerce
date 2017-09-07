package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Person;

public class PersonFound implements Event{

	private List<Person> persons;

	public PersonFound(List<Person> persons) {
		this.setPersons(persons);
	}

	public List<Person> getPersons()	{
		return persons;
	}

	public void setPersons(List<Person> persons)	{
		this.persons = persons;
	}
}

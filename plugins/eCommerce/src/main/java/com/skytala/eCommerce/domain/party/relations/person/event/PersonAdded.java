package com.skytala.eCommerce.domain.party.relations.person.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.person.model.Person;
public class PersonAdded implements Event{

	private Person addedPerson;
	private boolean success;

	public PersonAdded(Person addedPerson, boolean success){
		this.addedPerson = addedPerson;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Person getAddedPerson() {
		return addedPerson;
	}

}

package com.skytala.eCommerce.domain.person.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.person.model.Person;
public class PersonDeleted implements Event{

	private boolean success;

	public PersonDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

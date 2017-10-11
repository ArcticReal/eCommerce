package com.skytala.eCommerce.domain.party.relations.contactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
public class ContactMechUpdated implements Event{

	private boolean success;

	public ContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

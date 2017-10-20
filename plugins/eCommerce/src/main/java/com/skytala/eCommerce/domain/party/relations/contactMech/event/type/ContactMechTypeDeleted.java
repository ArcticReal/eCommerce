package com.skytala.eCommerce.domain.party.relations.contactMech.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.type.ContactMechType;
public class ContactMechTypeDeleted implements Event{

	private boolean success;

	public ContactMechTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

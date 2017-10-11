package com.skytala.eCommerce.domain.party.relations.contactMechType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechType.model.ContactMechType;
public class ContactMechTypeUpdated implements Event{

	private boolean success;

	public ContactMechTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

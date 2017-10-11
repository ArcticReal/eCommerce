package com.skytala.eCommerce.domain.party.relations.contactMechAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.model.ContactMechAttribute;
public class ContactMechAttributeUpdated implements Event{

	private boolean success;

	public ContactMechAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

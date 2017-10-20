package com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.attribute.ContactMechAttribute;
public class ContactMechAttributeUpdated implements Event{

	private boolean success;

	public ContactMechAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

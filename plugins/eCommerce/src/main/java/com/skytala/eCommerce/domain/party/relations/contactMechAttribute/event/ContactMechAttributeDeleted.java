package com.skytala.eCommerce.domain.party.relations.contactMechAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.model.ContactMechAttribute;
public class ContactMechAttributeDeleted implements Event{

	private boolean success;

	public ContactMechAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.model.ContactMechTypePurpose;
public class ContactMechTypePurposeUpdated implements Event{

	private boolean success;

	public ContactMechTypePurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

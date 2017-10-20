package com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;
public class ContactMechTypePurposeUpdated implements Event{

	private boolean success;

	public ContactMechTypePurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

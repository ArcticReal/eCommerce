package com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.model.ContactMechTypePurpose;
public class ContactMechTypePurposeDeleted implements Event{

	private boolean success;

	public ContactMechTypePurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

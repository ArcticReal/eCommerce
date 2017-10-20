package com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;
public class ContactMechTypePurposeDeleted implements Event{

	private boolean success;

	public ContactMechTypePurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

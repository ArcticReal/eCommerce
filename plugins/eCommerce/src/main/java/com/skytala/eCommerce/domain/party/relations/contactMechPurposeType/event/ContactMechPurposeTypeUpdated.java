package com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.model.ContactMechPurposeType;
public class ContactMechPurposeTypeUpdated implements Event{

	private boolean success;

	public ContactMechPurposeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

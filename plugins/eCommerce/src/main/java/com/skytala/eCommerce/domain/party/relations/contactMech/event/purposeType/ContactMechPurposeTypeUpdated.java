package com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;
public class ContactMechPurposeTypeUpdated implements Event{

	private boolean success;

	public ContactMechPurposeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

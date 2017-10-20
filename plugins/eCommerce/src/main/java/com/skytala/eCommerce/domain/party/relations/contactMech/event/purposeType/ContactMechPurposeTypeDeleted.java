package com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;
public class ContactMechPurposeTypeDeleted implements Event{

	private boolean success;

	public ContactMechPurposeTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.model.ContactListPartyStatus;
public class ContactListPartyStatusUpdated implements Event{

	private boolean success;

	public ContactListPartyStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

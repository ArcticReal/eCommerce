package com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus.ContactListPartyStatus;
public class ContactListPartyStatusUpdated implements Event{

	private boolean success;

	public ContactListPartyStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

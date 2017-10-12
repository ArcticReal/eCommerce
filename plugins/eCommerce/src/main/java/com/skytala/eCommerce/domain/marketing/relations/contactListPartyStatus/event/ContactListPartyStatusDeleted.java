package com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.model.ContactListPartyStatus;
public class ContactListPartyStatusDeleted implements Event{

	private boolean success;

	public ContactListPartyStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

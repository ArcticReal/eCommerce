package com.skytala.eCommerce.domain.marketing.relations.contactListParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;
public class ContactListPartyDeleted implements Event{

	private boolean success;

	public ContactListPartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.marketing.relations.contactList.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.party.ContactListParty;
public class ContactListPartyUpdated implements Event{

	private boolean success;

	public ContactListPartyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

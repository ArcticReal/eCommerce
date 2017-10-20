package com.skytala.eCommerce.domain.marketing.relations.contactList.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.party.ContactListParty;
public class ContactListPartyDeleted implements Event{

	private boolean success;

	public ContactListPartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

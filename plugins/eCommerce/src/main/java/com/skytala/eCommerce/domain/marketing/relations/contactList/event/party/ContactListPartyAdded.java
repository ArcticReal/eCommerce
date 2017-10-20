package com.skytala.eCommerce.domain.marketing.relations.contactList.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.party.ContactListParty;
public class ContactListPartyAdded implements Event{

	private ContactListParty addedContactListParty;
	private boolean success;

	public ContactListPartyAdded(ContactListParty addedContactListParty, boolean success){
		this.addedContactListParty = addedContactListParty;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactListParty getAddedContactListParty() {
		return addedContactListParty;
	}

}

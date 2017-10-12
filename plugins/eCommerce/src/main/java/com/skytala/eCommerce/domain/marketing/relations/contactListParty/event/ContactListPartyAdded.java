package com.skytala.eCommerce.domain.marketing.relations.contactListParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;
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

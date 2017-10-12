package com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.model.ContactListPartyStatus;
public class ContactListPartyStatusAdded implements Event{

	private ContactListPartyStatus addedContactListPartyStatus;
	private boolean success;

	public ContactListPartyStatusAdded(ContactListPartyStatus addedContactListPartyStatus, boolean success){
		this.addedContactListPartyStatus = addedContactListPartyStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactListPartyStatus getAddedContactListPartyStatus() {
		return addedContactListPartyStatus;
	}

}

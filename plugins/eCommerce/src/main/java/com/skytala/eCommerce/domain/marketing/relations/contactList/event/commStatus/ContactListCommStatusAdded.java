package com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus.ContactListCommStatus;
public class ContactListCommStatusAdded implements Event{

	private ContactListCommStatus addedContactListCommStatus;
	private boolean success;

	public ContactListCommStatusAdded(ContactListCommStatus addedContactListCommStatus, boolean success){
		this.addedContactListCommStatus = addedContactListCommStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactListCommStatus getAddedContactListCommStatus() {
		return addedContactListCommStatus;
	}

}

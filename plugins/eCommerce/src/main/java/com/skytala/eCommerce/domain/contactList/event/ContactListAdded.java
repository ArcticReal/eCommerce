package com.skytala.eCommerce.domain.contactList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contactList.model.ContactList;
public class ContactListAdded implements Event{

	private ContactList addedContactList;
	private boolean success;

	public ContactListAdded(ContactList addedContactList, boolean success){
		this.addedContactList = addedContactList;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactList getAddedContactList() {
		return addedContactList;
	}

}

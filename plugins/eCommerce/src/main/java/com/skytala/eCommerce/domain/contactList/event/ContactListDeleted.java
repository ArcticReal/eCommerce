package com.skytala.eCommerce.domain.contactList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contactList.model.ContactList;
public class ContactListDeleted implements Event{

	private boolean success;

	public ContactListDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

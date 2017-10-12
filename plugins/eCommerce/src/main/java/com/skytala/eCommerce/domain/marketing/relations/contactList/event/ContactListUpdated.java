package com.skytala.eCommerce.domain.marketing.relations.contactList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.ContactList;
public class ContactListUpdated implements Event{

	private boolean success;

	public ContactListUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

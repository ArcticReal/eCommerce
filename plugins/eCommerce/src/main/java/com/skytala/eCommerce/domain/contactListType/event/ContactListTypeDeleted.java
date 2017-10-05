package com.skytala.eCommerce.domain.contactListType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contactListType.model.ContactListType;
public class ContactListTypeDeleted implements Event{

	private boolean success;

	public ContactListTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

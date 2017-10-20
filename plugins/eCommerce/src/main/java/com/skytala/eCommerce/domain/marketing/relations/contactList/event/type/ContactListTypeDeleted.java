package com.skytala.eCommerce.domain.marketing.relations.contactList.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.type.ContactListType;
public class ContactListTypeDeleted implements Event{

	private boolean success;

	public ContactListTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

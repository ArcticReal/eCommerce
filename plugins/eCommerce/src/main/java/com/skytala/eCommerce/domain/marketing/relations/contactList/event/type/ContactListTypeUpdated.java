package com.skytala.eCommerce.domain.marketing.relations.contactList.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.type.ContactListType;
public class ContactListTypeUpdated implements Event{

	private boolean success;

	public ContactListTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.marketing.relations.contactListType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListType.model.ContactListType;
public class ContactListTypeUpdated implements Event{

	private boolean success;

	public ContactListTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

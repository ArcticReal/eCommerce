package com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus.ContactListCommStatus;
public class ContactListCommStatusUpdated implements Event{

	private boolean success;

	public ContactListCommStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

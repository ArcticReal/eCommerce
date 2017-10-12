package com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.model.ContactListCommStatus;
public class ContactListCommStatusUpdated implements Event{

	private boolean success;

	public ContactListCommStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

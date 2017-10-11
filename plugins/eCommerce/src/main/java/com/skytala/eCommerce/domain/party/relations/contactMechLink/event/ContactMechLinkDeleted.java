package com.skytala.eCommerce.domain.party.relations.contactMechLink.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechLink.model.ContactMechLink;
public class ContactMechLinkDeleted implements Event{

	private boolean success;

	public ContactMechLinkDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

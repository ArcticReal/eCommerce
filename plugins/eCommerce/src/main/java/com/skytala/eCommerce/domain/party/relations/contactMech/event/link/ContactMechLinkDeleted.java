package com.skytala.eCommerce.domain.party.relations.contactMech.event.link;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.link.ContactMechLink;
public class ContactMechLinkDeleted implements Event{

	private boolean success;

	public ContactMechLinkDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
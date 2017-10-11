package com.skytala.eCommerce.domain.party.relations.contactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
public class ContactMechDeleted implements Event{

	private boolean success;

	public ContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;
public class ContactMechTypeAttrDeleted implements Event{

	private boolean success;

	public ContactMechTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

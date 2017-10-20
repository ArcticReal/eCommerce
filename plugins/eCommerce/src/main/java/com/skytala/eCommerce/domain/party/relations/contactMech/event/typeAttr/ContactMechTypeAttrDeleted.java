package com.skytala.eCommerce.domain.party.relations.contactMech.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.typeAttr.ContactMechTypeAttr;
public class ContactMechTypeAttrDeleted implements Event{

	private boolean success;

	public ContactMechTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

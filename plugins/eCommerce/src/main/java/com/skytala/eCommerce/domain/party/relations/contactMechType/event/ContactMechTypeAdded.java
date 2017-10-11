package com.skytala.eCommerce.domain.party.relations.contactMechType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechType.model.ContactMechType;
public class ContactMechTypeAdded implements Event{

	private ContactMechType addedContactMechType;
	private boolean success;

	public ContactMechTypeAdded(ContactMechType addedContactMechType, boolean success){
		this.addedContactMechType = addedContactMechType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMechType getAddedContactMechType() {
		return addedContactMechType;
	}

}

package com.skytala.eCommerce.domain.party.relations.contactMech.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.type.ContactMechType;
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

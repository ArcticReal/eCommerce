package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;
public class ContactMechTypeAttrAdded implements Event{

	private ContactMechTypeAttr addedContactMechTypeAttr;
	private boolean success;

	public ContactMechTypeAttrAdded(ContactMechTypeAttr addedContactMechTypeAttr, boolean success){
		this.addedContactMechTypeAttr = addedContactMechTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMechTypeAttr getAddedContactMechTypeAttr() {
		return addedContactMechTypeAttr;
	}

}

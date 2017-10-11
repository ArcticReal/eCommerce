package com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.model.ContactMechPurposeType;
public class ContactMechPurposeTypeAdded implements Event{

	private ContactMechPurposeType addedContactMechPurposeType;
	private boolean success;

	public ContactMechPurposeTypeAdded(ContactMechPurposeType addedContactMechPurposeType, boolean success){
		this.addedContactMechPurposeType = addedContactMechPurposeType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMechPurposeType getAddedContactMechPurposeType() {
		return addedContactMechPurposeType;
	}

}

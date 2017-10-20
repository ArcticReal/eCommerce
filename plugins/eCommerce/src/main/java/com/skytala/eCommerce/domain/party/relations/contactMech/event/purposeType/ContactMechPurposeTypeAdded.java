package com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;
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

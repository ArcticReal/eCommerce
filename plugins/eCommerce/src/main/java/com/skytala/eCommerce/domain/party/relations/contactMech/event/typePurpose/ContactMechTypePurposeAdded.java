package com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;
public class ContactMechTypePurposeAdded implements Event{

	private ContactMechTypePurpose addedContactMechTypePurpose;
	private boolean success;

	public ContactMechTypePurposeAdded(ContactMechTypePurpose addedContactMechTypePurpose, boolean success){
		this.addedContactMechTypePurpose = addedContactMechTypePurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMechTypePurpose getAddedContactMechTypePurpose() {
		return addedContactMechTypePurpose;
	}

}

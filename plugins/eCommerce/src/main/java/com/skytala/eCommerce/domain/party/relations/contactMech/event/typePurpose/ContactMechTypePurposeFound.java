package com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;
public class ContactMechTypePurposeFound implements Event{

	private List<ContactMechTypePurpose> contactMechTypePurposes;

	public ContactMechTypePurposeFound(List<ContactMechTypePurpose> contactMechTypePurposes) {
		this.contactMechTypePurposes = contactMechTypePurposes;
	}

	public List<ContactMechTypePurpose> getContactMechTypePurposes()	{
		return contactMechTypePurposes;
	}

}

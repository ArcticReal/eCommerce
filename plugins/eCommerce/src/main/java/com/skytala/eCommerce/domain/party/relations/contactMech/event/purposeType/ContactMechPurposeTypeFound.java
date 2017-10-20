package com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;
public class ContactMechPurposeTypeFound implements Event{

	private List<ContactMechPurposeType> contactMechPurposeTypes;

	public ContactMechPurposeTypeFound(List<ContactMechPurposeType> contactMechPurposeTypes) {
		this.contactMechPurposeTypes = contactMechPurposeTypes;
	}

	public List<ContactMechPurposeType> getContactMechPurposeTypes()	{
		return contactMechPurposeTypes;
	}

}

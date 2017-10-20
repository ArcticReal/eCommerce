package com.skytala.eCommerce.domain.party.relations.contactMech.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.type.ContactMechType;
public class ContactMechTypeFound implements Event{

	private List<ContactMechType> contactMechTypes;

	public ContactMechTypeFound(List<ContactMechType> contactMechTypes) {
		this.contactMechTypes = contactMechTypes;
	}

	public List<ContactMechType> getContactMechTypes()	{
		return contactMechTypes;
	}

}

package com.skytala.eCommerce.domain.contactMechType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contactMechType.model.ContactMechType;
public class ContactMechTypeFound implements Event{

	private List<ContactMechType> contactMechTypes;

	public ContactMechTypeFound(List<ContactMechType> contactMechTypes) {
		this.contactMechTypes = contactMechTypes;
	}

	public List<ContactMechType> getContactMechTypes()	{
		return contactMechTypes;
	}

}

package com.skytala.eCommerce.domain.contactMech.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contactMech.model.ContactMech;
public class ContactMechFound implements Event{

	private List<ContactMech> contactMechs;

	public ContactMechFound(List<ContactMech> contactMechs) {
		this.contactMechs = contactMechs;
	}

	public List<ContactMech> getContactMechs()	{
		return contactMechs;
	}

}

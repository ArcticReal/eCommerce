package com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.attribute.ContactMechAttribute;
public class ContactMechAttributeFound implements Event{

	private List<ContactMechAttribute> contactMechAttributes;

	public ContactMechAttributeFound(List<ContactMechAttribute> contactMechAttributes) {
		this.contactMechAttributes = contactMechAttributes;
	}

	public List<ContactMechAttribute> getContactMechAttributes()	{
		return contactMechAttributes;
	}

}

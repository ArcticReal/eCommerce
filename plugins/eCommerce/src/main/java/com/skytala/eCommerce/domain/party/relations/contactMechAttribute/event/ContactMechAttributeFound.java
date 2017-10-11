package com.skytala.eCommerce.domain.party.relations.contactMechAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.model.ContactMechAttribute;
public class ContactMechAttributeFound implements Event{

	private List<ContactMechAttribute> contactMechAttributes;

	public ContactMechAttributeFound(List<ContactMechAttribute> contactMechAttributes) {
		this.contactMechAttributes = contactMechAttributes;
	}

	public List<ContactMechAttribute> getContactMechAttributes()	{
		return contactMechAttributes;
	}

}

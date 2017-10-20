package com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.attribute.ContactMechAttribute;
public class ContactMechAttributeAdded implements Event{

	private ContactMechAttribute addedContactMechAttribute;
	private boolean success;

	public ContactMechAttributeAdded(ContactMechAttribute addedContactMechAttribute, boolean success){
		this.addedContactMechAttribute = addedContactMechAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMechAttribute getAddedContactMechAttribute() {
		return addedContactMechAttribute;
	}

}

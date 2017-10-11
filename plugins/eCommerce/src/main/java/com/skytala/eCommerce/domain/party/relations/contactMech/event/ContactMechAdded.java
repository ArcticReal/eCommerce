package com.skytala.eCommerce.domain.party.relations.contactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
public class ContactMechAdded implements Event{

	private ContactMech addedContactMech;
	private boolean success;

	public ContactMechAdded(ContactMech addedContactMech, boolean success){
		this.addedContactMech = addedContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMech getAddedContactMech() {
		return addedContactMech;
	}

}

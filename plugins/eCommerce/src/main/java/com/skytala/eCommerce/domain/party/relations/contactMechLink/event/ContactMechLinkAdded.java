package com.skytala.eCommerce.domain.party.relations.contactMechLink.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechLink.model.ContactMechLink;
public class ContactMechLinkAdded implements Event{

	private ContactMechLink addedContactMechLink;
	private boolean success;

	public ContactMechLinkAdded(ContactMechLink addedContactMechLink, boolean success){
		this.addedContactMechLink = addedContactMechLink;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactMechLink getAddedContactMechLink() {
		return addedContactMechLink;
	}

}

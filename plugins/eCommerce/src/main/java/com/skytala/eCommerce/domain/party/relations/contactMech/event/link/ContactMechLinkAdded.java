package com.skytala.eCommerce.domain.party.relations.contactMech.event.link;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.link.ContactMechLink;
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

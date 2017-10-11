package com.skytala.eCommerce.domain.party.relations.contactMechLink.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechLink.model.ContactMechLink;
public class ContactMechLinkFound implements Event{

	private List<ContactMechLink> contactMechLinks;

	public ContactMechLinkFound(List<ContactMechLink> contactMechLinks) {
		this.contactMechLinks = contactMechLinks;
	}

	public List<ContactMechLink> getContactMechLinks()	{
		return contactMechLinks;
	}

}

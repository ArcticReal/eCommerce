package com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus.ContactListPartyStatus;
public class ContactListPartyStatusFound implements Event{

	private List<ContactListPartyStatus> contactListPartyStatuss;

	public ContactListPartyStatusFound(List<ContactListPartyStatus> contactListPartyStatuss) {
		this.contactListPartyStatuss = contactListPartyStatuss;
	}

	public List<ContactListPartyStatus> getContactListPartyStatuss()	{
		return contactListPartyStatuss;
	}

}

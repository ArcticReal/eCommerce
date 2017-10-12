package com.skytala.eCommerce.domain.marketing.relations.contactListParty.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;
public class ContactListPartyFound implements Event{

	private List<ContactListParty> contactListPartys;

	public ContactListPartyFound(List<ContactListParty> contactListPartys) {
		this.contactListPartys = contactListPartys;
	}

	public List<ContactListParty> getContactListPartys()	{
		return contactListPartys;
	}

}

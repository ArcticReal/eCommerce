package com.skytala.eCommerce.domain.marketing.relations.contactList.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.ContactList;
public class ContactListFound implements Event{

	private List<ContactList> contactLists;

	public ContactListFound(List<ContactList> contactLists) {
		this.contactLists = contactLists;
	}

	public List<ContactList> getContactLists()	{
		return contactLists;
	}

}

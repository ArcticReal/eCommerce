package com.skytala.eCommerce.domain.marketing.relations.contactList.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.type.ContactListType;
public class ContactListTypeFound implements Event{

	private List<ContactListType> contactListTypes;

	public ContactListTypeFound(List<ContactListType> contactListTypes) {
		this.contactListTypes = contactListTypes;
	}

	public List<ContactListType> getContactListTypes()	{
		return contactListTypes;
	}

}

package com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus.ContactListCommStatus;
public class ContactListCommStatusFound implements Event{

	private List<ContactListCommStatus> contactListCommStatuss;

	public ContactListCommStatusFound(List<ContactListCommStatus> contactListCommStatuss) {
		this.contactListCommStatuss = contactListCommStatuss;
	}

	public List<ContactListCommStatus> getContactListCommStatuss()	{
		return contactListCommStatuss;
	}

}

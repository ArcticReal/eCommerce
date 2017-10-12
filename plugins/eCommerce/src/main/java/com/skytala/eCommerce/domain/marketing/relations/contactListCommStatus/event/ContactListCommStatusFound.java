package com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.model.ContactListCommStatus;
public class ContactListCommStatusFound implements Event{

	private List<ContactListCommStatus> contactListCommStatuss;

	public ContactListCommStatusFound(List<ContactListCommStatus> contactListCommStatuss) {
		this.contactListCommStatuss = contactListCommStatuss;
	}

	public List<ContactListCommStatus> getContactListCommStatuss()	{
		return contactListCommStatuss;
	}

}

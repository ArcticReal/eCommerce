package com.skytala.eCommerce.domain.marketing.relations.contactList.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.type.ContactListType;
public class ContactListTypeAdded implements Event{

	private ContactListType addedContactListType;
	private boolean success;

	public ContactListTypeAdded(ContactListType addedContactListType, boolean success){
		this.addedContactListType = addedContactListType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContactListType getAddedContactListType() {
		return addedContactListType;
	}

}

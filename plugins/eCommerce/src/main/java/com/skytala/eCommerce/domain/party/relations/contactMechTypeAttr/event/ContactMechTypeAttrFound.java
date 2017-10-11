package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;
public class ContactMechTypeAttrFound implements Event{

	private List<ContactMechTypeAttr> contactMechTypeAttrs;

	public ContactMechTypeAttrFound(List<ContactMechTypeAttr> contactMechTypeAttrs) {
		this.contactMechTypeAttrs = contactMechTypeAttrs;
	}

	public List<ContactMechTypeAttr> getContactMechTypeAttrs()	{
		return contactMechTypeAttrs;
	}

}

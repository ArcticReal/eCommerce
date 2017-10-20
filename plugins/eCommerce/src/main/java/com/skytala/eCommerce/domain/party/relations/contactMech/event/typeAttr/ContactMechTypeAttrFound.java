package com.skytala.eCommerce.domain.party.relations.contactMech.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.typeAttr.ContactMechTypeAttr;
public class ContactMechTypeAttrFound implements Event{

	private List<ContactMechTypeAttr> contactMechTypeAttrs;

	public ContactMechTypeAttrFound(List<ContactMechTypeAttr> contactMechTypeAttrs) {
		this.contactMechTypeAttrs = contactMechTypeAttrs;
	}

	public List<ContactMechTypeAttr> getContactMechTypeAttrs()	{
		return contactMechTypeAttrs;
	}

}

package com.skytala.eCommerce.domain.party.relations.party.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.attribute.PartyAttribute;
public class PartyAttributeAdded implements Event{

	private PartyAttribute addedPartyAttribute;
	private boolean success;

	public PartyAttributeAdded(PartyAttribute addedPartyAttribute, boolean success){
		this.addedPartyAttribute = addedPartyAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyAttribute getAddedPartyAttribute() {
		return addedPartyAttribute;
	}

}

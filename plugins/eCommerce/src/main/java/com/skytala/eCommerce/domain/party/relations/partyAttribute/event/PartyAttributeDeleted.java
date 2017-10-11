package com.skytala.eCommerce.domain.party.relations.partyAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyAttribute.model.PartyAttribute;
public class PartyAttributeDeleted implements Event{

	private boolean success;

	public PartyAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

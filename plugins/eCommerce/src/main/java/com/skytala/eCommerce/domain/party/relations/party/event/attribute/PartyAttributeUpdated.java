package com.skytala.eCommerce.domain.party.relations.party.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.attribute.PartyAttribute;
public class PartyAttributeUpdated implements Event{

	private boolean success;

	public PartyAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyGroup.model.PartyGroup;
public class PartyGroupUpdated implements Event{

	private boolean success;

	public PartyGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

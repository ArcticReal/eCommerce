package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;
public class PartyIcsAvsOverrideUpdated implements Event{

	private boolean success;

	public PartyIcsAvsOverrideUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

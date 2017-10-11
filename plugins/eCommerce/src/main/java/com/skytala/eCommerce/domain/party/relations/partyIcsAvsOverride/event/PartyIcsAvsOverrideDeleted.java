package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;
public class PartyIcsAvsOverrideDeleted implements Event{

	private boolean success;

	public PartyIcsAvsOverrideDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

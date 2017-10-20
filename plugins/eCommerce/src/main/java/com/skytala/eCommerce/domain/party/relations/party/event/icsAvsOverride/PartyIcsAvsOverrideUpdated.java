package com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.icsAvsOverride.PartyIcsAvsOverride;
public class PartyIcsAvsOverrideUpdated implements Event{

	private boolean success;

	public PartyIcsAvsOverrideUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;
public class PartyIcsAvsOverrideAdded implements Event{

	private PartyIcsAvsOverride addedPartyIcsAvsOverride;
	private boolean success;

	public PartyIcsAvsOverrideAdded(PartyIcsAvsOverride addedPartyIcsAvsOverride, boolean success){
		this.addedPartyIcsAvsOverride = addedPartyIcsAvsOverride;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyIcsAvsOverride getAddedPartyIcsAvsOverride() {
		return addedPartyIcsAvsOverride;
	}

}

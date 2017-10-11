package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;
public class PartyIcsAvsOverrideFound implements Event{

	private List<PartyIcsAvsOverride> partyIcsAvsOverrides;

	public PartyIcsAvsOverrideFound(List<PartyIcsAvsOverride> partyIcsAvsOverrides) {
		this.partyIcsAvsOverrides = partyIcsAvsOverrides;
	}

	public List<PartyIcsAvsOverride> getPartyIcsAvsOverrides()	{
		return partyIcsAvsOverrides;
	}

}

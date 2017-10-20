package com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.icsAvsOverride.PartyIcsAvsOverride;
public class PartyIcsAvsOverrideFound implements Event{

	private List<PartyIcsAvsOverride> partyIcsAvsOverrides;

	public PartyIcsAvsOverrideFound(List<PartyIcsAvsOverride> partyIcsAvsOverrides) {
		this.partyIcsAvsOverrides = partyIcsAvsOverrides;
	}

	public List<PartyIcsAvsOverride> getPartyIcsAvsOverrides()	{
		return partyIcsAvsOverrides;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyNeed.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNeed.model.PartyNeed;
public class PartyNeedFound implements Event{

	private List<PartyNeed> partyNeeds;

	public PartyNeedFound(List<PartyNeed> partyNeeds) {
		this.partyNeeds = partyNeeds;
	}

	public List<PartyNeed> getPartyNeeds()	{
		return partyNeeds;
	}

}

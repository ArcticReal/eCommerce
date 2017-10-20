package com.skytala.eCommerce.domain.party.relations.party.event.group;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;
public class PartyGroupFound implements Event{

	private List<PartyGroup> partyGroups;

	public PartyGroupFound(List<PartyGroup> partyGroups) {
		this.partyGroups = partyGroups;
	}

	public List<PartyGroup> getPartyGroups()	{
		return partyGroups;
	}

}

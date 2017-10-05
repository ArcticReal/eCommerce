package com.skytala.eCommerce.domain.partyClassificationGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyClassificationGroup.model.PartyClassificationGroup;
public class PartyClassificationGroupFound implements Event{

	private List<PartyClassificationGroup> partyClassificationGroups;

	public PartyClassificationGroupFound(List<PartyClassificationGroup> partyClassificationGroups) {
		this.partyClassificationGroups = partyClassificationGroups;
	}

	public List<PartyClassificationGroup> getPartyClassificationGroups()	{
		return partyClassificationGroups;
	}

}

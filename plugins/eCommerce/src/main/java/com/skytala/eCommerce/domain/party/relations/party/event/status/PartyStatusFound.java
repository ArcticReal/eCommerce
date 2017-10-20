package com.skytala.eCommerce.domain.party.relations.party.event.status;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.status.PartyStatus;
public class PartyStatusFound implements Event{

	private List<PartyStatus> partyStatuss;

	public PartyStatusFound(List<PartyStatus> partyStatuss) {
		this.partyStatuss = partyStatuss;
	}

	public List<PartyStatus> getPartyStatuss()	{
		return partyStatuss;
	}

}

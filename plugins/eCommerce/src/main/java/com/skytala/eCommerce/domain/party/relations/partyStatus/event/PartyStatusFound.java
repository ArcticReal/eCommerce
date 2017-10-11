package com.skytala.eCommerce.domain.party.relations.partyStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyStatus.model.PartyStatus;
public class PartyStatusFound implements Event{

	private List<PartyStatus> partyStatuss;

	public PartyStatusFound(List<PartyStatus> partyStatuss) {
		this.partyStatuss = partyStatuss;
	}

	public List<PartyStatus> getPartyStatuss()	{
		return partyStatuss;
	}

}

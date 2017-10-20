package com.skytala.eCommerce.domain.party.relations.party.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.type.PartyType;
public class PartyTypeFound implements Event{

	private List<PartyType> partyTypes;

	public PartyTypeFound(List<PartyType> partyTypes) {
		this.partyTypes = partyTypes;
	}

	public List<PartyType> getPartyTypes()	{
		return partyTypes;
	}

}

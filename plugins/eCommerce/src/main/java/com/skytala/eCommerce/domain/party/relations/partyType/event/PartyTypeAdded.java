package com.skytala.eCommerce.domain.party.relations.partyType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyType.model.PartyType;
public class PartyTypeAdded implements Event{

	private PartyType addedPartyType;
	private boolean success;

	public PartyTypeAdded(PartyType addedPartyType, boolean success){
		this.addedPartyType = addedPartyType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyType getAddedPartyType() {
		return addedPartyType;
	}

}

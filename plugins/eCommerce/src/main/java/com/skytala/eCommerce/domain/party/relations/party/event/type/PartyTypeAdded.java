package com.skytala.eCommerce.domain.party.relations.party.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.type.PartyType;
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

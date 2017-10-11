package com.skytala.eCommerce.domain.party.relations.partyStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyStatus.model.PartyStatus;
public class PartyStatusAdded implements Event{

	private PartyStatus addedPartyStatus;
	private boolean success;

	public PartyStatusAdded(PartyStatus addedPartyStatus, boolean success){
		this.addedPartyStatus = addedPartyStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyStatus getAddedPartyStatus() {
		return addedPartyStatus;
	}

}

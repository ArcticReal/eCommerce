package com.skytala.eCommerce.domain.party.relations.partyNeed.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNeed.model.PartyNeed;
public class PartyNeedAdded implements Event{

	private PartyNeed addedPartyNeed;
	private boolean success;

	public PartyNeedAdded(PartyNeed addedPartyNeed, boolean success){
		this.addedPartyNeed = addedPartyNeed;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyNeed getAddedPartyNeed() {
		return addedPartyNeed;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyType.model.PartyType;
public class PartyTypeDeleted implements Event{

	private boolean success;

	public PartyTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

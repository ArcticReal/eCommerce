package com.skytala.eCommerce.domain.party.relations.partyStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyStatus.model.PartyStatus;
public class PartyStatusDeleted implements Event{

	private boolean success;

	public PartyStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

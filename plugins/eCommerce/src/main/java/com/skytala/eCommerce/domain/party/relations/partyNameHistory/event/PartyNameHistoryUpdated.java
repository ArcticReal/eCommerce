package com.skytala.eCommerce.domain.party.relations.partyNameHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNameHistory.model.PartyNameHistory;
public class PartyNameHistoryUpdated implements Event{

	private boolean success;

	public PartyNameHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

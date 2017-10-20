package com.skytala.eCommerce.domain.party.relations.party.event.nameHistory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;
public class PartyNameHistoryUpdated implements Event{

	private boolean success;

	public PartyNameHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

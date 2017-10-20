package com.skytala.eCommerce.domain.party.relations.party.event.nameHistory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;
public class PartyNameHistoryDeleted implements Event{

	private boolean success;

	public PartyNameHistoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

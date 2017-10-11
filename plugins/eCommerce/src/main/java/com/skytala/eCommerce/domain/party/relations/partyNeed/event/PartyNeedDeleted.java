package com.skytala.eCommerce.domain.party.relations.partyNeed.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNeed.model.PartyNeed;
public class PartyNeedDeleted implements Event{

	private boolean success;

	public PartyNeedDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

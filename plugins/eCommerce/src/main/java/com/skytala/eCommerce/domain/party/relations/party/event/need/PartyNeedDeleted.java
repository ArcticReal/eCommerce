package com.skytala.eCommerce.domain.party.relations.party.event.need;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.need.PartyNeed;
public class PartyNeedDeleted implements Event{

	private boolean success;

	public PartyNeedDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

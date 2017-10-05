package com.skytala.eCommerce.domain.partyNeed.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyNeed.model.PartyNeed;
public class PartyNeedUpdated implements Event{

	private boolean success;

	public PartyNeedUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

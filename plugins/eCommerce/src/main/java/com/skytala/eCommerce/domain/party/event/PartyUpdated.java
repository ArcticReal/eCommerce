package com.skytala.eCommerce.domain.party.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.model.Party;
public class PartyUpdated implements Event{

	private boolean success;

	public PartyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

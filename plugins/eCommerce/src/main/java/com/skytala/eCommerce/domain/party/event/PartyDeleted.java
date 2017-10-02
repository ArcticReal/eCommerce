package com.skytala.eCommerce.domain.party.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.model.Party;
public class PartyDeleted implements Event{

	private boolean success;

	public PartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

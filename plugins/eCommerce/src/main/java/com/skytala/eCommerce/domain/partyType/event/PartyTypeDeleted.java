package com.skytala.eCommerce.domain.partyType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyType.model.PartyType;
public class PartyTypeDeleted implements Event{

	private boolean success;

	public PartyTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

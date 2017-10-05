package com.skytala.eCommerce.domain.partyType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyType.model.PartyType;
public class PartyTypeUpdated implements Event{

	private boolean success;

	public PartyTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

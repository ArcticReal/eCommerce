package com.skytala.eCommerce.domain.party.relations.telecomNumber.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;
public class TelecomNumberUpdated implements Event{

	private boolean success;

	public TelecomNumberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
